package gui.view;

import gui.add_new_order.AddNewOrderModel;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.*;

public class AddNewOrderDialog extends JDialog {
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
    private JButton buttonReset;
    private JButton buttonAddCustomer;
    private JEditorPane editorNote;


    public AddNewOrderDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        addListener();

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
    private void setModel() {
        AddNewOrderModel addNewOrderModel = new AddNewOrderModel();


    }

    private void addListener() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

    }

    private void initComponent() {

    }

    private void createUIComponents() {
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();
        datePickerCheckInTime = (JDatePickerImpl) dateComponentFactory.createJDatePicker();
        datePickerCheckOutTime = (JDatePickerImpl) dateComponentFactory.createJDatePicker();
    }


    public static void main(String[] args) {
        AddNewOrderDialog dialog = new AddNewOrderDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
