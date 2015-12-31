package business.impl;

import business.entity.Deposit;
import business.entity.NewOrder;
import business.inter.INewOrderBusiness;
import entity.Order;
import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IQueryOrderByRoom;
import persistent.IRoomManager;
import utils.Log;

import java.sql.Timestamp;

/**
 * Created by hyx on 2015/12/30.
 */
public abstract class NewOrderBusiness implements INewOrderBusiness {
    protected Deposit deposit;
    protected Order order;
    private EntityManagerFactory fa;
    private IQueryOrderByRoom queryOrderByRoom;
    protected IOrderManager orderManager;

    public NewOrderBusiness(){
        fa = new EntityManagerFactory();
        queryOrderByRoom = fa.createQueryOrderByRoom();
        orderManager = fa.createOrderManager();
        //roomManager = fa.createRoomManager();
    }

    @Override
    public Order submitOrder(NewOrder newOrder) {
        //this.newOrder = newOrder;
        if ((queryOrderByRoom.queryActiveOrderByRoom(new Timestamp(newOrder.getCheckInTime().getTimeInMillis()),
                new Timestamp(newOrder.getCheckOutTime().getTimeInMillis()),
                newOrder.getRoom().getID()).isEmpty())) {
            Order norder = new Order();
            norder.setGenerateTime(new Timestamp(System.currentTimeMillis()));
            norder.setOrderCustomer(newOrder.getOrderCustomer());
            norder.setAccommodateCustomer(newOrder.getStayedCustomer());
            norder.setRoom(newOrder.getRoom());
            norder.setCheckinTime(new Timestamp(newOrder.getCheckInTime().getTimeInMillis()));
            norder.setCheckoutTime(new Timestamp(newOrder.getCheckOutTime().getTimeInMillis()));
            norder.setOrderState(getSubmitOrderState());
            Log.d("submitOrder:" + norder);
            // norder.setPaid(newOrder.getDeposit().getDeposit());
            /* Add new Order */
            Long ID = orderManager.addNewOrder(norder);
            if (ID != null) {
                return order = orderManager.loadOrder(ID);
            }
        }
        return null;
    }
    @Override
    public Order.OrderState getSubmitOrderState(){
        return null;
    }

    @Override
    public boolean cancelOrder() {
        /* Change the state to close */
        if(order != null) {
            order.setOrderState(Order.OrderState.close);
            order.setCloseTime(new Timestamp(System.currentTimeMillis()));
            boolean modified = orderManager.modifyOrder(order);
            return modified;
        }
        return false;
    }
}
