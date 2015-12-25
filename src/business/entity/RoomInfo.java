package business.entity;

/**
 * Created by hyx on 2015/12/22.
 */
public class RoomInfo {

    String roomNo;
    String roomTypeName;

    public RoomInfo(){

    }

    public RoomInfo(String roomNo, String roomTypeName) {
        this.roomNo = roomNo;
        this.roomTypeName = roomTypeName;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public String toString() {
        return "RoomInfo{" +
                "roomNo='" + roomNo + '\'' +
                ", roomTypeName='" + roomTypeName + '\'' +
                '}';
    }
}
