package com.able.transactionproagationdemo;

public interface FooService {
    void insertThenRollback() throws RollbackException;

    void invokeInsertThenRollback();
}
