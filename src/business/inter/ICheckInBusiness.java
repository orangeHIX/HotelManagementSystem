package business.inter;

import business.entity.IDeposit;

/**
 * Created by hyx on 2015/12/17.
 */
public interface ICheckInBusiness {

    /**��ȡѺ��*/
    void charge(IDeposit deposit);

    /**�ͻ���ס
     * @return  �ɹ�����Ӧ����ת��Ϊ��ס״̬����true�����򷵻�false*/
    boolean checkIn();
}
