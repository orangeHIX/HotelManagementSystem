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

    public static final Color pink =  new Color(240,128,128);
    public static final Color sky_blue =new Color(100,149,237);
    public static final Color orange  = new Color(255,140,0);
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
                Order order = ((Order) value);
                setBackground(getOrderColor(order.getOrderState()));
                setText("<html><center>" + order.getID() +
                        "<br>" + order.getOrderState().getName() +
                        "<br>" + order.getAccommodateCustomer().getName() +
                        "</center></html>");
            } else if (value instanceof Room) {
                setBackground(pink);
                setText("<html>" + ((Room) value).getRoomNo() + "<br>" + ((Room) value).getRoomType().getTypeName() + "</html>");
            }

        } else {
            setBackground(Color.LIGHT_GRAY);
            setText("");
        }

        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            setBorder(null);
        }

        return this;
    }

    private Color getOrderColor(Order.OrderState state){
        switch (state){
            case checkin_confirm:
                return orange;
            case reservation_confirm:
                return sky_blue;
        }
        return null;
    }


}
