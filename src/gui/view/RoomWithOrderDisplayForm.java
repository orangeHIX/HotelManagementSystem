package gui.view;

import business.DisplayFactory;
import business.inter.IRoomWithOrderDisplay;
import entity.Order;
import entity.Room;
import gui.room_with_order_display.*;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.Constant;
import utils.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by hyx on 2015/12/21.
 */
public class RoomWithOrderDisplayForm {

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
    private IRoomWithOrderDisplay roomWithOrderDisplay;

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
                tableModel.setData(searchModel.getStartDate(), searchModel.getEndDate(), searchModel.getRoomInfo());
                //tableRoomWithOrder.updateUI();
            }
        });

        tableRoomWithOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    if(tableRoomWithOrder.isCellSelected(tableRoomWithOrder.getSelectedRow(),tableRoomWithOrder.getSelectedColumn())){
                        Dialog dialog = new AddNewOrderDialog();
                        Dimension size = new Dimension((int)(Constant.screenSize.getWidth()/3),(int)(Constant.screenSize.getHeight()/2));
                        dialog.setSize(size);
                        dialog.setVisible(true);
                    }
                }
            }
        });

    }

    private void initComponent() {
        comboBoxPeriod.setSelectedIndex(comboBoxPeriod.getItemCount() - 1);
        comboBoxRoomType.setSelectedIndex(comboBoxRoomType.getItemCount() - 1);

        tableModel.setData(searchModel.getStartDate(), searchModel.getEndDate(), searchModel.getRoomInfo());
    }



    private void createUIComponents() {
        Log.d("create");
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();

        datePickerStartDate = (JDatePickerImpl) dateComponentFactory.createJDatePicker();

        datePickerEndDate = (JDatePickerImpl) dateComponentFactory.createJDatePicker();

        tableRoomWithOrder = new JTable();
        tableRoomWithOrder.setDefaultRenderer(Room.class, new RoomWithOrderCellRender());
        tableRoomWithOrder.setDefaultRenderer(Order.class, new RoomWithOrderCellRender());
        tableRoomWithOrder.getTableHeader().setReorderingAllowed(false);
        tableRoomWithOrder.getTableHeader().setResizingAllowed(false);
        tableRoomWithOrder.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //关闭表格列自动调整，此时水平滚动条可见
        tableRoomWithOrder.setRowHeight(100);
        tableRoomWithOrder.setCellEditor(new RoomWithOrderTableCellEditor());
        //tableRoomWithOrder = new JTable();

    }

    public Container getContentPane(){
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
