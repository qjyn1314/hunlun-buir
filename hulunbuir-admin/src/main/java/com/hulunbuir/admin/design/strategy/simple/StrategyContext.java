package com.hulunbuir.admin.design.strategy.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 使用策略的类
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:18
 */
@Slf4j
public class StrategyContext {

    private final Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeDraw(int radius, int x, int y){
        strategy.draw(radius,x,y);
        log.info("引入策略接口，并使用相应的具体实现。");
        return radius + x + y;
    }


}
