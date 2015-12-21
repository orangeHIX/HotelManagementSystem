package business.inter;

/**
 * Created by hyx on 2015/12/12.
 */
public interface IExtentionBusiness {
    boolean setExistingOrder(long existingOrderID);

    int getMaxExtentionDays();

    boolean setExtentionDays(int days);

    boolean confirmExtention();
}
