package business.inter;

import business.entity.NewOrder;
import entity.Customer;
import entity.Room;

import java.util.Calendar;

/**
 * �˽ӿڵĹ����ǲ����µĶ���
 * Created by hyx on 2015/12/16.
 */
public interface INewOrderCreator {
    /**
     * �����¶������µ��ˡ�<B>��Ҫ���ò�����</B><br/>
     * �µ��˱��������пͻ������������Ƿ��и�ID�����пͻ�����������ڸÿͻ���¼�������쳣�����
     * �쳣�������ֵӦ��Ϊfalse��
     *
     * @param customer �¶������µ���
     * @return �ɹ������¶������µ��˷���true�����򷵻�false
     */
    boolean setOrderCustomer(Customer customer);

    /**
     * �����¶�������ס�ˡ�<B>���Ǳ�Ҫ���ò�����</B><br/>
     * ��ס�˱��������пͻ������������Ƿ��и�ID�����пͻ�����������ڸÿͻ���¼�������쳣�����
     * �쳣�������ֵӦ��Ϊfalse��
     *
     * @param customer �¶�������ס��
     * @return �ɹ������¶�������ס�˷���true�����򷵻�false
     */
    boolean setStayedCustomer(Customer customer);

    /**
     * �����¶�������ס��Ϊ�µ��ˣ����ö�������ס�˺��µ�����ͬһ���ˡ�<B>���Ǳ�Ҫ���ò�����</B><br/>
     * �����ʱ��û�����ö������µ��ˣ������쳣������쳣�������ֵӦ��Ϊfalse��
     *
     * @return �ɹ������¶�������ס�˷���true�����򷵻�false
     */
    boolean setSameStayedCustomer();

    /**
     * ������ס���䡣<B>��Ҫ���ò�����</B><br/>
     * ��ס�˱��������з��䣬���������Ƿ��и�ID�����з��䡣��������ڸ÷����¼�������쳣�����
     * �쳣�������ֵӦ��Ϊfalse��
     *
     * @param room Ҫ��ס�ķ���
     * @return �ɹ������¶�������ס���䷵��true�����򷵻�false
     */
    boolean setRoom(Room room);

    /**
     * ������סʱ��Ρ�<B>��Ҫ���ò�����</B><br/>
     * ����סʱ������ʱ�����򵥵���Ч�Լ��
     * @param checkInTime Ԥ����סʱ��
     * @param checkOutTime Ԥ�����ʱ��
     */
    boolean setTime(Calendar checkInTime, Calendar checkOutTime);

    /**
     * ����֮ǰ���õĲ��������µĶ���ԭ�͡�
     * ���ĳЩ��Ҫ����û�����õ����޷������¶���ԭ�ͣ������쳣�����
     * �쳣�������ֵӦ��Ϊnull��
     *
     * @return �µĶ���ԭ�͡��޷����ɶ���ԭ�ͷ���null
     */
    NewOrder createNewOrder();
}
