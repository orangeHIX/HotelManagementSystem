package gui.view;

import javax.swing.*;

/**
 * Created by hyx on 2015/12/11.
 */
public class MainForm {
    private JButton buttonOrderCenter;
    private JButton buttonRoomCenter;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JTable table1;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button9;
    private JPanel panelRoot;
    private JPanel Home;
    private JButton button1;
    private JButton button2;
    private JButton button10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().panelRoot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
