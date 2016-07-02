package com.liuboyu.designmodel.java8.tmeplatemethod;

/**
 * 模板方法模式
 * <p>
 * Created by Tony on 4/16/16.
 */
public abstract class LoanApplication {

    public void checkLoanApplication() throws Exception {
        checkIdentity();
        checkIncomeHistory();
        checkCreditHistory();
    }

    protected abstract void checkIdentity() throws Exception;
    protected abstract void checkIncomeHistory() throws Exception;
    protected abstract void checkCreditHistory() throws Exception;

}
