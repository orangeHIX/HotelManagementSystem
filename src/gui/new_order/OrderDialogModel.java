package gui.new_order;

import business.utils.DateConvertor;
import entity.Customer;
import entity.Room;
import org.jdatepicker.DateModel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hyx on 2015/12/25.
 */
public class OrderDialogModel {


    private Calendar generateTime;
    private Room room;

    private Calendar minCheckInTime;
    private Calendar maxCheckOutTime;

    private Customer customer;

    private DateModel checkInDateModel;
    private DateModel checkOutDateModel;

    private RoomRateTableModel roomRateModel;

    private OrderModelChangeListener modelChangeListener;
    private SimpleDateFormat dateFormat;


    public OrderDialogModel(OrderModelChangeListener modelChangeListener) {
        this.modelChangeListener = modelChangeListener;
        dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    }

    public void setModelChangeListener(OrderModelChangeListener modelChangeListener) {
        this.modelChangeListener = modelChangeListener;
    }

    public void setCheckInDateModel(DateModel checkInDateModel) {
        this.checkInDateModel = checkInDateModel;
        this.checkInDateModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                checkCheckInDate();
                roomRateModel.setcheckInDate(getCheckInDate());
                modelChangeListener.onCheckInAndOutTimeChanged();
            }
        });
    }

    public void setCheckOutDateModel(DateModel checkOutDateModel) {
        this.checkOutDateModel = checkOutDateModel;
        this.checkOutDateModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                checkCheckOutDate();
                roomRateModel.setCheckOutDate(getCheckOutDate());
                modelChangeListener.onCheckInAndOutTimeChanged();
            }
        });
    }

    public void setRoomRateModel(RoomRateTableModel roomRateModel) {
        this.roomRateModel = roomRateModel;
    }

    public void setGenerateTime(Calendar generateTime) {
        this.generateTime = generateTime;
        modelChangeListener.onBasicInfoChanged();
    }

    public void setCheckInTime(Calendar checkInTime) {
        //Log.d("setCheckInTime " + dateFormat.format(checkInTime.getTime()));
        this.checkInDateModel.setDate(
                checkInTime.get(Calendar.YEAR),
                checkInTime.get(Calendar.MONTH),
                checkInTime.get(Calendar.DATE));
    }

    public void setCheckOutTime(Calendar checkOutTime) {
        //Log.d("setCheckOutTime " + dateFormat.format(checkOutTime.getTime()));
        this.checkOutDateModel.setDate(
                checkOutTime.get(Calendar.YEAR),
                checkOutTime.get(Calendar.MONTH),
                checkOutTime.get(Calendar.DATE));

    }

    public void checkCheckInDate() {
        Calendar checkInDate = getCheckInDate();
        if (minCheckInTime != null && DateConvertor.toCheckInTime(checkInDate).compareTo(minCheckInTime) < 0) {
            setCheckInTime(minCheckInTime);
        }
        Calendar checkOutDate = getCheckOutDate();
        if (checkInDate.compareTo(checkOutDate) >= 0) {
            checkOutDate.add(Calendar.DATE, -1);
            setCheckInTime(checkOutDate);
        }
    }

    public void checkCheckOutDate() {
        Calendar checkInDate = getCheckInDate();
        Calendar checkOutDate = getCheckOutDate();
        if (maxCheckOutTime != null && DateConvertor.toCheckOutTime(checkOutDate).compareTo(maxCheckOutTime) > 0) {
            setCheckOutTime(maxCheckOutTime);
        }
        if (checkInDate.compareTo(checkOutDate) >= 0) {
            checkInDate.add(Calendar.DATE, 1);
            setCheckOutTime(checkInDate);
        }
    }

    public void setMaxCheckOutTime(Calendar maxCheckOutTime) {
        this.maxCheckOutTime = maxCheckOutTime;
    }

    public String getMaxCheckOutDateString() {

        return dateFormat.format(maxCheckOutTime.getTime());
    }

    public void setMinCheckInTime(Calendar minCheckInTime) {
        this.minCheckInTime = minCheckInTime;
    }

    public String getMinCheckInDateString() {
        return dateFormat.format(minCheckInTime.getTime());
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
        modelChangeListener.onCustomerChanged();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setRoom(Room room) {
        this.room = room;
        modelChangeListener.onRoomChanged();
    }

    public Room getRoom() {
        return room;
    }

    public Calendar getOrderGenerateTime() {
        return generateTime;
    }

    public Calendar getCheckInTime() {
        Calendar cal = DateConvertor.toCheckInTime(getCheckInDate());
        return cal;
    }

    private Calendar getCheckInDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(checkInDateModel.getYear(), checkInDateModel.getMonth(), checkInDateModel.getDay(), 0, 0, 0);
        return cal;
    }

    public Calendar getCheckOutTime() {
        Calendar cal = DateConvertor.toCheckOutTime(getCheckOutDate());
        return cal;
    }

    private Calendar getCheckOutDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(checkOutDateModel.getYear(), checkOutDateModel.getMonth(), checkOutDateModel.getDay(), 0, 0, 0);
        return cal;
    }

    public int getTotalPrice() {
        return roomRateModel.getTotalPrice();
    }

    public int getDeposit() {
        return roomRateModel.getDeposit();
    }

    public int getDiscount() {
        return 0;
    }

    public int getPriceAfterDiscount() {
        return getTotalPrice();
    }

    private int getStayedDays() {
        long diff = getCheckOutDate().getTimeInMillis() - getCheckInDate().getTimeInMillis();
        return (int) (diff / (24L * 60 * 60 * 1000));
    }

}
