package com.hulunbuir.clam.evening.persistence.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hulunbuir.clam.common.base.Pages;
import com.hulunbuir.clam.evening.persistence.entity.Org;
import com.hulunbuir.clam.evening.persistence.mapper.OrgMapper;
import com.hulunbuir.clam.evening.persistence.service.IOrgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {


    public Pages<Org> selectOrgPage(Pages<Org> page) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return this.baseMapper.selectOrgPage(page);
    }


}
