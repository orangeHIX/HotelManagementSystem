package business.inter;

import business.entity.NewOrder;
import entity.Order;

/**
 * Created by hyx on 2015/12/16.
 */
public interface INewOrderBusiness {

    /**
     * �ύ����������newOrder�������Ա�֤��Ч�Ϸ����Ҳ������ж�����ͻ
     * @return �ɹ��ύ�Ķ��������û�гɹ��ύ����null
     */
    Order submitOrder(NewOrder newOrder);

    /**
     * ����Ӧ���ύ�Ķ�����
     * @return �ɹ������Ѿ��ύ�Ķ����������ߵ�ǰ��û���ύ����������true�������������false*/
    boolean cancelOrder();
}
