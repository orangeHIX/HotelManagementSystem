package gui.view;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.event.*;

public class SearchRoomDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton buttonReset;
    private JTable table1;
    private JSpinner spinnerPeopleNum;
    private JTextField textFieldFilter;
    private JDatePickerImpl JDatePickerImpl1;
    private JDatePickerImpl JDatePickerImpl2;

    public SearchRoomDialog() {
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

// call onNewOrderCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onNewOrderCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        SearchRoomDialog dialog = new SearchRoomDialog();
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
