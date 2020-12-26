package com.hulunbuir.admin.design.strategy.simple;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 使用了蓝笔画了图
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 18:16
 */
@Slf4j
public class BluePen implements Strategy{
    @Override
    public void draw(int radius, int x, int y) {
        log.info("使用蓝笔去画了一个坐标，中心点：{}，X轴：{}，Y轴：{}", radius, x, y);
    }
}
