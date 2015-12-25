package gui.room_with_order_display;

import entity.Order;
import entity.Room;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by hyx on 2015/12/24.
 */
public class RoomWithOrderCellRender extends JLabel implements TableCellRenderer {

    public RoomWithOrderCellRender() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {


        //setHorizontalTextPosition(CENTER);
        setHorizontalAlignment(CENTER);
        //setText("<html><b><u>T</u>wo</b><br>lines</html>");
        if (value != null) {
            if (value instanceof Order) {
                setBackground(Color.CYAN);
                Order order =((Order) value);
                setText("<html>"+order.getID()+"<br>"+order.getAccommodateCustomer().getName()+"</html>");
            } else if (value instanceof Room) {
                setBackground(Color.PINK);
                setText("<html>"+((Room) value).getRoomNo()+"<br>"+((Room) value).getRoomType().getTypeName()+"</html>");
            }

            if (hasFocus) {
                setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            } else {
                setBorder(null);
            }
        }else{
            setBackground(null);
            setText("");
        }


//        int height = new Double(this.getPreferredSize().getHeight()).intValue();
//        if (table.getRowHeight() < height) {
//            table.setRowHeight(row, height);
//        }

        return this;
    }


}
