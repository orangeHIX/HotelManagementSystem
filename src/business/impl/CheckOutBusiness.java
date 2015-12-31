package business.impl;


import business.entity.Deposit;
import business.inter.ICheckOutBusiness;
import entity.Order;
import entity.Order.OrderState;
import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IRoomManager;

public class CheckOutBusiness implements ICheckOutBusiness {
    Deposit deposit;
    Order checkOutOrder;
    EntityManagerFactory fa = new EntityManagerFactory();
    IOrderManager orderManager = fa.createOrderManager();
    IRoomManager roomManager = fa.createRoomManager();

    @Override
    public boolean setImminentCheckOutOrder(long checkOutID) {
        checkOutOrder = orderManager.loadOrder(checkOutID);
        if (checkOutOrder != null
                && checkOutOrder.getOrderState().equals(OrderState.checkin_confirm)) {
            return true;
        }
        checkOutOrder = null;
        return false;
    }

    @Override
    public boolean charge() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean checkout() {
        if (checkOutOrder != null) {
        /*Change the OrderState to "Complete"*/
            checkOutOrder.setOrderState(OrderState.complete);
		/*Change the RoomState to "Cleaninning"*/
//		Room checkOutRoom = checkOutOrder.getRoom();
//		checkOutRoom.setRoomState(RoomState.cleanning);
            Long ID = checkOutOrder.getID();
//            roomManager.modifyRoom(ID, checkOutRoom.getRoomState().getName());
		/*Return checkOut*/
            boolean checkOut = orderManager.modifyOrder(checkOutOrder);
            return checkOut;
        }
        return false;
    }

}
