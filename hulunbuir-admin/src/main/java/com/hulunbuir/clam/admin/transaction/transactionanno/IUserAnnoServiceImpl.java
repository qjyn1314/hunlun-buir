package com.hulunbuir.clam.admin.transaction.transactionanno;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 17:31
 */
public class IUserAnnoServiceImpl implements IUserAnnoService {


    //DI setter方式注入bean
    private IUserAnnoDao userDao;

    public void setUserDao(IUserAnnoDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void transfer(String outer, String inner, Integer money) {
        userDao.out(outer,money);
        int i = 1/0;
        userDao.in(inner,money);
    }



}
