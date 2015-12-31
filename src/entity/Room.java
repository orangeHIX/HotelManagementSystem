package entity;

/**
 * Created by hyx on 2015/12/10.
 */
public class Room {
    long ID;
    String roomNo;
    int floor;
    boolean window;
    RoomType roomType;
    RoomState roomState;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isWindow() {
        return window;
    }

    public void setWindow(boolean window) {
        this.window = window;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomState getRoomState() {
        return roomState;
    }

    public void setRoomState(RoomState roomState) {
        this.roomState = roomState;
    }

    public enum RoomState {
        idle("����"), cleanning("���"), renovating("װ��"),
        occupied("ռ��");

        String name;

        RoomState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static RoomState fromString(String name) {
            if (name != null) {
                for (RoomState roomState : RoomState.values()) {
                    //System.out.println(roomState.name + ", "+name +", "+(roomState.name.compareTo(name) == 0));
                    if (roomState.name().compareTo(name) == 0) {
                        return roomState;
                    }
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "floor=" + floor +
                ", ID=" + ID +
                ", roomNo='" + roomNo + '\'' +
                ", window=" + window +
                ", roomType=" + roomType +
                ", roomState=" + roomState +
                '}';
    }
}
