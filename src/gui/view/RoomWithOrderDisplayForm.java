package gui.view;

import business.DisplayFactory;
import entity.Order;
import entity.Room;
import gui.ViewUtils;
import gui.room_with_order_display.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.Constant;
import utils.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

/**
 * Created by hyx on 2015/12/21.
 */
public class RoomWithOrderDisplayForm implements OrderChangeListener {

    private JPanel panel1;
    private JTable tableRoomWithOrder;
    private JComboBox comboBoxRoomType;
    private JDatePickerImpl datePickerStartDate;
    private JDatePickerImpl datePickerEndDate;
    private JComboBox comboBoxPeriod;
    private JButton buttonSearch;
    private JFormattedTextField formattedTextFieldRoomNo;
    private JScrollPane scrollPane1;

    private RoomWithOrderSearchModel searchModel;
    private RoomWithOrderTableModel tableModel;
    //private IRoomWithOrderDisplay roomWithOrderDisplay;

    public RoomWithOrderDisplayForm() {
        Log.d("init");
        setModel();
        addListener();
        initComponent();
    }

    private void setModel() {
        ComboBoxModel peroidModel = new DefaultComboBoxModel(Period.values());
        RoomTypeModel roomTypeModel = new RoomTypeModel();

        comboBoxPeriod.setModel(peroidModel);
        comboBoxRoomType.setModel(roomTypeModel);

        searchModel = new RoomWithOrderSearchModel(
                peroidModel,
                datePickerStartDate.getModel(),
                datePickerEndDate.getModel(),
                formattedTextFieldRoomNo,
                roomTypeModel);

        tableModel = new RoomWithOrderTableModel(DisplayFactory.createRoomWithOrderDisplay());
        //tableModel.setData(searchModel.getStartDate(), searchModel.getEndDate());
        //tableRoomWithOrder.setModel(dispalyModel.getTableModel());
        tableRoomWithOrder.setModel(tableModel);


    }

    private void addListener() {
        comboBoxPeriod.addActionListener(e -> {
            searchModel.changeStartDateModel();
            searchModel.changeEndDateModel();
        });


        datePickerStartDate.addActionListener(
                e -> searchModel.checkStartDateModel());
        datePickerEndDate.addActionListener(
                e -> searchModel.checkEndDateModel());

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableView();
            }
        });
        buttonSearch.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSearch.requestFocus();
                updateTableView();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        buttonSearch.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSearch.requestFocus();
                updateTableView();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        tableRoomWithOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tableRoomWithOrder.getSelectedRow();
                    int col = tableRoomWithOrder.getSelectedColumn();

                    if (tableRoomWithOrder.isCellSelected(row, col)) {
                        Object o = tableRoomWithOrder.getValueAt(row, col);
                        if (o == null) {
                            fireNewOrderDialog();
                        } else if (o instanceof Order) {
                            fireOldOrderDialog((Order) o);
                        }
                    }
                }
            }
        });

    }


    private void initComponent() {
        comboBoxPeriod.setSelectedItem(Period.in_10_days);
        //comboBoxPeriod.setSelectedIndex(comboBoxPeriod.getItemCount() - 1);
        comboBoxRoomType.setSelectedIndex(comboBoxRoomType.getItemCount() - 1);

        tableModel.setData(searchModel.getStartDate(), searchModel.getEndDate(), searchModel.getRoomInfo());
        //searchModel.set\\\
    }


    private void createUIComponents() {
        Log.d("create");
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();

        datePickerStartDate = (JDatePickerImpl) dateComponentFactory.createJDatePicker();

        datePickerEndDate = (JDatePickerImpl) dateComponentFactory.createJDatePicker();

        tableRoomWithOrder = new JTable() {
            @Override
            public String getToolTipText(MouseEvent event) {
                java.awt.Point p = event.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int ealColumnIndex = convertColumnIndexToModel(colIndex);
                int ealRowIndex = convertRowIndexToModel(rowIndex);
                Object o = getModel().getValueAt(ealRowIndex, ealColumnIndex);
                if (o instanceof Order) {
                    String in = ViewUtils.getDateFormat_yyyy_MM_dd_E().format(((Order) o).getCheckinTime().getTime());
                    String out = ViewUtils.getDateFormat_yyyy_MM_dd_E().format(((Order) o).getCheckoutTime().getTime());
                    return "入住时间" + in + ", 离店时间" + out;
                } else
                    return super.getToolTipText(event);
            }
        };
        tableRoomWithOrder.setDefaultRenderer(Room.class, new RoomWithOrderCellRender());
        tableRoomWithOrder.setDefaultRenderer(Order.class, new RoomWithOrderCellRender());
        tableRoomWithOrder.getTableHeader().setReorderingAllowed(false);
        tableRoomWithOrder.getTableHeader().setResizingAllowed(false);
        tableRoomWithOrder.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableRoomWithOrder.setRowHeight(100);

        buttonSearch = new JButton();
        buttonSearch.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        //tableRoomWithOrder.setCellEditor(new RoomWithOrderTableCellEditor());
        //tableRoomWithOrder = new JTable();

    }


    private void fireNewOrderDialog() {
        int row = tableRoomWithOrder.getSelectedRow();
        int col = tableRoomWithOrder.getSelectedColumn();

        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
//        Log.d("fireNewOrderDialog select", tableModel.getDateAt(col));
//        Log.d("fireNewOrderDialog now", now);
        if (tableModel.getDateAt(col).compareTo(now) >= 0) {
            OrderDialog dialog = new OrderDialog(
                    Calendar.getInstance(),
                    tableModel.getRoomAt(row),
                    tableModel.getMinCheckInTime(row, col),
                    tableModel.getMaxCheckOutTime(row, col),
                    tableModel.getDateAt(col));
            configureAndShowOrderDialog(dialog, "新订单");
        } else {
            JOptionPane.showMessageDialog(getContentPane(), "不能选择过去的日期入住");
        }
    }

    private void fireOldOrderDialog(Order order) {
        //new OrderDialog.ReservationPattern();
        if (order != null
                && (order.getOrderState().equals(Order.OrderState.reservation_confirm)
                || order.getOrderState().equals(Order.OrderState.checkin_confirm))) {
            OrderDialog dialog = new OrderDialog(order);
            configureAndShowOrderDialog(dialog, "已有订单");
        }
    }

    private void configureAndShowOrderDialog(OrderDialog dialog, String title) {
        dialog.setOrderChangeListener(this);
        dialog.setTitle(title);
        Dimension size = new Dimension(
                (int) (Constant.screenSize.getWidth() * 3 / 7),
                (int) (Constant.screenSize.getHeight() * 3 / 5));
        dialog.setSize(size);
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
    }

    @Override
    public void onNewOrderBorn() {
        Log.d("onNewOrderBorn");
        updateTableView();
    }

    @Override
    public void onNewOrderCancel() {
        Log.d("onNewOrderCancel");
    }

    private void updateTableView() {
        tableModel.setData(searchModel.getStartDate(), searchModel.getEndDate(), searchModel.getRoomInfo());
    }

    public Container getContentPane() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RoomWithOrderDisplayForm");
        frame.setContentPane(new RoomWithOrderDisplayForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
