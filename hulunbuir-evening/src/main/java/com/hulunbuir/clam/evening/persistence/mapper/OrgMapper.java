package com.hulunbuir.clam.evening.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.clam.common.base.Pages;
import com.hulunbuir.clam.evening.persistence.entity.Org;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
public interface OrgMapper extends BaseMapper<Org> {

    Pages<Org> selectOrgPage(Pages<Org> page);

}
