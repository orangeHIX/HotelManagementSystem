package entity_persistent;

import entity.OrderType;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IOrderTypeManager {
    /**��ѯ���е�δ���Ƴ��Ķ�������
     * @return ���еĶ��������б����û��Ӧ�÷���һ�����б���������null*/
    List<OrderType> queryAllOrderType();
    /**����һ��ָ��ID�Ķ�������
     * @param ID �������������ݿ��е�ID
     * @return IDΪָ��ID�Ķ������͡������ѯʧ�ܣ�����null*/
    OrderType loadOrderType(long ID);
    /**����һ���������͡�<br/>
     * @param orderType Ҫ���ӵĶ������͡�����IDӦ���ڷ������Զ�����
     * @return �����ӵĶ������͵ķ����ID������ʧ�ܷ���null*/
    Long addNewOrderType(OrderType orderType);

    /**ɾ��һ����������<br/>
     * ɾ��һ���������Ͳ��������ɾ�����ݿ��еļ�¼�����ǽ��ü�¼���Ϊ�Ƴ���
     * ���ɾ���ö�������ʱ���д��ڲ����ڡ��رա��͡���ɡ�״̬�����������÷������ͣ������쳣�����
     * �����쳣״���������Ͳ���ʧ�ܡ�
     * @param ID �������������ݿ��е�ID
     * @return �Ƿ�ɹ��Ƴ�����������Ϣ��*/
    boolean removeOrderType(long ID);
}
