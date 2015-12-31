package persistent.impl;

import entity.Room;
import persistent.IRoomManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzh on 2015/12/11.
 */

public class RoomManager extends Pkid_from_add implements IRoomManager{
    //static ConnectionSQL con = new ConnectionSQL();
    static RoomTypeManager rtm = new RoomTypeManager();

    @Override
    public List<Room> queryAllRoom() {
        List<Room> lr= new ArrayList<>();
        ResultSet rs = con.querySQL("select * from hotel.room;");
        hu(lr,rs);
        return lr;
    }

    @Override
    public List<Room> queryEmptyRoom(Timestamp checkinTime, Timestamp checkoutTime, String roomType) {
        List<Room> lr= new ArrayList<Room>();
        ResultSet rs = con.querySQL("select `room_pkid`, `room_num`, `floor`, `windows`," +
                " `room_type_pkid`, `room_state` " +
                "from yewu where outdatetime < \'" + checkinTime + "\' and " +
                " `room_pkid` not in (select `room_pkid` from yewu where indatetime > \'" + checkoutTime + "\') " +
                "or indatetime = NULL " +
                "and room_type = \'" + roomType + "\';");
        hu(lr,rs);
        return lr;
    }

    @Override
    public Room loadRoom(long ID) {                 //ok
        Room room = new Room();
        ResultSet rs = con.querySQL("select * from room where room_pkid = " + ID);
        try{if (rs.next()){
            room.setID(rs.getInt(1));
            room.setRoomNo(rs.getString(2));
            room.setFloor(rs.getInt(3));
            room.setWindow(rs.getBoolean(4));
            room.setRoomType(rtm.loadRoomType(rs.getInt(5)));
            room.setRoomState(Room.RoomState.fromString(rs.getString(6)));
        }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return room;
    }

    @Override
    public Long addNewRoom(Room room) {
        Long b; //ok
        int n;
        String roomNo = room.getRoomNo();
        int floor = room.getFloor();
        boolean window = room.isWindow();
        long typeid =room.getRoomType().getID();
        //String state =room.getRoomState().getName();
        n = con.updateSQL("insert into room" +
                "(room_num,floor,windows,room_type_pkid,room_state, room_del_mark) " +
                "values("+roomNo+","+floor+","+window+","+ typeid+",\'idle\',false);");
        b= get_pkid("select max(room_pkid) from room",n);
        return b;
    }

    @Override
    public boolean removeRoom(long ID) {            //ok
        int n;
        n = con.updateSQL("update room set room_del_mark =true where room_pkid=" +
                ID);
        if(n>=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean modifyRoom(Long ID, String newState) {
        int n;
        n = con.updateSQL("update room set room_state = \'"+ newState +"\' where room_pkid="+ID+";");
        if(n>0){
            return true;
        }else{
            return false;
        }
    }


    private void hu(List<Room> l,ResultSet rs){
        try {
            while(rs.next()){
                Room room = new Room();
                room.setID(rs.getInt(1));
                room.setRoomNo(rs.getString(2));
                room.setFloor(rs.getInt(3));
                room.setWindow(rs.getBoolean(4));
                room.setRoomType(rtm.loadRoomType(rs.getInt(5)));
                room.setRoomState(Room.RoomState.fromString(rs.getString(6)));
                l.add(room);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
