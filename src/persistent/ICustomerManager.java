package persistent;

import entity.Customer;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface ICustomerManager {

    /**
     * ��ѯ���е�δ���Ƴ��Ŀ���
     *
     * @return ���еĿ����б����û��Ӧ�÷���һ�����б���������null
     */
    List<Customer> queryAllCustomer();

    /**
     * ����һ��ָ��ID�Ŀ���
     *
     * @param ID ���������ݿ��е�ID
     * @return IDΪָ��ID�Ŀ��ˡ������ѯʧ�ܣ�����null
     */
    Customer loadCustomer(long ID);

    /**
     * ����һ�����ˡ�<br/>
     *
     * @param customer Ҫ���ӵĿ��ˡ�����IDӦ���ڷ������Զ�����
     * @return �����ӵĿ��˵ķ����ID������ʧ�ܷ���null
     */
    Long addNewCustomer(Customer customer);

    /**
     * �޸�һ������<br/>
     * �����쳣״�����˲���ʧ�ܡ�
     *
     * @param newCustomer ���������ݿ��е�ID
     * @return �Ƿ�ɹ��޸Ŀ�����Ϣ
     */
    boolean modifyCustomer(Customer newCustomer);

    /**
     * ɾ��һ������<br/>
     * ɾ��һ�����˲��������ɾ�����ݿ��еļ�¼�����ǽ��ü�¼���Ϊ�Ƴ���
     * ���ɾ���ÿ���ʱ�����ڶ�����������������ÿ��ˣ������쳣�����
     * �����쳣״�����˲���ʧ�ܡ�
     *
     * @param ID ���������ݿ��е�ID
     * @return �Ƿ�ɹ��Ƴ�������Ϣ��
     */
    boolean removeCustomer(long ID);
}
