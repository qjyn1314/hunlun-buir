package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hulunbuir.clam.common.base.Pages;
import com.hulunbuir.clam.distributed.model.OrgQo;
import com.hulunbuir.clam.evening.persistence.entity.Org;
import com.hulunbuir.clam.evening.persistence.mapper.OrgMapper;
import com.hulunbuir.clam.evening.persistence.service.IOrgService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int insertOrg(OrgQo orgQo) {
        log.info("OrgServiceImpl-->全局事务XID："+ RootContext.getXID());
        Org org = new Org();
        BeanUtils.copyProperties(orgQo,org);
        return this.baseMapper.insert(org);
    }


}
