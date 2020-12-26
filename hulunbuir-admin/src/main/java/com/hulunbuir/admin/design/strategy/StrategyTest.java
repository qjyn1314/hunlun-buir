package com.hulunbuir.admin.design.strategy;

import com.hulunbuir.admin.design.strategy.simple.BluePen;
import com.hulunbuir.admin.design.strategy.simple.StrategyContext;
import com.hulunbuir.admin.design.strategy.spring.SupplierController;
import com.hulunbuir.admin.design.strategy.spring.model.SupplierDto;
import com.hulunbuir.parent.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * explain: 策略设计模式
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/16 10:54
 */
@Slf4j
@Component
public class StrategyTest {

    public static void main(String[] args) {
        BluePen bluePen = new BluePen();
        StrategyContext strategyContext = new StrategyContext(bluePen);
        final int executeDraw = strategyContext.executeDraw(10, 15, 10);
        log.info("简单的策略模式示例，结果是：{}", executeDraw);
    }

    @Autowired
    private SupplierController supplierController;

    /**
     * 测试策略模式
     */
//    @Scheduled(cron = "0/15 * * * * ?")
    public void checkStrategy() {
        SupplierDto supplierDto = new SupplierDto();
        final JsonResult jsonResult = supplierController.save(supplierDto);
        log.info("测试使用策略模式保存供应商信息的结果是：{}", jsonResult);
    }


}
