package entity;

/**
 * Created by hyx on 2015/12/10.
 */
public class Room {
    long ID;
    String roomNo;
    int floor;
    boolean window;
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

}
