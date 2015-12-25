package gui.room_with_order_display;

import entity.RoomType;
import persistent.EntityManagerFactory;
import persistent.IRoomTypeManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyx on 2015/12/23.
 */
public class RoomTypeModel extends DefaultComboBoxModel {

    IRoomTypeManager roomTypeManager;
    List<RoomType> roomTypeList;

    public RoomTypeModel() {
        super();
        roomTypeManager = new EntityManagerFactory().createRoomTypeManager();
        roomTypeList = new ArrayList<>();
        updateRoomType();
        updateElement();
    }

    public RoomType getSelectedRoomType(){
        String s = (String)getSelectedItem();
        for(RoomType roomType: roomTypeList){
            if(roomType.getTypeName().compareTo(s) == 0){
                return roomType;
            }
        }
        return null;
    }

    public void updateElement() {
        removeAllElements();
        for (RoomType roomType : roomTypeList) {
            addElement(roomType.getTypeName());
        }
        addElement("全部类型");
    }


    public void updateRoomType() {
        roomTypeList.clear();
        roomTypeList = roomTypeManager.queryAllRoomType();
    }
}
