package com.hulunbuir.admin.design.decoration;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * explain: 柠檬调出的茶
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/25 17:49
 */
@Slf4j
public class Lemon implements BaseCondiment {

    private BaseBeverage bevarage;
    // 这里很关键，需要传入具体的饮料，如需要传入没有被装饰的红茶或绿茶，
    // 当然也可以传入已经装饰好的芒果绿茶，这样可以做芒果柠檬绿茶
    public Lemon(BaseBeverage bevarage) {
        this.bevarage = bevarage;
    }

    /**
     * 返回描述
     */
    @Override
    public String getDescription() {
        //原有的描述，现在在此要对已有的描述进行增强，或者加一些饮料
         String description = bevarage.getDescription();
        description =  description + "柠檬";
        log.info("柠檬调出的茶：{}",description);
        return description;
    }

    /**
     * 返回价格
     */
    @Override
    public double cost() {
        final double cost = bevarage.cost();
        log.info("柠檬调出的茶价格：{}",cost + 10);
        return cost;
    }
}
