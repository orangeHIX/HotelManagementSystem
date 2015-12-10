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
        idle("����"), cleanning("���"), renovating("װ��"),
        occupied("ռ��");

        String name;

        RoomState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
