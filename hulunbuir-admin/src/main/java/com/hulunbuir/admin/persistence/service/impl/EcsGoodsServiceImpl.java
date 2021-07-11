package com.hulunbuir.admin.persistence.service.impl;

import com.hulunbuir.admin.persistence.entity.EcsGoods;
import com.hulunbuir.admin.persistence.mapper.EcsGoodsMapper;
import com.hulunbuir.admin.persistence.service.EcsGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/10 18:55
 */
@Service
public class EcsGoodsServiceImpl implements EcsGoodsService {

    @Autowired
    private EcsGoodsMapper ecsGoodsMapper;


    /**
     * 将当前需要修改的数据进行悲观锁的设置
     *
     * @param id 下一步进行修改的记录ID
     * @return java.lang.Long  此记录ID
     * @author wangjunming
     * @since 2021/7/10 19:14
     */
    @Override
    public Long selectIdByForUpdate(Long id) {
        return ecsGoodsMapper.selectIdByForUpdate(id);
    }

    /**
     * 查询商品信息
     *
     * @param id   商品goodid
     * @param code 商品编码
     * @return com.hulunbuir.admin.persistence.entity.EcsGoods
     * @author wangjunming
     * @since 2021/7/10 19:32
     */
    @Override
    public EcsGoods selectByIdAndCode(Long id, String code) {
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return ecsGoodsMapper.selectByIdAndCode(id,code);
    }

    /**
     * 更新商品信息
     *
     * @param ecsGoods 商品信息
     * @return boolean
     * @author wangjunming
     * @since 2021/7/11 12:45
     */
    @Override
    public boolean updateGoodsById(EcsGoods ecsGoods) {
        int update = ecsGoodsMapper.updateByPrimaryKeySelective(ecsGoods);
        return update > 0;
    }
}
