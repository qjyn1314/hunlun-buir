package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirUserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>
 * 用户信息表信息 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@Service
public class BuirUserServiceImpl implements IBuirUserService {

    @Autowired
    private BuirUserMapper userMapper;


    /**
     * 注册用户信息
     *
     * @param buirUser
     * @author wangjunming
     * @since 2020/5/25 14:01
     */
    @Override
    @Transactional
    public boolean regUser(BuirUser buirUser) {
        return userMapper.insert(buirUser) > 0;
    }

    /**
     * 验证注册用户信息以抛出异常的方式
     *
     * @param buirUser
     * @author wangjunming
     * @since 2020/5/25 14:19
     */
    @Override
    public void validate(BuirUser buirUser,Integer type) throws HulunBuirException {
        if(Integer.valueOf("2").equals(type) || Integer.valueOf("3").equals(type)){
            if(null == buirUser.getId()){
                throw HulunBuirException.build("请传入用户ID！");
            }
            final BuirUser selectByIdUser = userMapper.selectById(buirUser.getId());
            if(null == selectByIdUser){
                throw HulunBuirException.build("未查询到该用户信息！");
            }
        }
        if(Integer.valueOf("3").equals(type)){
            Integer[] userIds = {8,9};
            final boolean contains = Arrays.asList(userIds).contains(buirUser.getId());
            if(contains){
                throw HulunBuirException.build("此用户为超级管理员，不能删除！");
            }
        }


        if(Integer.valueOf("1").equals(type) || Integer.valueOf("2").equals(type)){
            if(StringUtils.isBlank(buirUser.getNickName())){
                throw HulunBuirException.build("请填写用户昵称！");
            }
            if(StringUtils.isBlank(buirUser.getUserName())){
                throw HulunBuirException.build("请填写登录邮箱！");
            }
            if(null == buirUser.getStatus()){
                throw HulunBuirException.build("请选择用户的状态！");
            }
        }
        if(Integer.valueOf("2").equals(type)){
            if (StringUtils.isNotBlank(buirUser.getNickName())) {
                HashMap<String, Object> queryNickNameMap = new HashMap<>();
                queryNickNameMap.put("nickName", buirUser.getNickName());
                queryNickNameMap.put("id",buirUser.getId());
                queryNickNameMap.put("check",true);
                final BuirUser userNickName = userMapper.selectBuirUser(queryNickNameMap);
                if (null != userNickName) {
                    throw HulunBuirException.build("昵称已注册！");
                }
            }
        }
        if(Integer.valueOf("1").equals(type)){
            if(StringUtils.isBlank(buirUser.getPassword())){
                throw HulunBuirException.build("请填写登录密码！");
            }
            if (StringUtils.isNotBlank(buirUser.getUserName())) {
                HashMap<String, Object> queryUserNameMap = new HashMap<>();
                queryUserNameMap.put("userName", buirUser.getUserName());
                final BuirUser userMail = userMapper.selectBuirUser(queryUserNameMap);
                if (null != userMail) {
                    throw HulunBuirException.build("邮箱已注册！");
                }
            }
            if (StringUtils.isNotBlank(buirUser.getNickName())) {
                HashMap<String, Object> queryNickNameMap = new HashMap<>();
                queryNickNameMap.put("nickName", buirUser.getNickName());
                final BuirUser userNickName = userMapper.selectBuirUser(queryNickNameMap);
                if (null != userNickName) {
                    throw HulunBuirException.build("昵称已注册！");
                }
            }
        }
    }

    /**
     * 根据条件查询用户信息
     *
     * @param queryMap
     * @author wangjunming
     * @since 2020/5/25 15:07
     */
    @Override
    public BuirUser queryBuirUser(HashMap<String, Object> queryMap) {
        return userMapper.selectBuirUser(queryMap);
    }

    /**
     * 查询用户分页
     *
     * @param queryRequest
     * @param buirUser
     * @author wangjunming
     * @since 2020/6/21 22:01
     */
    @Override
    public IPage<BuirUser> userPage(QueryRequest queryRequest, BuirUser buirUser) {
        LambdaQueryWrapper<BuirUser> queryWrapper = initQueryWrapper(queryRequest,buirUser);
        Page<BuirUser> page = new Page<>(queryRequest.getCurrent(), queryRequest.getPageSize());
        return userMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<BuirUser> initQueryWrapper(QueryRequest queryRequest, BuirUser buirUser) {
        LambdaQueryWrapper<BuirUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(buirUser.getNickName())) {
            queryWrapper.like(BuirUser::getNickName, buirUser.getNickName());
        }
        if (StringUtils.isNotBlank(buirUser.getUserName())) {
            queryWrapper.like(BuirUser::getUserName, buirUser.getUserName());
        }
        if (null != buirUser.getStatus()) {
            queryWrapper.eq(BuirUser::getStatus, buirUser.getStatus());
        }
        if (null != queryRequest.getStartTime()) {
            queryWrapper.ge(BuirUser::getCreateTime, queryRequest.getStartTime());
        }
        if (null != queryRequest.getEndTime()) {
            queryWrapper.le(BuirUser::getCreateTime, queryRequest.getEndTime());
        }
        queryWrapper.orderByDesc(BuirUser::getCreateTime);
        return queryWrapper;
    }

    /**
     * 编辑用户信息
     *
     * @param buirUser
     * @author wangjunming
     * @since 2020/7/14 12:18
     */
    @Override
    @Transactional
    public boolean userEdit(BuirUser buirUser) {
        return userMapper.updateById(buirUser)>0;
    }

    /**
     * 通过用户ID删除用户
     *
     * @param buirUser
     * @author wangjunming
     * @since 2020/7/14 12:30
     */
    @Override
    public boolean userDel(BuirUser buirUser) {
        return userMapper.deleteById(buirUser.getId())>0;
    }



}
