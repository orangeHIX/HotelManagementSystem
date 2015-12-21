package business;

import business.entity.INewOrder;
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
     * ����µ��������пͻ�����customer�е�IDΪ�Ǹ�ֵ���������Իᱻ���ԡ���ʱӦ�ü���Ƿ��и�ID�����пͻ�����������ڸÿͻ���¼�������쳣�����
     * ����µ������¿ͻ�����customer�е�IDΪ-1����ʱӦ�ü��customer�ĸ��������Ƿ�Ϸ������customer�в��Ϸ������ԣ������쳣�����
     * �쳣�������ֵӦ��Ϊfalse��
     *
     * @param customer �¶������µ���
     * @return �ɹ������¶������µ��˷���true�����򷵻�false
     */
    boolean setOrderCustomer(Customer customer);

    /**
     * �����¶�������ס�ˡ�<B>���Ǳ�Ҫ���ò�����</B><br/>
     * �����ס�������пͻ�����customer�е�IDΪ�Ǹ�ֵ���������Իᱻ���ԡ���ʱӦ�ü���Ƿ��и�ID�����пͻ�����������ڸÿͻ���¼�������쳣�����
     * �����ס�����¿ͻ�����customer�е�IDΪ-1����ʱӦ�ü��customer�ĸ��������Ƿ�Ϸ������customer�в��Ϸ������ԣ������쳣�����
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
     * ������ס���䣬��סʱ��Ρ�<B>��Ҫ���ò�����</B><br/>
     * ������ʱ�䷿�䱻��������ռ�ݣ����ߴ��ڲ��ɳ��۵�״̬�������쳣�����
     * �쳣�������ֵӦ��Ϊfalse��
     * @param room Ҫ��ס�ķ���
     * @param checkInTime Ԥ����סʱ��
     * @param checkOutTime Ԥ�����ʱ��
     * @return �ɹ������¶�������ס�˷���true�����򷵻�false
     */
    boolean setRoomWithTime(Room room, Calendar checkInTime, Calendar checkOutTime);

    /**����֮ǰ���õĲ��������µĶ���ԭ�͡�
     * ���ĳЩ��Ҫ����û�����õ����޷������¶���ԭ�ͣ������쳣�����
     * �����ΪĳЩ����������ԭ�������еĶ����г�ͻ�������쳣�����
     * �쳣�������ֵӦ��Ϊnull��
     *
     * @return �µĶ���ԭ�͡��޷����ɶ���ԭ�ͷ���null*/
    INewOrder createNewOrder();
}
