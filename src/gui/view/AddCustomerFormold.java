package gui.view;

import entity.Customer;

import javax.swing.*;

/**
 * Created by hyx on 2015/12/11.
 */
public class AddCustomerFormold {
    private JTextField JTextFieldName;
    private JTextField textField2;
    private JPanel JPanelAddCustomer;
    private JPanel JPanelCommonInformation;
    private JPanel JPanelContactInformation;
    private JLabel JLabelName;
    private JLabel JLabelPhone;
    private JTextField JTextFieldIDNumber;
    private JLabel JLabelIDNumber;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddCustomerFormold");
        frame.setContentPane(new AddCustomerFormold().JPanelAddCustomer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void setData(Customer data) {
        JTextFieldName.setText(data.getName());
        textField2.setText(data.getPhoneNumber());
    }

    public void getData(Customer data) {
        data.setName(JTextFieldName.getText());
        data.setPhoneNumber(textField2.getText());
    }

    public boolean isModified(Customer data) {
        if (JTextFieldName.getText() != null ? !JTextFieldName.getText().equals(data.getName()) : data.getName() != null)
            return true;
        if (textField2.getText() != null ? !textField2.getText().equals(data.getPhoneNumber()) : data.getPhoneNumber() != null)
            return true;
        return false;
    }
}
