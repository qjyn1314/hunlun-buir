package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserRole;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirUserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserRoleService;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.afternoon.vo.PermissionVo;
import com.hulunbuir.clam.common.base.QueryRequest;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表信息 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@Slf4j
@Service
public class BuirUserServiceImpl implements IBuirUserService {

    @Autowired
    private BuirUserMapper userMapper;
    @Autowired
    private IBuirUserRoleService userRoleService;


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
        final boolean userInsert = userMapper.insert(buirUser) > 0;
        BuirUserRole userRole = new BuirUserRole(buirUser);
        log.info("添加的用户和权限中间表的数据是：{}", JSON.toJSONString(userRole));
        final boolean userRoleInsert = userRoleService.saveBuirUserRole(userRole);
        return userInsert & userRoleInsert;
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
            if(null == buirUser.getRoleId()){
                throw HulunBuirException.build("请选择用户的角色！");
            }
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
        final Page<BuirUser> userPage = userMapper.selectPage(page, queryWrapper);
        userPage.getRecords().forEach(user -> {user.setRoleId(userRoleService.getOneBuirUserRole(new BuirUserRole(user.getId())).getRoleId());});
        return userPage;
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
        boolean updateUser = userMapper.updateById(buirUser) > 0;
        if(null != buirUser.getId() & null != buirUser.getRoleId()){
            boolean delUserRole = userRoleService.delUserRole(buirUser.getId());
            boolean saveUserRole = userRoleService.saveBuirUserRole(new BuirUserRole(buirUser));
            return updateUser & delUserRole & saveUserRole;
        }
        return updateUser;
    }

    /**
     * 通过用户ID删除用户
     *
     * @param buirUser
     * @author wangjunming
     * @since 2020/7/14 12:30
     */
    @Override
    @Transactional
    public boolean userDel(BuirUser buirUser) {
        boolean delUser = userMapper.deleteById(buirUser.getId()) > 0;
        boolean delUserRole = userRoleService.delUserRole(buirUser.getId());
        return delUser & delUserRole;
    }

    /**
     * 登录用户的权限查询
     *
     * @param userName
     * @author wangjunming
     * @since 2020/7/30 17:33
     */
    @Override
    public List<PermissionVo> getPermissionTreeList(String userName) {
        return permissionTreeList(userName,0);
    }

    private List<PermissionVo> permissionTreeList(String userName, Integer parentId) {
        final List<PermissionVo> permissionTreeList = userMapper.getPermissionTreeList(userName, parentId);
        for (PermissionVo permissionVo : permissionTreeList) {
            final List<PermissionVo> childPermissionTreeList = userMapper.getPermissionTreeList(userName, permissionVo.getId());
            if(null != childPermissionTreeList && childPermissionTreeList.size() > 0){
                permissionVo.setChildren(childPermissionTreeList);
            }
            permissionTreeList(userName,permissionVo.getId());
        }
        return permissionTreeList;
    }

}
