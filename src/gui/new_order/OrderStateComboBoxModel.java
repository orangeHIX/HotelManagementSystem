package gui.new_order;

import entity.Order;

import javax.swing.*;

/**
 * Created by hyx on 2015/12/30.
 */
public class OrderStateComboBoxModel extends DefaultComboBoxModel {
    public OrderStateComboBoxModel() {
        super();
        for (Order.OrderState state : Order.OrderState.values()) {
            addElement(state);
        }
    }

}
