package business;

/**
 * Created by hyx on 2015/12/15.
 */
public interface ICheckOutBusiness {

    boolean setImminentCheckOutOrder(long checkOutID);

    boolean charge();

    boolean checkout();
}
