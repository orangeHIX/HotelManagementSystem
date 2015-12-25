package gui.add_new_order;

import business.entity.NewOrder;
import entity.Customer;
import entity.Room;
import org.jdatepicker.DateModel;

import javax.swing.table.TableModel;
import java.util.Calendar;

/**
 * Created by hyx on 2015/12/25.
 */
public class AddNewOrderModel {

    private String Note;
    private NewOrder newOrder;

    private DateModel checkInDateModel;
    private DateModel checkOutDateModel;

    private TableModel roomModel;

    private TableModel customerModel;

    public AddNewOrderModel() {
        newOrder = new NewOrder();
    }

    Customer getCustomer() {
        return newOrder.getOrderCustomer();
    }

    void setCustomer(Customer customer) {
        newOrder.setOrderCustomer(customer);
    }

    void setCustomerName(String customerName) {
        newOrder.getOrderCustomer().setName(customerName);
    }

    void setCustomerIDNumber(String customerIDNumber) {
        newOrder.getOrderCustomer().setIDNumber(customerIDNumber);
    }

    void setPhoneNumber(String phone) {
        newOrder.getOrderCustomer().setPhoneNumber(phone);
    }

    void getRoom() {
        newOrder.getRoom();
    }

    void setRoom(Room room) {
        newOrder.setRoom(room);
    }

    Calendar getCheckInTime() {
        Calendar cal = getCheckInDate();
        cal.set(Calendar.HOUR_OF_DAY, 18);
        return cal;
    }

    private Calendar getCheckInDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(checkOutDateModel.getYear(), checkOutDateModel.getMonth(), checkOutDateModel.getDay(), 0, 0, 0);
        return cal;
    }

    Calendar getCheckOutTime() {
        Calendar cal =getCheckOutDate();
        cal.set(Calendar.HOUR_OF_DAY, 12);
        return cal;
    }

    private Calendar getCheckOutDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(checkOutDateModel.getYear(), checkOutDateModel.getMonth(), checkOutDateModel.getDay(), 0, 0, 0);
        return cal;
    }

    int getTotalPrice(){
        //newOrder.getRoom().getRoomType().getPricePerNight() * getStayedDays();
        return 0;
    }

    private int getStayedDays(){
        long diff = getCheckOutDate().getTimeInMillis() - getCheckInDate().getTimeInMillis();
        return (int)(diff / (24L * 60 * 60 * 1000));
    }

}
