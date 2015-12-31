package gui.new_order;

import entity.Room;
import entity.RoomType;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hyx on 2015/12/26.
 */
public class RoomRateTableModel extends AbstractTableModel {

    public static final String[] titles = {"日期", "房价(￥)"};
    private Room room;
    private SimpleDateFormat dateFormat;
    private DecimalFormat decimalFormat;

    private Calendar checkInDate;
    private Calendar checkOutDate;
    List<Date> datelist;

    public RoomRateTableModel() {
        super();
        dateFormat = new SimpleDateFormat("yyyy.MM.dd E");
        decimalFormat = new DecimalFormat("###,##0.00");
        datelist = new ArrayList<>();
        RoomType roomType = new RoomType();
        roomType.setTypeName("商务间");
        roomType.setPricePerNight(20000);
        Room room = new Room();
        room.setRoomNo("0102");
        room.setRoomType(roomType);
        setData(room, Calendar.getInstance(), Calendar.getInstance());
        //dateFormatWeek = new SimpleDateFormat("E");
    }

    public void setRoom(Room room) {
        this.room = room;
        fireTableDataChanged();
    }

    public void setcheckInDate(Calendar checkInDate) {
        this.checkInDate = checkInDate;
        updateDateList();
        //Log.d("fire setCheckInDate " + dateFormat.format(checkInDate.getTime()));
        fireTableDataChanged();
    }

    public void setCheckOutDate(Calendar checkOutDate) {
        this.checkOutDate = checkOutDate;
        updateDateList();
        //Log.d("fire setCheckOutDate " + dateFormat.format(checkOutDate.getTime()));
        fireTableDataChanged();
    }

    public void setData(Room room, Calendar checkInDate, Calendar checkOutDate) {
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        updateDateList();
        fireTableDataChanged();
    }

    private void updateDateList() {
        if (checkInDate != null && checkOutDate != null
                && checkInDate.compareTo(checkOutDate) < 0) {
            datelist.clear();
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(checkInDate.getTimeInMillis());
            for (;
                 c.compareTo(checkOutDate) < 0;
                 c.add(Calendar.DATE, 1)) {
                datelist.add(c.getTime());
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return titles[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Date.class;
        } else if (columnIndex == 1) {
            return Integer.class;
        }
        return null;
    }

    @Override
    public int getRowCount() {
        return datelist.size();
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            //System.out.println(datelist.get(rowIndex));
            return datelist.get(rowIndex);
        } else if (columnIndex == 1 && room != null && room.getRoomType() != null) {
            NumberFormat numberFormat = new DecimalFormat();
            return room.getRoomType().getPricePerNight();
        }

        return null;
    }

    public int getTotalPrice() {
        if (room != null) {
            RoomType roomType = room.getRoomType();
            if (roomType != null) {
                int rowCount = getRowCount();
                int total = 0;
                //datelist.stream().forEach(System.out::println);
                for (int i = 0; i < rowCount; i++) {
                    total += (Integer) getValueAt(i, 1);
                }
                //Log.d("total"+total);
                return total;
            }
        }
        return 0;
    }

    public int getDeposit() {
        return 0;
    }
}
