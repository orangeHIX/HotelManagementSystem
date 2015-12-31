package business.inter;

import java.sql.Timestamp;

/**
 * Created by hyx on 2015/12/12.
 */
public interface IExtentionBusiness {
    boolean setExistingOrder(long existingOrderID);

    int getMaxExtentionDays(Timestamp endDate);

    boolean setExtentionDays(int days);

    boolean confirmExtention();
}
