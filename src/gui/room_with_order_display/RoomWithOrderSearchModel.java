package gui.room_with_order_display;

import business.entity.RoomInfo;
import entity.RoomType;
import org.jdatepicker.DateModel;
import persistent.EntityManagerFactory;
import persistent.IRoomTypeManager;

import javax.swing.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hyx on 2015/12/23.
 */
public class RoomWithOrderSearchModel {

    ComboBoxModel periodModel;

    Calendar previousStartDate;
    Calendar previousEndDate;

//    Calendar startDate;
//    Calendar endDate;

    DateModel startDateModel;
    DateModel endDateModel;

    JTextField roomNoModel;
    RoomTypeModel roomtypeModel;


    IRoomTypeManager roomTypeManager;
    Map<String, RoomType> roomTypeMap;


    public RoomWithOrderSearchModel(ComboBoxModel periodModel, DateModel startDateModel,
                                    DateModel endDateModel, JTextField roomNoField, RoomTypeModel roomTypeModel) {
        this.periodModel = periodModel;
        this.startDateModel = startDateModel;
        previousStartDate = getStartDate();
        this.endDateModel = endDateModel;
        previousEndDate = getEndDate();
        this.roomNoModel = roomNoField;
        this.roomtypeModel = roomTypeModel;
        roomTypeManager = new EntityManagerFactory().createRoomTypeManager();
        roomTypeMap = new HashMap<>();
        updateRoomTypeMap();
    }

    public void updateRoomTypeMap() {
        roomTypeMap.clear();
        List<RoomType> list = roomTypeManager.queryAllRoomType();
        for (RoomType roomType : list) {
            roomTypeMap.put(roomType.getTypeName(), roomType);
        }
    }


    public Period getPeriod() {
        return (Period) periodModel.getSelectedItem();
    }

    public void setPeriod(Period period) {
        periodModel.setSelectedItem(period);
        changeStartDateModel();
        changeEndDateModel();
    }

    public void changeStartDateModel() {
        Calendar cal = getPeriod().getStartDate();
        if (cal != null) {
            setStartDateModel(cal);
        }
    }

    private void setStartDateModel(Calendar cal) {
        //Log.d("setStartDateModel" + DateString.toString(cal));
        startDateModel.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        previousStartDate = getStartDate();
    }

    public void changeEndDateModel() {
        Calendar cal = getPeriod().getEndDate();
        if (cal != null) {
            setEndDateModel(cal);
        }
    }

    private void setEndDateModel(Calendar cal) {
        //Log.d("setEndDateModel" + DateString.toString(cal));
        endDateModel.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        previousEndDate = getEndDate();
    }

    public void checkStartDateModel() {
        Calendar startDate = getStartDate();
        Calendar endDate = getEndDate();
        if (startDate.compareTo(endDate) <= 0) {
            previousStartDate = getStartDate();
        } else {
            setStartDateModel(previousEndDate);
        }
    }

    public void checkEndDateModel() {
        Calendar startDate = getStartDate();
        Calendar endDate = getEndDate();
        if (startDate.compareTo(endDate) <= 0) {
            previousEndDate = getEndDate();
        } else {
            setEndDateModel(previousEndDate);
        }
    }

    public Calendar getStartDate() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDateModel.getYear(), startDateModel.getMonth(), startDateModel.getDay(), 0, 0, 0);
        return startDate;
    }


    public Calendar getEndDate() {
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDateModel.getYear(), endDateModel.getMonth(), endDateModel.getDay(), 0, 0, 0);
        return endDate;
    }

    public String getRoomNo() {
        return roomNoModel.getText().isEmpty() ? null : roomNoModel.getText();
    }

    public void setRoomNo(String roomNo) {
        roomNoModel.setText(roomNo);
    }

//    public String getSelectedRoomTypeName() {
//        return (String) roomtypeModel.getSelectedItem();
//    }


    public RoomType getSelectedRoomType() {
        return roomtypeModel.getSelectedRoomType();
    }

    public RoomInfo getRoomInfo() {
        RoomType roomType = getSelectedRoomType();
        return new RoomInfo(getRoomNo(), roomType == null ? null : roomType.getTypeName());
    }

}
