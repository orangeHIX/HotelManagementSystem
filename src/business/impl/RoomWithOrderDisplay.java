package business.impl;

import business.NoFilterConditionException;
import business.entity.RoomInfo;
import business.entity.RoomWithOrder;
import business.inter.IRoomWithOrderDisplay;
import entity.Order;
import entity.Room;
import persistent.EntityManagerFactory;
import persistent.IQueryOrderByRoom;
import persistent.IRoomManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RoomWithOrderDisplay implements IRoomWithOrderDisplay {
    Calendar startDate;
    Calendar closingDate;
    RoomInfo roomInfo;
    EntityManagerFactory fa = new EntityManagerFactory();
    IQueryOrderByRoom orderManager = fa.createQueryOrderByRoom();
    IRoomManager roomManager = fa.createRoomManager();

    @Override
    public void setTimeFilterConditions(Calendar startDate, Calendar closingDate) {
        // TODO Auto-generated method stub
        this.startDate = startDate;
        this.closingDate = closingDate;

    }

    @Override
    public void setRoomInfoFilterConditions(RoomInfo roomInfo) {
        // TODO Auto-generated method stub
        this.roomInfo = roomInfo;
    }

    @Override
    public List<RoomWithOrder> searchRoomWithOrder() throws NoFilterConditionException {
        // TODO Auto-generated method stub
        if (roomInfo == null)
            throw new NoFilterConditionException("No RoomInfo Condition");
        else if (startDate == null)
            throw new NoFilterConditionException("No StartDate Condition");
        else if (closingDate == null)
            throw new NoFilterConditionException("No ClosingDate Condition");
        List<Room> roomList = roomManager.queryAllRoom();

        roomList.removeIf((va) -> va.getRoomState().equals(Room.RoomState.renovating));


        String conditionRoomNo = roomInfo.getRoomNo();
        if (conditionRoomNo != null) {
            roomList.removeIf((va) -> conditionRoomNo.compareTo(va.getRoomNo()) != 0);
        }
        String conditionRoomTypeName = roomInfo.getRoomTypeName();
        if (conditionRoomTypeName != null) {
            roomList.removeIf((va) -> va.getRoomType().getTypeName().compareTo(conditionRoomTypeName) != 0);
        }
//		for(int i = 0;i<roomList.size();i++){
//
//
//			if(!(roomList.get(i).getRoomNo().equals(roomInfo.getRoomNo()))
//				&&!(roomList.get(i).getRoomState().getName().equals("renovating")))
//			{
//				roomFilter.add(roomList.get(i));
//			}
//		}
        if (roomList.isEmpty()) {
            return null;
        }

        List<RoomWithOrder> roomWithOrderList = new ArrayList<RoomWithOrder>();
//        int k = 0;
        for (int i = 0; i < roomList.size(); i++) {
            RoomWithOrder roomWithOrder = new RoomWithOrder();
            roomWithOrder.setRoom(roomList.get(i));
            roomWithOrder.setStartDate(startDate);
            roomWithOrder.setClosingDate(closingDate);
//            SimpleDateFormat format = ViewUtils.getTimeFormat_yyyy_MM_dd_HH_mm_ss_E();
//            Log.d(format.format(startDate.getTime()));
//            Log.d(format.format(closingDate.getTime()));
            List<Order> list = orderManager.queryActiveOrderByRoom(new Timestamp(startDate.getTimeInMillis()),
                    new Timestamp(closingDate.getTimeInMillis()),
                    roomList.get(i).getID());

            roomWithOrder.setOrderList(list);
            roomWithOrderList.add(roomWithOrder);
            //k++;

        }

        return roomWithOrderList;

    }

}


