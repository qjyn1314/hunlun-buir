package com.hulunbuir.admin.persistence.service;

import com.hulunbuir.admin.persistence.entity.EcsGoods;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/10 18:55
 */
public interface EcsGoodsService {

    /**
     * 将当前需要修改的数据进行悲观锁的设置
     *
     * @param id 下一步进行修改的记录ID
     * @author wangjunming
     * @since 2021/7/10 19:14
     * @return java.lang.Long  此记录ID
     */
    Long selectIdByForUpdate(Long id);

    /**
     * 查询商品信息
     *
     * @param id 商品goodid
     * @param code 商品编码
     * @author wangjunming
     * @since 2021/7/10 19:32
     * @return com.hulunbuir.admin.persistence.entity.EcsGoods
     */
    EcsGoods selectByIdAndCode(Long id,String code);

}
