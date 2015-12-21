package persistent;

import entity.Order;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface IOrderManager {
    /**
     * ��ѯ���з��������������Ҳ����ڡ��رա��͡���ɡ�״̬������<br/>
     * from��toʱ��������涨�˶�������ʱ��Ĳ�ѯ��Χ��from��to������֤Ϊ��Ч������<br/>
     * orderCustomerName��ʾ�µ��˵����֡��˲�������Ϊ�ա�����Ϊ��ʱ����Ϊ����������<br/>
     * roomType��ʾ��������͡��˲�������Ϊ�ա�����Ϊ��ʱ����Ϊ����������<br/>
     *
     * @param from              ��ѯ��ʼʱ��
     * @param to                ��ѯ��ֹʱ��
     * @param orderCustomerName �µ�������
     * @param roomType          ��������
     * @return �������������Ķ��������û��Ӧ�÷���һ�����б���������null
     */
    List<Order> queryActiveOrder(Timestamp from, Timestamp to,
                                 String orderCustomerName, String roomType);

    /**
     * ��ѯ���з����������Ҵ��ڡ�ȷ��Ԥ����״̬������<br/>
     * from��toʱ��������涨�˶�������ʱ��Ĳ�ѯ��Χ��from��to������֤Ϊ��Ч������<br/>
     * orderCustomerName��ʾ�µ��˵����֡��˲�������Ϊ�ա�����Ϊ��ʱ����Ϊ����������<br/>
     * roomType��ʾ��������͡��˲�������Ϊ�ա�����Ϊ��ʱ����Ϊ����������<br/>
     *
     * @param from              ��ѯ��ʼʱ��
     * @param to                ��ѯ��ֹʱ��
     * @param orderCustomerName �µ�������
     * @param roomType          ��������
     * @return �������������Ķ��������û��Ӧ�÷���һ�����б���������null
     */
    List<Order> queryRevervation(Timestamp from, Timestamp to,
                                 String orderCustomerName, String roomType);

    /**
     * ����һ��ָ��ID�Ķ���
     *
     * @param ID ���������ݿ��е�ID
     * @return IDΪָ��ID�Ķ����������ѯʧ�ܣ�����null
     */
    Order loadOrder(long ID);

    /**
     * ����һ��������<br/>
     * Ҫ����Ķ�����������ж��ʵ��������ǵ�ID�����ʱ�����������ݿ��У������쳣״����<br/>
     * �����쳣״����������ʧ�ܡ�
     *
     * @param order Ҫ���ӵĶ���������IDӦ���ڷ������Զ�����
     * @return �����ӵĶ�����ID���������ʧ�ܣ�Ӧ�÷���null
     */
    Long addNewOrder(Order order);


    /**
     * �޸�һ������������newOrder�е�ID��ѯҪ�޸ĵĶ�����¼<br/>
     * Ҫ�޸ĵĶ�����������г�����״̬�Ķ��ʵ������޸Ķ�����Ӧ�ø��������޸���Щʵ�����������������޸������쳣���<br/>
     * ����������ʵ�����Ϊ�գ������ǵ�ID�����ʱ�����������ݿ��У������쳣״����<br/>
     * �����쳣״����������ʧ�ܡ�
     *
     * @param newOrder ���������ݿ��е�ID
     * @return �Ƿ��޸Ķ����ɹ�
     */
    boolean modifyOrder(Order newOrder);

}
