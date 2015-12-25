package business.inter;

import business.NoFilterConditionException;
import business.entity.CustomerInfo;
import entity.Customer;

import java.util.List;

/**
 * Created by hyx on 2015/12/16.
 */
public interface ICustomerDisplay {
    /**
     * �˷�������ɸѡ�ͻ�����ɸѡ�����Ǳ�Ҫ�ġ�
     * ����ͻ����ϲ����а�����ɸѡ��Ϣ����ô�ÿͻ�����Ϊ����ɸѡ����
     *
     * @param customerInfo ���ڿͻ���ɸѡ��Ϣ�����customerInfo��ĳ����Ϣ�ֶ�Ϊnull����ʾ���ô���Ϣ�ν���ɸѡ��
     */
    void setCustomerFilterConditions(CustomerInfo customerInfo);

    /**
     * ���ҷ�������ɸѡ�����Ŀͻ����������տͻ����ֽ����ֵ�����
     * ÿ�ε��ø÷�������ѯ�������Ӧ�÷��ϵ�ǰ���õ�ɸѡ������
     * ���û������ĳЩ��Ҫɸѡ���������׳�NoFilterConditionException�쳣���쳣��Ϣָ����Щ��Ҫɸѡ����û�����á�
     *
     * @return ��������������ͻ��б������ѯ���Ϊ�գ�����null
     */
    List<Customer> getCustomer() throws NoFilterConditionException;
}
