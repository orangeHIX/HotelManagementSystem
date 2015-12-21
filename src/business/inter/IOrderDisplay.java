package business.inter;

import business.NoFilterConditionException;
import business.entity.ICustomerInfo;
import business.entity.IRoomInfo;
import entity.Order;

import java.util.Calendar;
import java.util.List;

/**
 * �˽ӿ����ڲ�ѯ����
 * Created by hyx on 2015/12/16.
 */
public interface IOrderDisplay {

    /**
     * ����ʱ�䶩������ʱ��ɸѡʱ��Ρ���ɸѡ�����Ǳ�Ҫɸѡ������
     * ��������Ĳ���ʱ���ڸ�ʱ����ڣ�������������
     */
    void setGeneratedTimeFilterConditions(Calendar startDate, Calendar closingDate);

    /**
     * �˷�������ɸѡ���������ķ��䡣��ɸѡ����Ϊ���Ǳ�Ҫ�ġ�
     * ���������ϲ����а�����ɸѡ��Ϣ����ô�÷��䱻��Ϊ����ɸѡ����
     *
     * @param roomInfo ���ڷ����ɸѡ��Ϣ�����roomInfo��ĳ����Ϣ�ֶ�Ϊnull����ʾ���ô���Ϣ�ν���ɸѡ��
     */
    void setRoomInfoFilterConditions(IRoomInfo roomInfo);

    /**
     * �˷�������ɸѡ�����������µ��ˡ���ɸѡ����Ϊ���Ǳ�Ҫ�ġ�
     * ����µ��˷��ϲ����а�����ɸѡ��Ϣ����ô���µ��˱���Ϊ����ɸѡ����
     *
     * @param customerInfo �����µ��˵�ɸѡ��Ϣ�����customerInfo��ĳ����Ϣ�ֶ�Ϊnull����ʾ���ô���Ϣ�ν���ɸѡ��
     */
    void setCustomerInfoFilterConditions(ICustomerInfo customerInfo);

    /**
     * ���ҷ�������ɸѡ�����Ķ������������ն�������ʱ����Ⱥ�˳�����С�
     * ÿ�ε��ø÷�������ѯ������Ϸ��ϵ�ǰ���õ�ɸѡ������
     * ���û������ĳЩ��Ҫɸѡ���������׳�NoFilterConditionException�쳣���쳣��Ϣָ����Щ��Ҫɸѡ����û�����á�
     *
     * @return �������������򶩵��������ѯ���Ϊ�գ�����null
     */
    List<Order> searchOrders() throws NoFilterConditionException;
}
