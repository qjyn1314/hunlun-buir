package com.hulunbuir.admin.transaction.transactionanno;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 16:38
 */
public interface IUserAnnoDao {

    void out(String outer, Integer money);

    void in(String inner, Integer money);

}
