package persistent.impl;

import entity.RoomType;
import persistent.IRoomTypeManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzh on 2015/12/22.
 */
public class RoomTypeManager extends Pkid_from_add implements IRoomTypeManager{
    //static ConnectionSQL con = new ConnectionSQL();

    @Override
    public List<RoomType> queryAllRoomType() {
        List<RoomType> lr= new ArrayList<>();
        ResultSet rs = con.querySQL("select * from hotel.room_type;");
        hu(lr,rs);
        return lr;
    }

    @Override
    public RoomType loadRoomType(long ID) {
        RoomType roomType = new RoomType();
        ResultSet rs = con.querySQL("select * from room_type where room_type_pkid = " + ID);
        try{if(rs.next()){
            roomType.setID(rs.getLong(1));
            roomType.setTypeName(rs.getString(2));
            roomType.setPricePerNight(rs.getInt(3));
            roomType.setCapicity(rs.getInt(4));
            roomType.setSubscriptionPrice((rs.getInt(5)));}
        }catch(Exception e){
            e.printStackTrace();
        }
        return roomType;
    }

    @Override
    public Long addNewRoomType(RoomType roomType) {
        Long b ;
        int n = con.updateSQL("insert into room_type" +
                "(room_type,unit_price,p_num,deposit,type_del_mark) " +
                "values(\""+ roomType.getTypeName() +"\","+ roomType.getPricePerNight() + ","+
                roomType.getCapicity() +"," + roomType.getSubscriptionPrice() +",false);");
        b = get_pkid("select max(room_type_pkid) from room_type;",n);
        return b;
    }

    @Override
    public boolean removeRoomType(long ID) {
        int n;
        n = con.updateSQL("update room_type set type_del_mark =true where room_type_pkid=" +
                ID);
        if(n>0){
            return true;
        }else{
            return false;
        }
    }

    private void hu(List<RoomType> l, ResultSet rs){
        try {
            while(rs.next()){
                RoomType roomType = new RoomType();
                roomType.setID(rs.getInt(1));
                roomType.setTypeName(rs.getString(2));
                roomType.setPricePerNight(rs.getInt(3));
                roomType.setCapicity(rs.getInt(4));
                roomType.setSubscriptionPrice((rs.getInt(5)));
                l.add(roomType);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
