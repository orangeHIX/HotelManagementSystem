package business.inter;


/**
 * Created by hyx on 2015/12/15.
 */
public interface ICheckOutBusiness {

    /**
     * ���ü������Ķ���ID
     * @return ��ӦID���������Ҵ�����ס״̬����true�������������false*/
    boolean setImminentCheckOutOrder(long checkOutID);

    /**�շ�*/
    boolean charge();

    /**���*/
    boolean checkout();
}
