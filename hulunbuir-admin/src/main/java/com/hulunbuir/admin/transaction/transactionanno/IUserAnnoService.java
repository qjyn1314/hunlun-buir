package com.hulunbuir.admin.transaction.transactionanno;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 17:30
 */
public interface IUserAnnoService {

    void transfer(String outer, String inner, Integer money);

}
