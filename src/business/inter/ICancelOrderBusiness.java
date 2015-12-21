package business.inter;

/**
 * Created by hyx on 2015/12/12.
 */
public interface ICancelOrderBusiness {

    boolean setCancelOrder(long id);

    boolean confirmCancel();
}
