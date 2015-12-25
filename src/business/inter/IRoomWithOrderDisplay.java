package business.inter;

import business.NoFilterConditionException;
import business.entity.RoomInfo;
import business.entity.RoomWithOrder;

import java.util.Calendar;
import java.util.List;


/**
 * 本类用于从房间和时间段的角度分类查询房间和关联的订单。
 * 宾馆房间销售实际销售的是房间的一段时间使用权，即房时。
 * 通过房间分类查找关联到房间的订单就可以知道某段时间内这个房间房时的销售状况，这种销售情况是通过关联的订单体现的。
 * 按照这种方式查询出的房间和其关联的订单，提供了预订、入住、离店等基本业务的必要信息。
 * 本类提供若干个筛选条件来筛选房间和房间关联的订单。
 * Created by hyx on 2015/12/16.
 */
public interface IRoomWithOrderDisplay {

    /**
     * 此方法截取房时查找的时间范围，此筛选条件为必要筛选条件。
     * 筛选开始时间点和筛选截止时间点保证为有效时间点。
     * 如果某个房间关联的订单的入住时间和离店时间截取的时间段全部或者部分在查找的时间范围内，那么该订单被视为符合筛选条件
     *
     * @param startDate   筛选开始时间点
     * @param closingDate 筛选截止时间点
     */
    void setTimeFilterConditions(Calendar startDate, Calendar closingDate);

    /**
     * 此方法用于筛选关联的房间。此筛选条件为不是必要的。
     * 如果房间符合参数中包含的筛选信息，那么该房间被视为符合筛选条件
     *
     * @param roomInfo 关于房间的筛选信息。如果roomInfo的某个信息字段为null，表示不用此信息段进行筛选。
     */
    void setRoomInfoFilterConditions(RoomInfo roomInfo);

    /**
     * 查找符合所用筛选条件的房间及这些房间符合筛选条件的订单，这些订单将按照他们关联的房间分类，每个房间关联的订单按照时间的先后顺序排列。
     * 每次调用该方法，查询结果集合符合当前设置的筛选条件。
     * 如果没有设置某些必要筛选条件，就抛出NoFilterConditionException异常，异常信息指明那些必要筛选条件没有设置。
     *
     * @return 符合条件的房间及它们关联的有序订单。如果查询结果为空，返回null
     */
    List<RoomWithOrder> searchRoomWithOrder() throws NoFilterConditionException;
}
