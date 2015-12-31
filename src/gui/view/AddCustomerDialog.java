package gui.view;

import entity.Customer;
import persistent.impl.CustomerManager;
import utils.Log;

import javax.swing.*;
import java.awt.event.*;

public class AddCustomerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    //ConnectionSQL con = new ConnectionSQL();
    Customer customer;
    CustomerManager cm;

    private AddNewCustomerListener listener;

    public AddCustomerDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        customer = new Customer();
        cm = new CustomerManager();

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

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void setAddNewCustomerListener(AddNewCustomerListener addNewCustomerListener) {
        this.listener = addNewCustomerListener;
    }

    private void onOK() {

        String name = textField1.getText().trim();
        if (name != null && !name.isEmpty()) {
            String cid = textField2.getText().trim();
            String phone_num = textField3.getText().trim();
            customer.setIDNumber(cid);
            customer.setName(name);
            customer.setPhoneNumber(phone_num);
            Long b = cm.addNewCustomer(customer);
            if (b == null) {
                JOptionPane.showMessageDialog(getContentPane(), "新顾客未添加成功");
            }
            if (listener != null) {
                customer.setID(b);
                listener.newCustmerAdded(customer);
                dispose();
                //System.out.println(b);
            }
        }

    }

    private void onCancel() {
// add your code here if necessary
        Log.d("cancel");
        dispose();
    }

    public static void main(String[] args) {
        AddCustomerDialog dialog = new AddCustomerDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
