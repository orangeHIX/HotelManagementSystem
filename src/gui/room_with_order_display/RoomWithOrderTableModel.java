package gui.room_with_order_display;

import business.entity.RoomInfo;
import business.entity.RoomWithOrder;
import business.inter.IRoomWithOrderDisplay;
import business.utils.DateConvertor;
import entity.Order;
import entity.Room;
import utils.Log;

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
//    private SimpleDateFormat dateFormatWeek = null;

    private IRoomWithOrderDisplay roomWithOrderDisplay;

    public RoomWithOrderTableModel(IRoomWithOrderDisplay roomWithOrderDisplay) {
        dateFormat = new SimpleDateFormat("yyyy.MM.dd");
//        dateFormatWeek = new SimpleDateFormat("E");
        this.roomWithOrderDisplay = roomWithOrderDisplay;
    }

    public void setData(Calendar startDate, Calendar endDate) {
        setData(startDate, endDate, new RoomInfo());
    }

    public void setData(Calendar startDate, Calendar endDate, RoomInfo roomInfo) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomInfo = roomInfo;
        //this.roomInfo.setRoomNo("0106");
        updateTitle();
        loadRoomWithOrder();
        fireTableStructureChanged();
    }

    private void updateTitle() {
        long diff = endDate.getTimeInMillis() - startDate.getTimeInMillis();
        long diffDays = diff / (24L * 60 * 60 * 1000);
        title = new String[(int) diffDays + 1];
        for (int i = 0; i < title.length; i++) {
            title[i] = getTitleHtmlString(getDateAt(i + 1).getTime());
        }
    }

    private void loadRoomWithOrder() {

        roomWithOrderDisplay.setRoomInfoFilterConditions(roomInfo);
        roomWithOrderDisplay.setTimeFilterConditions(getStartTime(), getEndTime());
        Log.d("" + dateFormat.format(getStartTime().getTime()) + "," + dateFormat.format(getEndTime().getTime()));
        content = roomWithOrderDisplay.searchRoomWithOrder();

    }

    private Calendar getStartTime() {
        return DateConvertor.toCheckInTime(startDate);
    }

    private Calendar getEndTime() {
        endDate.add(Calendar.DATE, 1);
        Calendar endTime = DateConvertor.toCheckOutTime(endDate);
        endDate.add(Calendar.DATE, -1);
        return endTime;
    }

    public Calendar getDateAt(int col) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startDate.getTimeInMillis());
        cal.add(Calendar.DATE, col - 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    private String getTitleHtmlString(Date date) {
        return "<html>" + dateFormat.format(date)
                /*+dateFormatWeek.format(date)*/
                + "</html>";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex < 1) {
            return Room.class;
        } else {
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
        if (isValidRowAndCol(rowIndex, columnIndex) && content != null) {
            if (columnIndex < 1) {
                //Log.d(""+content.get(rowIndex).getRoom());
                return content.get(rowIndex).getRoom();
            } else {
                return findOrder(content.get(rowIndex), getDateAt(columnIndex));
            }
        }
        return null;
    }

    private Order findOrder(RoomWithOrder roomWithOrder, Calendar startOfDay) {
        //System.out.println("findOrder"+startOfDay.get(Calendar.DATE));
        Calendar endOfDay = Calendar.getInstance();
        endOfDay.setTimeInMillis(startOfDay.getTimeInMillis());
        endOfDay.add(Calendar.DATE, 1);

        startOfDay = DateConvertor.toCheckInTime(startOfDay);
        endOfDay = DateConvertor.toCheckOutTime(endOfDay);

        for (Order o : roomWithOrder.getOrderList()) {
            if ( !
                    (o.getCheckoutTime().getTime() < startOfDay.getTimeInMillis()
                            || o.getCheckinTime().getTime() > endOfDay.getTimeInMillis())) {
                return o;
            }
        }
        return null;
    }

    private boolean isValidRowAndCol(int row, int col) {
        return isValidRowIndex(row) && isValidColIndex(col);
    }

    private boolean isValidRowIndex(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            return true;
        }
        return false;
    }

    private boolean isValidColIndex(int colIndex) {
        if (colIndex >= 0 && colIndex < getColumnCount()) {
            return true;
        }
        return false;
    }

    public Room getRoomAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < getRowCount()) {
            return content.get(rowIndex).getRoom();
        }
        return null;
    }

    public Calendar getMinCheckInTime(int rowIndex, int colIndex) {
        if (getValueAt(rowIndex, colIndex) != null || !isValidRowAndCol(rowIndex, colIndex)) {
            return null;
        }
        for (int i = colIndex; i >= 0; i--) {
            Object o = getValueAt(rowIndex, i);
            if (o != null) {
                Calendar minCheckInTime = DateConvertor.toCheckInTime(getDateAt(i + 1));
                //Log.d("getMinCheckInTime: "+dateFormat.format(minCheckInTime.getTime()));
                return minCheckInTime;
            }
        }
        return null;
    }

    public Calendar getMaxCheckOutTime(int rowIndex, int colIndex) {
        if (getValueAt(rowIndex, colIndex) != null || !isValidRowAndCol(rowIndex, colIndex)) {
            return null;
        }
        int i = colIndex;
        for (; i < getColumnCount(); i++) {
            Object o = getValueAt(rowIndex, i);
            if (o != null) {
                Calendar maxCheckOutTime = DateConvertor.toCheckOutTime(getDateAt(i));
                //Log.d("getMaxCheckOutTime: "+dateFormat.format(maxCheckOutTime.getTime()));
                return maxCheckOutTime;
            }
        }
        return DateConvertor.toCheckOutTime(getDateAt(i));
    }
}
