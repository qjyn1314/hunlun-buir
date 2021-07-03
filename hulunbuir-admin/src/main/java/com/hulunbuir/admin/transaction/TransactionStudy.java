package com.hulunbuir.admin.transaction;

import com.hulunbuir.admin.transaction.transactionanno.IUserAnnoService;
import com.hulunbuir.admin.transaction.transactionxml.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-25 16:37
 */
public class TransactionStudy {

    public static void main(String[] args) {
        ApplicationContext acs = new ClassPathXmlApplicationContext("com/hulunbuir/admin/transaction/transactionanno/applicationContext.xml");
        IUserService userService = acs.getBean(IUserService.class);
        userService.transfer("jack","luse",1000);

        ApplicationContext acsAnno = new ClassPathXmlApplicationContext("com/hulunbuir/admin/transaction/transactionxml/applicationContext.xml");
        IUserAnnoService userAnnoService = acsAnno.getBean(IUserAnnoService.class);
        userAnnoService.transfer("jack","luse",1000);
    }

}
