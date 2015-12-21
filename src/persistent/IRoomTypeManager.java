package persistent;

import entity.RoomType;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IRoomTypeManager {
    /**
     * ��ѯ���е�δ���Ƴ��ķ�������
     *
     * @return ���еķ��������б����û��Ӧ�÷���һ�����б���������null
     */
    List<RoomType> queryAllRoomType();

    /**
     * ����һ��ָ��ID�ķ�������
     *
     * @param ID �������������ݿ��е�ID
     * @return IDΪָ��ID�ķ������͡������ѯʧ�ܣ�����null
     */
    RoomType loadRoomType(long ID);

    /**
     * ����һ���������͡�<br/>
     *
     * @param roomType Ҫ���ӵķ������͡�����IDӦ���ڷ������Զ�����
     * @return �����ӵķ������͵ķ����ID������ʧ�ܷ���null
     */
    Long addNewRoomType(RoomType roomType);

    /**
     * ɾ��һ����������<br/>
     * ɾ��һ���������Ͳ��������ɾ�����ݿ��еļ�¼�����ǽ��ü�¼���Ϊ�Ƴ���
     * ���ɾ���÷�������ʱ���д��ڷ���������÷������ͣ������쳣�����
     * �����쳣״���������Ͳ���ʧ�ܡ�
     *
     * @param ID �������������ݿ��е�ID
     * @return �Ƿ�ɹ��Ƴ�����������Ϣ��
     */
    boolean removeRoomType(long ID);
}
