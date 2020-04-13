package com.hulunbuir.clam.admin.transaction.transactionxml;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 16:38
 */
public class IUserDaoImpl extends JdbcDaoSupport implements IUserDao {

    @Override
    public void out(String outer, Integer money) {
        this.getJdbcTemplate().update("UPDATE `bankmoney` SET `money` = `money` - ? WHERE `username` = ? ",money,outer);
    }

    @Override
    public void in(String inner, Integer money) {
        this.getJdbcTemplate().update("UPDATE `bankmoney` SET `money` = `money` + ? WHERE `username` = ? ",money,inner);
    }
}
