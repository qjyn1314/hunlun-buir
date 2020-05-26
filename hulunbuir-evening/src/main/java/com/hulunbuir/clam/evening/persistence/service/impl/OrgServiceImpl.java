package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hulunbuir.clam.common.base.Pages;
import com.hulunbuir.clam.distributed.afternoon.AfternoonProvider;
import com.hulunbuir.clam.distributed.model.OrgQo;
import com.hulunbuir.clam.distributed.model.UserQo;
import com.hulunbuir.clam.evening.persistence.entity.Org;
import com.hulunbuir.clam.evening.persistence.mapper.OrgMapper;
import com.hulunbuir.clam.evening.persistence.service.IOrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
@Slf4j
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

    @Reference(check = false,timeout = 500000,retries = 0)
    private AfternoonProvider afternoonProvider;


    @Override
    public Pages<Org> selectOrgPage(Pages<Org> page) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return this.baseMapper.selectOrgPage(page);
    }

    /**
     * 添加组织信息
     *
     * @param orgQo :
     * @return int
     * @author wangjunming
     * @since 2020/1/18 12:10
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrg(OrgQo orgQo) {
        Org org = new Org();
        BeanUtils.copyProperties(orgQo,org);
//        int i = 10/0;
        return this.baseMapper.insert(org);
    }

    /**
     * 添加组织信息，用于从本项目开始调用，进行分布式事务的管理
     *
     * @param byId :
     * @return void
     * @author wangjunming
     * @since 2020/2/25 16:38
     */
    @Override
    public void initOrgData(Org byId,String dateTimes) {
        byId.setName(dateTimes);
        int insert = this.baseMapper.insert(byId);
        UserQo userQo = new UserQo();
        userQo.setUserName(dateTimes);
    }


}
