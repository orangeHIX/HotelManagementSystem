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

    public enum RoomState {
        idle("空闲"), cleanning("清洁"), renovating("装修"),
        occupied("占据");

        String name;

        RoomState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

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
}
