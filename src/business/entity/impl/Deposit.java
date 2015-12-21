package business.entity.impl;

import business.entity.IDeposit;

/**
 * Created by hyx on 2015/12/12.
 */
public class Deposit implements IDeposit {
    int deposit;

    @Override
    public int getDeposit() {
        return deposit;
    }

    @Override
    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
