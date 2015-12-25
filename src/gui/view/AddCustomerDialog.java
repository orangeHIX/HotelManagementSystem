package gui.view;

import entity.Customer;

import javax.swing.*;
import java.awt.event.*;

public class AddCustomerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelCommonInfor;
    private JPanel panelContactInfor;
    private JTextField textFieldName;
    private JTextField textFieldID;
    private JTextField textFieldPhone;

    public AddCustomerDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

    public static void main(String[] args) {
        AddCustomerDialog dialog = new AddCustomerDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void setData(Customer data) {
        textFieldName.setText(data.getName());
        textFieldID.setText(data.getIDNumber());
        textFieldPhone.setText(data.getPhoneNumber());
    }

    public void getData(Customer data) {
        data.setName(textFieldName.getText());
        data.setIDNumber(textFieldID.getText());
        data.setPhoneNumber(textFieldPhone.getText());
    }

    public boolean isModified(Customer data) {
        if (textFieldName.getText() != null ? !textFieldName.getText().equals(data.getName()) : data.getName() != null)
            return true;
        if (textFieldID.getText() != null ? !textFieldID.getText().equals(data.getIDNumber()) : data.getIDNumber() != null)
            return true;
        if (textFieldPhone.getText() != null ? !textFieldPhone.getText().equals(data.getPhoneNumber()) : data.getPhoneNumber() != null)
            return true;
        return false;
    }
}
