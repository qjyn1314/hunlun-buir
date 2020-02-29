package com.hulunbuir.clam.evening.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hulunbuir.clam.common.base.Pages;
import com.hulunbuir.clam.distributed.model.OrgQo;
import com.hulunbuir.clam.evening.persistence.entity.Org;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
public interface IOrgService extends IService<Org> {


    /**
     * 分页示例：
     * @author wangjunming
     * @since 2020/1/18 11:43
     * @param page:
     * @return com.hulunbuir.clam.common.base.Pages<com.hulunbuir.clam.evening.persistence.entity.Org>
     */
    Pages<Org> selectOrgPage(Pages<Org> page);

    /**
     * 添加组织信息-用于afternoon进行调用的分布式事务使用
     *
     * @author wangjunming
     * @since 2020/1/18 12:10
     * @param orgQo:
     * @return int
     */
    int insertOrg(OrgQo orgQo);

    /**
     * 添加组织信息，用于从本项目开始调用，进行分布式事务的管理
     * @author wangjunming
     * @since 2020/2/25 16:38
     * @param byId:
     * @param dateTimes:
     * @return void
     */
    void initOrgData(Org byId,String dateTimes);


}
