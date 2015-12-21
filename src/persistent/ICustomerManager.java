package persistent;

import entity.Customer;

import java.util.List;

/**
 * Created by hyx on 2015/12/10.
 */
public interface ICustomerManager {

    /**
     * 查询所有的未被移除的客人
     *
     * @return 所有的客人列表。如果没有应该返回一个空列表，而不返回null
     */
    List<Customer> queryAllCustomer();

    /**
     * 加载一个指定ID的客人
     *
     * @param ID 客人在数据库中的ID
     * @return ID为指定ID的客人。如果查询失败，返回null
     */
    Customer loadCustomer(long ID);

    /**
     * 增加一个客人。<br/>
     *
     * @param customer 要增加的客人。它的ID应该在方法内自动分配
     * @return 新增加的客人的分配的ID。插入失败返回null
     */
    Long addNewCustomer(Customer customer);

    /**
     * 修改一个客人<br/>
     * 出现异常状况客人插入失败。
     *
     * @param newCustomer 客人在数据库中的ID
     * @return 是否成功修改客人信息
     */
    boolean modifyCustomer(Customer newCustomer);

    /**
     * 删除一个客人<br/>
     * 删除一个客人并不会真的删除数据库中的记录，而是将该记录标记为移除。
     * 如果删除该客人时，存在订单或者团体关联到该客人，属于异常情况。
     * 出现异常状况客人插入失败。
     *
     * @param ID 客人在数据库中的ID
     * @return 是否成功移除客人信息。
     */
    boolean removeCustomer(long ID);
}
