package persistent;

import entity.Room;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IRoomManager {
    /**
     * ��ѯ���е�δ���Ƴ��Ŀ���
     *
     * @return ���еĿ����б����û��Ӧ�÷���һ�����б���������null
     */
    List<Room> queryAllRoom();

    /**
     * ��ѯ���и����������ƺ������ס�Ŀշ���<br/>
     * checkinTime��checkoutTimeΪָ��Ҫ��ס��ʱ��Σ�checkinTime��checkoutTime��֤Ϊ��Ч����<br/>
     * roomType��ʾ��������͡��˲�������Ϊ�ա�����Ϊ��ʱ����Ϊ����������<br/>
     *
     * @param checkinTime  Ԥ����סʱ��
     * @param checkoutTime Ԥ���뿪ʱ��
     * @param roomType     ����
     * @return ���еķ��������ķ����б����û��Ӧ�÷���һ�����б���������null
     */
    List<Room> queryEmptyRoom(Timestamp checkinTime, Timestamp checkoutTime,
                              String roomType);

    /**
     * ����һ��ָ��ID�ķ���
     *
     * @param ID ���������ݿ��е�ID
     * @return IDΪָ��ID�ķ��䡣�����ѯʧ�ܣ�����null
     */
    Room loadRoom(long ID);

    /**
     * ����һ�����䡣<br/>
     * Ҫ����ķ����������ж��ʵ��������ǵ�ID�����ʱ�����������ݿ��У������쳣״����<br/>
     * �����쳣״���������ʧ�ܡ�
     *
     * @param room Ҫ���ӵķ��䡣����IDӦ���ڷ������Զ�����
     * @return �����ӵķ����ID���������ʧ�ܣ�Ӧ�÷���null
     */
    Long addNewRoom(Room room);

    /**
     * ɾ��һ������<br/>
     * ɾ��һ�����䲢�������ɾ�����ݿ��еļ�¼�����ǽ��ü�¼���Ϊ�Ƴ���
     * ���ɾ���÷���ʱ���д��ڲ����ڡ��رա��͡���ɡ�״̬�Ķ����������÷������ͣ������쳣�����
     * �����쳣״���������Ͳ���ʧ�ܡ�
     *
     * @param ID ���������ݿ��е�ID
     * @return �Ƿ�ɹ��Ƴ�������Ϣ��
     */
    boolean removeRoom(long ID);

    boolean modifyRoom(Long ID, String newState);
}
