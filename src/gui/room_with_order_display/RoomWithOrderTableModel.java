package gui.room_with_order_display;

import business.entity.RoomInfo;
import business.entity.RoomWithOrder;
import business.inter.IRoomWithOrderDisplay;
import business.utils.DateConvertor;
import entity.Order;
import entity.Room;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hyx on 2015/12/23.
 */
public class RoomWithOrderTableModel extends AbstractTableModel {
    private Calendar startDate;
    private Calendar endDate;
    private RoomInfo roomInfo;
    private List<RoomWithOrder> content = null;

    String[] title;
    private SimpleDateFormat dateFormat = null;
    private SimpleDateFormat dateFormatWeek = null;

    private IRoomWithOrderDisplay roomWithOrderDisplay;

    public RoomWithOrderTableModel(IRoomWithOrderDisplay roomWithOrderDisplay) {
        dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        dateFormatWeek = new SimpleDateFormat("E");
        this.roomWithOrderDisplay = roomWithOrderDisplay;
    }

    public void setData(Calendar startDate, Calendar endDate) {
        setData(startDate, endDate, new RoomInfo());
    }

    public void setData(Calendar startDate, Calendar endDate, RoomInfo roomInfo) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomInfo = roomInfo;
        updateTitle();
        loadRoomWithOrder();
        fireTableStructureChanged();
    }

    private void updateTitle() {
        long diff = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        long diffDays = diff / (24L * 60 * 60 * 1000);
        title = new String[(int) diffDays + 1];
        for (int i = 0; i < title.length; i++) {
            title[i] = getTitleHtmlString(getDate(i + 1).getTime());
        }
    }

    private void loadRoomWithOrder() {

        roomWithOrderDisplay.setRoomInfoFilterConditions(roomInfo);
        roomWithOrderDisplay.setTimeFilterConditions(getStartTime(), getEndTime());
        content = roomWithOrderDisplay.searchRoomWithOrder();

    }

    private Calendar getStartTime() {
        return DateConvertor.convertDateToStandardCheckInDateTime(startDate);
    }

    private Calendar getEndTime() {
        endDate.add(Calendar.DATE, 1);
        Calendar endTime = DateConvertor.convertDateToStandardCheckOutDateTime(endDate);
        endDate.add(Calendar.DATE, -1);
        return endTime;
    }

    private Calendar getDate(int col) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startDate.getTimeInMillis());
        cal.add(Calendar.DATE, col - 1);
        return cal;
    }

    private String getTitleHtmlString(Date date) {
        return "<html>" + dateFormat.format(date)
                /*+dateFormatWeek.format(date)*/
                + "</html>";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex < 1){
            return Room.class;
        }else {
            return Order.class;
        }
    }

    @Override
    public String getColumnName(int col) {
        if (col < 1) {
            return "·¿¼ä";
        } else {
            return title[col - 1];
        }
    }

    @Override
    public int getRowCount() {
        if (content != null) {
            return content.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (content != null) {
            return title.length + 1;
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (content != null) {
            if (columnIndex < 1) {
                //Log.d(""+content.get(rowIndex).geRoom());
                return content.get(rowIndex).geRoom();
            } else {
                return findOrder(content.get(rowIndex), getDate(columnIndex));
            }
        }
        return null;
    }

    private Order findOrder(RoomWithOrder roomWithOrder, Calendar date) {
        //System.out.println("findOrder"+date.get(Calendar.DATE));
        date.add(Calendar.DATE, 1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        for (Order o : roomWithOrder.getOrderList()) {
            if (o.getCheckinTime().getTime() <= date.getTimeInMillis()
                    && date.getTimeInMillis() <= o.getCheckoutTime().getTime()) {
                return o;
            }
        }
        return null;
    }
}
