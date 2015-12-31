package business.impl;


import business.inter.ICancelOrderBusiness;
import entity.Order;
import entity.Order.OrderState;
import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IRoomManager;

public class CancelOrderBusiness implements ICancelOrderBusiness {
    Order cancelOrder;
    EntityManagerFactory fa = new EntityManagerFactory();
    IOrderManager orderManager = fa.createOrderManager();
    IRoomManager roomManager = fa.createRoomManager();

    @Override
    public boolean setCancelOrder(long id) {
        cancelOrder = orderManager.loadOrder(id);
        if (cancelOrder != null
                && cancelOrder.getOrderState().equals(OrderState.reservation_confirm)) {
            return true;
        }
        cancelOrder = null;
        return false;
    }

    @Override
    public boolean confirmCancel() {
        if (cancelOrder == null) {
            return false;
        }
        cancelOrder.setOrderState(OrderState.close);
        boolean cancel = orderManager.modifyOrder(cancelOrder);
        return cancel;
    }

}
