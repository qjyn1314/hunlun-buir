package com.hulunbuir.clam.admin.test_demo;

import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/6/6 20:54
 */
@Slf4j
public class AInstantTest {

    public static int staticVar = 0;
    public int instanceVar = 0;

    public AInstantTest() {

        staticVar++;

        instanceVar++;

        System.out.println("staticVar=" + staticVar + ",instanceVar=" + instanceVar);

    }

    public static void main(String[] args) {
        final Date date = new Date(1588130199000L);
        final String formatDate = DateUtils.getFormatDate(date, "yyyy-MM-dd HH:mm:ss");

        System.out.println(date);
        System.out.println(formatDate);
    }

    private static void tryCatchFinally() {
        try {
            int i = 10 / 0;
            log.info("try");
        } catch (Exception e) {
            log.info("catch");
            System.out.println(e.getMessage());
            return;
        } finally {
            log.info("finally");
            return;
        }
    }


}
