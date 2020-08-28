package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.evening.persistence.entity.HulunbuirUser;
import com.hulunbuir.clam.evening.persistence.mapper.HulunbuirUserMapper;
import com.hulunbuir.clam.evening.persistence.service.IHulunbuirUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表 Service实现
 *
 * @author Mr.Wang
 * @since 2020-08-13 13:40:19
 */
@Service
public class HulunbuirUserServiceImpl implements IHulunbuirUserService {

    @Autowired
    private HulunbuirUserMapper hulunbuirUserMapper;

   /**
    * 用户表分页列表
    *
    * @param queryRequest
    * @param hulunbuirUser
    * @author Mr.Wang
    * @since 2020-08-13 13:40:19
    */
    @Override
    public IPage<HulunbuirUser> page(QueryRequest queryRequest, HulunbuirUser hulunbuirUser) {
        LambdaQueryWrapper<HulunbuirUser> queryWrapper = initQueryWrapper(queryRequest,hulunbuirUser);
        Page<HulunbuirUser> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return hulunbuirUserMapper.selectPage(page, queryWrapper);
    }

    /**
    * 列表的查询参数
    *
    * @author Mr.Wang
    * @since 2020-08-13 13:40:19
    */
    private LambdaQueryWrapper<HulunbuirUser> initQueryWrapper(QueryRequest queryRequest, HulunbuirUser hulunbuirUser) {
        LambdaQueryWrapper<HulunbuirUser> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO  添加查询条件
        return queryWrapper;
    }

   /**
    * 保存
    *
    * @param hulunbuirUser
    * @author Mr.Wang
    * @since 2020-08-13 13:40:19
    */
    @Override
    @Transactional
    public boolean save(HulunbuirUser hulunbuirUser) {
        //--TODO 做一些初始化动作
        return hulunbuirUserMapper.insert(hulunbuirUser)>0;
    }

   /**
    * 修改
    *
    * @param hulunbuirUser
    * @author Mr.Wang
    * @since 2020-08-13 13:40:19
    */
    @Override
    @Transactional
    public boolean update(HulunbuirUser hulunbuirUser) {
        //--TODO 做一些效验动作
        return hulunbuirUserMapper.updateById(hulunbuirUser)>0;
    }

   /**
    * 获取单个
    *
    * @param hulunbuirUser
    * @author Mr.Wang
    * @since 2020-08-13 13:40:19
    */
    @Override
    public HulunbuirUser selOne(HulunbuirUser hulunbuirUser) {
    LambdaQueryWrapper<HulunbuirUser> queryWrapper = new LambdaQueryWrapper<>();
        //--TODO 初始化查询条件
        return hulunbuirUserMapper.selectOne(queryWrapper);
    }


}
