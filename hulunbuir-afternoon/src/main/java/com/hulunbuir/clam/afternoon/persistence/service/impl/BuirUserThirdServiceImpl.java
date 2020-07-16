package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserThird;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirUserThirdMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserThirdService;
import com.hulunbuir.clam.common.base.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 第三方登录与用户关联表 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-06-12
 */
@Service
public class BuirUserThirdServiceImpl implements IBuirUserThirdService {

    @Autowired
    private BuirUserThirdMapper buirUserThirdMapper;

    /**
     * 第三方登录与用户关联表分页列表
     *
     * @param queryRequest
     * @param buirUserThird
     * @author wangjunming
     * @since 2020/7/16 10:07
     */
    @Override
    public IPage<BuirUserThird> buirUserThirdPage(QueryRequest queryRequest, BuirUserThird buirUserThird) {
        LambdaQueryWrapper<BuirUserThird> queryWrapper = initQueryWrapper(queryRequest, buirUserThird);
        Page<BuirUserThird> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return buirUserThirdMapper.selectPage(page, queryWrapper);
    }

    /**
     * 列表的查询参数
     *
     * @author wangjunming
     * @since 2020/7/16 10:16
     */
    private LambdaQueryWrapper<BuirUserThird> initQueryWrapper(QueryRequest queryRequest, BuirUserThird buirUserThird) {
        LambdaQueryWrapper<BuirUserThird> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件

        return queryWrapper;
    }

    /**
     * 保存
     *
     * @param buirUserThird
     * @author wangjunming
     * @since 2020/7/16 11:03
     */
    @Override
    @Transactional
    public boolean saveBuirUserThird(BuirUserThird buirUserThird) {
        //--TODO 做一些初始化动作
        return buirUserThirdMapper.insert(buirUserThird) > 0;
    }

    /**
     * 修改
     *
     * @param buirUserThird
     * @author wangjunming
     * @since 2020/7/16 11:06
     */
    @Override
    @Transactional
    public boolean updateBuirUserThird(BuirUserThird buirUserThird) {
        //--TODO 做一些效验动作
        return buirUserThirdMapper.updateById(buirUserThird) > 0;
    }

    /**
     * 获取单个
     *
     * @param buirUserThird
     * @author wangjunming
     * @since 2020/7/16 11:06
     */
    @Override
    public BuirUserThird getOneBuirUserThird(BuirUserThird buirUserThird) {
        LambdaQueryWrapper<BuirUserThird> queryWrapper = new LambdaQueryWrapper<>();
        //--初始化查询条件
        return buirUserThirdMapper.selectOne(queryWrapper);
    }


}
