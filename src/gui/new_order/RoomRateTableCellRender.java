package gui.new_order;

import gui.ViewUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyx on 2015/12/27.
 */
public class RoomRateTableCellRender extends JLabel implements TableCellRenderer {

    private SimpleDateFormat dateFormat;
    private DecimalFormat decimalFormat;
    public RoomRateTableCellRender() {
        super();
        setOpaque(true);
        setHorizontalAlignment(LEADING);
        setBackground(Color.WHITE);
        decimalFormat = ViewUtils.getMoneyDecimalFormat();
        dateFormat = ViewUtils.getDateFormat_yyyy_MM_dd_E();
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        //setText("<html><b><u>T</u>wo</b><br>lines</html>");
        if (value != null) {
            if (value instanceof Date) {
                setText(dateFormat.format(((Date)value)));
            } else if (value instanceof Integer) {
                setText(decimalFormat.format(new Double((Integer)value)/100));
            }

        }else{
            setText("");
        }

        return this;
    }
}
