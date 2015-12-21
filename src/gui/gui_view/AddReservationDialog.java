package gui.gui_view;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.*;

public class AddReservationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldOrderCode;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField7;
    private JTextField textField8;
    private JButton button1;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTable table1;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JComboBox comboBox1;
    private JTable table2;
    private JTextField textField1;
    private JTextField textField2;
    private JDatePickerImpl JDatePickerImpl1;
    private JDatePickerImpl JDatePickerImpl2;

    public AddReservationDialog() {
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
        AddReservationDialog dialog = new AddReservationDialog();
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

    private void createUIComponents() {
        JDateComponentFactory dateComponentFactory = new JDateComponentFactory();
        JDatePickerImpl1 = (JDatePickerImpl) dateComponentFactory.createJDatePicker();
        JDatePickerImpl2 = (JDatePickerImpl) dateComponentFactory.createJDatePicker();
        JDatePickerImpl2.getModel().addDay(1);
    }
}
