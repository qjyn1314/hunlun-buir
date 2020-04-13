package com.hulunbuir.clam.admin.transaction.transactionxml;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 16:38
 */
public interface IUserDao {

    void out(String outer, Integer money);

    void in(String inner, Integer money);

}
