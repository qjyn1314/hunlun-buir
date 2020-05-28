package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.mapper.BuirUserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BuirUserServiceImpl extends ServiceImpl<BuirUserMapper, BuirUser> implements IBuirUserService {

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
        return this.save(buirUser);
    }

    /**
     * 验证注册用户信息以抛出异常的方式
     *
     * @param buirUser
     * @author wangjunming
     * @since 2020/5/25 14:19
     */
    @Override
    public void validate(BuirUser buirUser) throws HulunBuirException {
        HashMap<String, Object> queryNickNameMap = new HashMap<>();
        queryNickNameMap.put("nickName", buirUser.getNickName());
        if(StringUtils.isNotBlank(buirUser.getNickName())){
            final BuirUser userNickName = this.baseMapper.selectBuirUser(queryNickNameMap);
            if (null != userNickName) {
                throw HulunBuirException.build("昵称已注册！");
            }
        }
        if(StringUtils.isNotBlank(buirUser.getUserName())){
            HashMap<String, Object> queryUserNameMap = new HashMap<>();
            queryUserNameMap.put("userName", buirUser.getUserName());
            final BuirUser userMail = this.baseMapper.selectBuirUser(queryUserNameMap);
            if (null != userMail) {
                throw HulunBuirException.build("邮箱已注册！");
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
        return this.baseMapper.selectBuirUser(queryMap);
    }
}
