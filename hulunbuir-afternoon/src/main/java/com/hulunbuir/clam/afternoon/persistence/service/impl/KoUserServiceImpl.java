package com.hulunbuir.clam.afternoon.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hulunbuir.clam.afternoon.persistence.entity.KoUser;
import com.hulunbuir.clam.afternoon.persistence.mapper.KoUserMapper;
import com.hulunbuir.clam.afternoon.persistence.service.IKoUserService;
import com.hulunbuir.clam.distributed.evening.EveningProvider;
import com.hulunbuir.clam.distributed.model.OrgQo;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
@Slf4j
@Service
public class KoUserServiceImpl extends ServiceImpl<KoUserMapper, KoUser> implements IKoUserService {

    @Reference(check = false,timeout = 500000,retries = 0)
    private EveningProvider provider;

    /**
     * 添加用户信息，
     *
     * @param user :
     * @return boolean
     * @author wangjunming
     * @since 2020/1/18 12:04
     */
    @Override
    public boolean insertUserGlob(KoUser user) {
        int insert = this.baseMapper.insert(user);
//        int i = 10/0;
        OrgQo orgQo = new OrgQo();
        orgQo.setName("顶级组织-"+ DateUtils.getDateTimes());
        orgQo.setParentId(2);
        insert = provider.insertOrg(orgQo);
        return insert > 0;
    }

    /**
     * 添加用户信息，
     *
     * @param user :
     * @return boolean
     * @author wangjunming
     * @since 2020/1/18 12:04
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertUser(KoUser user) {
        int i = 10/0;
        return user.insert();
    }

}
