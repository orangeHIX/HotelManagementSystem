package gui.new_order;

/**
 * Created by hyx on 2015/12/26.
 */
public interface OrderModelChangeListener {

    void onBasicInfoChanged();

    void onCustomerChanged();

    void onRoomChanged();

    void onCheckInAndOutTimeChanged();

}
