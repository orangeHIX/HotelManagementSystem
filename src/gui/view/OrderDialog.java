package gui.view;

import business.BusinessFactory;
import business.entity.NewOrder;
import business.impl.NewOrderCreator;
import business.inter.*;
import business.utils.DateConvertor;
import entity.Customer;
import entity.Order;
import entity.Room;
import entity.RoomType;
import gui.ViewUtils;
import gui.new_order.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.Constant;
import utils.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderDialog extends JDialog implements OrderModelChangeListener, AddCustomerIntoNewOrderListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldOrderCode;
    private JTextField textFieldOrderGeneratedTime;
    private JTextField textFieldAccount;
    private JTextField textFieldRoomNo;
    private JButton buttonAddRoom;
    private JTextField textFieldCustomerName;
    private JTextField textFieldCustomerID;
    private JTextField textFieldCustomerPhone;
    private JTable tableRoomWithRate;
    private JTextField textFieldTotalPrice;
    private JTextField textFieldDiscount;
    private JTextField textFieldPriceAfterDiscount;
    private JComboBox comboBoxOrderState;
    private JTextField textFieldDeposit;
    private JDatePickerImpl datePickerCheckInTime;
    private JDatePickerImpl datePickerCheckOutTime;
    private JButton buttonAddCustomer;
    private JEditorPane editorNote;
    private JLabel labelMinCheckInDate;
    private JLabel labelMaxCheckOutDate;
    private JLabel labelMinCheckInDateTag;
    private JLabel labelMaxCheckOutDateTag;

    private OrderDialogModel orderModel;

    private DecimalFormat decimalFormat;
    private SimpleDateFormat dateFormat;

    private OrderChangeListener orderListener;


    public OrderDialog() {
        super();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        decimalFormat = ViewUtils.getMoneyDecimalFormat();
        dateFormat = ViewUtils.getDateFormat_yyyy_MM_dd_E();
    }

    public OrderDialog(Order order) {
        this();
        if (order != null) {
            if (order.getOrderState().equals(Order.OrderState.reservation_confirm)) {
                CancelReservationPattern reservationPattern = new CancelReservationPattern(order);
                reservationPattern.setModel();
                reservationPattern.addListener();
                reservationPattern.initComponent();
            } else if (order.getOrderState().equals(Order.OrderState.checkin_confirm)) {
                CheckOutPattern checkOutPattern = new CheckOutPattern(order);
                checkOutPattern.setModel();
                checkOutPattern.addListener();
                checkOutPattern.initComponent();
            }
        }
    }

//    public OrderDialog(Calendar generateTime, Room room, Calendar minCheckInTime, Calendar maxCheckOutTime) {
//        this();
//
//    }

    public OrderDialog(Calendar generateTime, Room room,
                       Calendar minCheckInTime, Calendar maxCheckOutTime,
                       Calendar selectedDate) {
        this();
        //this(generateTime, room, minCheckInTime, maxCheckOutTime);

        Calendar now = Calendar.getInstance();
        long diff =selectedDate.getTimeInMillis()- now.getTimeInMillis();
    //todo 判断有问题
        if (diff > (24 * 60 * 60 * 1000)) {
            ReservationPattern newOrderPattern = new ReservationPattern();
            newOrderPattern.setModel(generateTime, room, null, minCheckInTime, maxCheckOutTime, selectedDate);
            newOrderPattern.addListener();
            newOrderPattern.initComponent();
        } else {
            CheckInNewOrderPattern checkInNewOrderPattern = new CheckInNewOrderPattern();
            checkInNewOrderPattern.setModel(generateTime, room, null, minCheckInTime, maxCheckOutTime, selectedDate);
            checkInNewOrderPattern.addListener();
            checkInNewOrderPattern.initComponent();
        }


        //Log.d("second");
    }

    public void setOrderChangeListener(OrderChangeListener newOrderListener) {
        this.orderListener = newOrderListener;
    }


    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(getContentPane(), message);
    }

    private void onCancel() {
        dispose();
    }

    private void createUIComponents() {
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();
        datePickerCheckInTime = (JDatePickerImpl) dateComponentFactory.createJDatePicker();
        datePickerCheckOutTime = (JDatePickerImpl) dateComponentFactory.createJDatePicker();

        tableRoomWithRate = new JTable();
        tableRoomWithRate.getTableHeader().setReorderingAllowed(false);
        RoomRateTableCellRender render = new RoomRateTableCellRender();
        tableRoomWithRate.setDefaultRenderer(Integer.class, render);
        tableRoomWithRate.setDefaultRenderer(Date.class, render);

        buttonOK = new JButton();

        buttonAddRoom = new JButton();
        buttonAddRoom.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        buttonAddCustomer =new JButton();
        buttonAddCustomer.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));

    }


    @Override
    public void onBasicInfoChanged() {
        textFieldOrderGeneratedTime.setText(getOrderGenerateTimeString());
    }

    private String getOrderGenerateTimeString() {
        SimpleDateFormat dateFormat = ViewUtils.getTimeFormat_yyyy_MM_dd_HH_mm_ss_E();
        return dateFormat.format(orderModel.getOrderGenerateTime().getTime());
    }

    @Override
    public void onCustomerChanged() {
        Customer c = orderModel.getCustomer();
        textFieldCustomerID.setText(c.getIDNumber());
        textFieldCustomerName.setText(c.getName());
        textFieldCustomerPhone.setText(c.getPhoneNumber());
    }

    @Override
    public void onRoomChanged() {
        Room room = orderModel.getRoom();
        textFieldRoomNo.setText(room.getRoomNo() + " - " + room.getRoomType().getTypeName());
        changeMoneyField();
    }

    @Override
    public void onCheckInAndOutTimeChanged() {
        changeMoneyField();
    }

    private void changeMoneyField() {
        textFieldDeposit.setText(
                decimalFormat.format(((double) orderModel.getDeposit()) / 100));
        textFieldDiscount.setText(
                decimalFormat.format(((double) orderModel.getDiscount()) / 100));
        textFieldPriceAfterDiscount.setText(
                decimalFormat.format(((double) orderModel.getPriceAfterDiscount()) / 100));
        textFieldTotalPrice.setText(
                decimalFormat.format(((double) orderModel.getTotalPrice()) / 100));
    }

    @Override
    public void addCustomer(Customer customer) {
        orderModel.setCustomer(customer);
    }

    public static void main(String[] args) {
        RoomType roomType = new RoomType();
        roomType.setTypeName("商务间");
        roomType.setCapicity(3);
        roomType.setPricePerNight(20000);
        Room room = new Room();
        room.setRoomNo("0102");
        room.setRoomType(roomType);

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -2);
        cal2 = DateConvertor.toCheckInTime(cal2);
        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 4);
        cal3 = DateConvertor.toCheckOutTime(cal3);
        Calendar cal4 = Calendar.getInstance();

        OrderDialog dialog = new OrderDialog(
                cal1,
                room,
                cal2,
                cal3,
                cal4);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    class NewOrderPattern {

        void setModel(Calendar generateTime, Room room, Customer customer,
                      Calendar minCheckInTime, Calendar maxCheckOutTime, Calendar selectedDate) {
            tableRoomWithRate.setModel(new RoomRateTableModel());

            orderModel = new OrderDialogModel(OrderDialog.this);

            orderModel.setCheckInDateModel(datePickerCheckInTime.getModel());
            orderModel.setCheckOutDateModel(datePickerCheckOutTime.getModel());
            orderModel.setRoomRateModel((RoomRateTableModel) tableRoomWithRate.getModel());

            orderModel.setGenerateTime(generateTime);
            orderModel.setRoom(room);
            orderModel.setMinCheckInTime(minCheckInTime);
            orderModel.setMaxCheckOutTime(maxCheckOutTime);

            comboBoxOrderState.setModel(new OrderStateComboBoxModel());

            setDefaultCheckInAndOutTime(selectedDate);
        }

        private void setDefaultCheckInAndOutTime(Calendar selectedDate) {
            //这个设置顺序是有必要的。因为checkin时间必须比checkout时间晚，而datepicker初始时时间为当前时间
            selectedDate.add(Calendar.DATE, 1);
            orderModel.setCheckOutTime(selectedDate);

            selectedDate.add(Calendar.DATE, -1);
            orderModel.setCheckInTime(selectedDate);
        }

        void addListener() {

            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }
            });

            // call onNewOrderCancel() when cross is clicked
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onCancel();
                }
            });

            buttonAddCustomer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SelectCustomerDialog dialog = new SelectCustomerDialog();
                    dialog.setAddCustomerIntoNewOrderListener(OrderDialog.this);
                    dialog.setTitle("选择入住人");
                    Dimension size = new Dimension(
                            (int) (Constant.screenSize.getWidth() * 2 / 7),
                            (int) (Constant.screenSize.getHeight() * 2 / 5));
                    dialog.setSize(size);
                    dialog.setLocationByPlatform(true);
                    dialog.setVisible(true);
                }
            });
        }

        protected NewOrder createNewOrder() {
            INewOrderCreator newOrderCreator = new NewOrderCreator();
            NewOrder newOrder;

            if (!newOrderCreator.setRoom(orderModel.getRoom())) {
                showMessageDialog("无效房间");
                Log.d("createNewOrder", "invalid room");
                return null;
            }
            if (!newOrderCreator.setOrderCustomer(orderModel.getCustomer())) {
                showMessageDialog("无效下单人");
                buttonAddCustomer.requestFocus();
                Log.d("createNewOrder", "invalid order customer" + orderModel.getCustomer());
                return null;
            }
            if (!newOrderCreator.setSameStayedCustomer()) {
                //showMessageDialog("无效入住人");
                Log.d("createNewOrder", "invalid stayed customer");
                return null;
            }
            //Log.d("createNewOrder checkin", orderModel.getCheckInTime());
            //Log.d("createNewOrder checkout", orderModel.getCheckOutTime());
            if (!newOrderCreator.setTime(orderModel.getCheckInTime(), orderModel.getCheckOutTime())) {
                showMessageDialog("无效入住时间");
                Log.d("createNewOrder", "invalid time");
                return null;
            }
            newOrder = newOrderCreator.createNewOrder();
            Log.d("createNewOrder: ", newOrder);
            return newOrder;
        }


        void initComponent() {
            labelMinCheckInDate.setText(orderModel.getMinCheckInDateString());
            labelMaxCheckOutDate.setText(orderModel.getMaxCheckOutDateString());
            buttonOK.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        }
    }

    class ReservationPattern extends NewOrderPattern {

        void addListener() {
            super.addListener();
            buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    NewOrder newOrder = createNewOrder();
                    if (newOrder != null) {
                        if (submitAndComfirmOrder(newOrder)) {
                            JOptionPane.showMessageDialog(getContentPane(), "成功预订！");
                            if (orderListener != null) {
                                orderListener.onNewOrderBorn();
                            }
                            dispose();
                        }
                    }
                }
            });
        }

        private boolean submitAndComfirmOrder(NewOrder newOrder) {
            IReservationBusiness checkInBusiness = BusinessFactory.createReservationBusiness();
            Order order = checkInBusiness.submitOrder(newOrder);
            Log.d("submit order: " + order);
            return checkInBusiness.confirmReservation();
        }

        void initComponent() {
            super.initComponent();
            comboBoxOrderState.setSelectedItem(Order.OrderState.reservation_generate);
            buttonOK.setText("预订");
        }
    }

    class CheckInNewOrderPattern extends NewOrderPattern {
        void addListener() {
            super.addListener();
            buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    NewOrder newOrder = createNewOrder();
                    if (newOrder != null) {
                        if (submitAndComfirmOrder(newOrder)) {
                            JOptionPane.showMessageDialog(getContentPane(), "成功入住！");
                            if (orderListener != null) {
                                orderListener.onNewOrderBorn();
                            }
                            dispose();
                        }
                    }
                }
            });
        }

        private boolean submitAndComfirmOrder(NewOrder newOrder) {
            ICheckInBusinessWithoutReservation checkInBusiness = BusinessFactory.createCheckInBusinessWithoutReservation();
            Order order = checkInBusiness.submitOrder(newOrder);
            Log.d("submit order: " + order);
            return checkInBusiness.checkIn();
        }

        void initComponent() {
            super.initComponent();
            comboBoxOrderState.setSelectedItem(Order.OrderState.checkin_generate);
            buttonOK.setText("入住");
        }
    }

    class CloseOrderPattern {
        Order order;

        public CloseOrderPattern(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }

        void setModel() {
            tableRoomWithRate.setModel(new RoomRateTableModel());

            orderModel = new OrderDialogModel(OrderDialog.this);

            orderModel.setCheckInDateModel(datePickerCheckInTime.getModel());
            orderModel.setCheckOutDateModel(datePickerCheckOutTime.getModel());
            orderModel.setRoomRateModel((RoomRateTableModel) tableRoomWithRate.getModel());

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(order.getGenerateTime().getTime());
            orderModel.setGenerateTime(cal);
            orderModel.setRoom(order.getRoom());
            orderModel.setCustomer(order.getOrderCustomer());
            cal.setTimeInMillis(order.getCheckoutTime().getTime());
            orderModel.setCheckOutTime(cal);
            cal.setTimeInMillis(order.getCheckinTime().getTime());
            orderModel.setCheckInTime(cal);

            comboBoxOrderState.setModel(new OrderStateComboBoxModel());
        }

        void addListener() {
            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }
            });

            // call onNewOrderCancel() when cross is clicked
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onCancel();
                }
            });

        }

        void initComponent() {
            textFieldOrderCode.setText("" + order.getID());
            labelMinCheckInDate.setVisible(false);
            labelMaxCheckOutDate.setVisible(false);
            labelMaxCheckOutDateTag.setVisible(false);
            labelMinCheckInDateTag.setVisible(false);

            buttonAddCustomer.setEnabled(false);

            datePickerCheckInTime.setEnabled(false);
            datePickerCheckOutTime.setEnabled(false);

            comboBoxOrderState.setSelectedItem(order.getOrderState());
            buttonOK.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        }
    }


    class CancelReservationPattern extends CloseOrderPattern {

        public CancelReservationPattern(Order order) {
            super(order);
        }

        @Override
        void addListener() {
            super.addListener();
            buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ICancelOrderBusiness cancelOrderBusiness = BusinessFactory.createCancelOrderBusiness();
                    cancelOrderBusiness.setCancelOrder(order.getID());
                    if (cancelOrderBusiness.confirmCancel()) {
                        JOptionPane.showMessageDialog(getContentPane(), "成功关闭订单");
                        if (orderListener != null) {
                            orderListener.onNewOrderBorn();
                        }
                        dispose();
                    }
                }
            });
        }

        @Override
        void initComponent() {
            super.initComponent();
            buttonOK.setText("关闭订单");
        }
    }

    class CheckOutPattern extends CloseOrderPattern {
        public CheckOutPattern(Order order) {
            super(order);
        }

        @Override
        void setModel() {
            super.setModel();
            orderModel.setCheckOutTime(Calendar.getInstance());
        }

        @Override
        void addListener() {
            super.addListener();
            buttonOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ICheckOutBusiness checkOutBusiness = BusinessFactory.createCheckOutBusiness();
                    checkOutBusiness.setImminentCheckOutOrder(order.getID());
                    checkOutBusiness.charge();
                    if (checkOutBusiness.checkout()) {
                        JOptionPane.showMessageDialog(getContentPane(), "离店成功");
                        if (orderListener != null) {
                            orderListener.onNewOrderBorn();
                        }
                        dispose();
                    }
                }
            });
        }

        @Override
        void initComponent() {
            super.initComponent();

            if (isCheckOutTimeAheadOfSchedule()) {
                labelMaxCheckOutDateTag.setText("提前离店!");
                labelMaxCheckOutDateTag.setVisible(true);
            }
            buttonOK.setText("离店");
        }

        private boolean isCheckOutTimeAheadOfSchedule() {
            long curr = orderModel.getCheckOutTime().getTimeInMillis();
            long plan = order.getCheckoutTime().getTime();
            if (plan > curr) {
                if (((double) plan - curr) / (24 * 60 * 60 * 1000) >= 1) {
                    return true;
                }
            }
            return false;
        }
    }

}
