package com.hulunbuir.admin.springstudy;

import com.hulunbuir.admin.springstudy.txconfig.StuInfo;
import com.hulunbuir.admin.springstudy.txconfig.StuInfoService;
import com.hulunbuir.admin.springstudy.txconfig.TxConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/8 21:41
 */
public class TxTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        StuInfoService stuInfoService = applicationContext.getBean(StuInfoService.class);
        String stuName = UUID.randomUUID().toString().substring(0, 5).replaceAll("-", "");
        StuInfo stuInfo = new StuInfo(null, stuName, "女", 1005, 20);
        System.out.println("本次插入的数据信息是："+stuInfo);
        stuInfoService.insertStuInfo(stuInfo);
    }
}
