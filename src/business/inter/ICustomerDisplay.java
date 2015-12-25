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
     * 此方法用于筛选客户。此筛选条件是必要的。
     * 如果客户符合参数中包含的筛选信息，那么该客户被视为符合筛选条件
     *
     * @param customerInfo 关于客户的筛选信息。如果customerInfo的某个信息字段为null，表示不用此信息段进行筛选。
     */
    void setCustomerFilterConditions(CustomerInfo customerInfo);

    /**
     * 查找符合所用筛选条件的客户，订单按照客户名字进行字典排序。
     * 每次调用该方法，查询结果集合应该符合当前设置的筛选条件。
     * 如果没有设置某些必要筛选条件，就抛出NoFilterConditionException异常，异常信息指明那些必要筛选条件没有设置。
     *
     * @return 符合条件的有序客户列表。如果查询结果为空，返回null
     */
    List<Customer> getCustomer() throws NoFilterConditionException;
}
