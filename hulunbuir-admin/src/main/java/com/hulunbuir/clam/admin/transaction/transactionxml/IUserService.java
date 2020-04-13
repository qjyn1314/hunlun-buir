package com.hulunbuir.clam.admin.transaction.transactionxml;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 17:30
 */
public interface IUserService {

    void transfer(String outer, String inner, Integer money);

}
