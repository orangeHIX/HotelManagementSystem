package business.impl;


import java.util.List;

import business.inter.IChangeRoomBusiness;
import entity.Order;
import entity.Room;
import entity.Room.RoomState;

import persistent.EntityManagerFactory;
import persistent.IOrderManager;
import persistent.IRoomManager;

public class ChangeRoomBusiness implements IChangeRoomBusiness {
	Order ChangeRoomOrder;
	EntityManagerFactory fa = new EntityManagerFactory();
	IOrderManager orderManager = fa.createOrderManager();
	IRoomManager roomManager = fa.createRoomManager();
	Room OldRoom;
	Room NewRoom;
	@Override
	public void setOldRoom(String roomNo) {
		// TODO Auto-generated method stub
		OldRoom = ChangeRoomOrder.getRoom();

	}

	@Override
	public List<Room> getRoomAvaliabletoChange() {
		// TODO Auto-generated method stub
		/*Return Emtpy Room List*/
		return roomManager.queryEmptyRoom(ChangeRoomOrder.getCheckinTime()
				,ChangeRoomOrder.getCheckoutTime()
				,ChangeRoomOrder.getRoom().getRoomType().getTypeName());
	}

	@Override
	public boolean setChangeRoom(Room newRoom) {
		// TODO Auto-generated method stub
		this.NewRoom = newRoom;
		List<Room> AvailableRoom = roomManager.queryEmptyRoom(ChangeRoomOrder.getCheckinTime()
				,ChangeRoomOrder.getCheckoutTime()
				,ChangeRoomOrder.getRoom().getRoomType().getTypeName());
		/*Check the NewRoom whether is Empty*/
		if(AvailableRoom.contains(NewRoom))
			return true;
		else 
			return false;
	}

	@Override
	public boolean confirmChange() {
		// TODO Auto-generated method stub
		OldRoom.setRoomState(RoomState.idle);
		boolean OldRoomModified = roomManager.modifyRoom(ChangeRoomOrder.getID(),
				OldRoom.getRoomState().getName());
		NewRoom.setRoomState(RoomState.occupied);
		boolean NewRoomModified = roomManager.modifyRoom(ChangeRoomOrder.getID(),
				NewRoom.getRoomState().getName());
		/*The State of the OldRoom changed to "idle"
		 *The State of the NewRoom changed to "occupied"
		 *Successfully Change Room
		 **/
		if(OldRoomModified&&NewRoomModified)
			return true;
		else
		    return false;
	}

}
