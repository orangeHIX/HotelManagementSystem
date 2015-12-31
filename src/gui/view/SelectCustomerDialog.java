package gui.view;

import entity.Customer;
import persistent.EntityManagerFactory;
import persistent.IQueryCustomer;
import utils.Constant;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SelectCustomerDialog extends JDialog implements AddNewCustomerListener {
    private JPanel contentPane;
    private JButton buttonSelect;
    private JButton buttonCancel;
    private JTextField textName;
    private JTable table;
    private JButton addCustomerButton;
    private JScrollPane scrollPane;
    private ResultSetTableModel model;
    private ResultSetTableModel tableModel;

    private AddCustomerIntoNewOrderListener listener;

    public SelectCustomerDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSelect);

        setModel();
        addListener();
        updateTable();
    }

    public void setAddCustomerIntoNewOrderListener(AddCustomerIntoNewOrderListener listener) {
        this.listener = listener;
    }

    private void addListener() {


        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomerDialog dialog = new AddCustomerDialog();
                dialog.setAddNewCustomerListener(SelectCustomerDialog.this);
                dialog.setTitle("选择入住人");
                Dimension size = new Dimension(
                        (int) (Constant.screenSize.getWidth() * 2 / 7),
                        (int) (Constant.screenSize.getHeight() * 2 / 5));
                dialog.setSize(size);
                dialog.setLocationByPlatform(true);
                dialog.setVisible(true);
            }
        });
        buttonSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onselect();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        Document document = textName.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
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

    private void setModel() {
        tableModel = new ResultSetTableModel();
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void updateTable() {
        tableModel.setPartName(textName.getText().trim());
        if (table.getRowCount() > 0) {
            table.setRowSelectionInterval(0, 0);
        }
    }
    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void onselect() {
        if (listener != null) {
            int row = table.getSelectedRow();
            Customer customer = tableModel.getCustomerAt(row);
            listener.addCustomer(customer);
        }
        dispose();
    }

    public static void main(String[] args) {
        SelectCustomerDialog dialog = new SelectCustomerDialog();
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);

        System.exit(0);
    }

    private void createUIComponents() {
        table = new JTable();
        table.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public void newCustmerAdded(Customer customer) {
        if (customer != null) {
            textName.setText(customer.getName());
        }
    }

    class ResultSetTableModel extends AbstractTableModel {
        private IQueryCustomer queryCustomer;
        private List<Customer> customerList;
        private String partName;

        private final String[] titles = {"姓名", "电话", "身份证号"};

        //构造器，初始化rs和rsmd两个属性
        public ResultSetTableModel() {
            queryCustomer = EntityManagerFactory.createQueryCustomer();
        }

        public void setPartName(String partOfName) {
            this.partName = partOfName;
            update();
        }

        public void update() {
            customerList = queryCustomer.queryCustomerByName(partName);
            fireTableDataChanged();
        }

        @Override
        public String getColumnName(int c) {
            return titles[c];
        }

        @Override
        public int getColumnCount() {
            return titles.length;
        }

        @Override
        public Object getValueAt(int r, int c) {
            Customer customer = customerList.get(r);
            switch (c) {
                case 0:
                    return customer.getName();
                case 2:
                    return customer.getIDNumber();
                case 1:
                    return customer.getPhoneNumber();
                default:
                    return null;
            }
        }

        public Customer getCustomerAt(int row) {
            return customerList.get(row);
        }

        @Override
        public int getRowCount() {
            if (customerList != null) {
                return customerList.size();
            }
            return 0;
        }
    }
}

