package com.hulunbuir.clam.afternoon.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirRole;
import org.springframework.stereotype.Repository;

/**
 * 角色表 Mapper
 *
 * @author wangjunming
 * @since 2020-07-23 10:09:33
 */
@Repository
public interface BuirRoleMapper extends BaseMapper<BuirRole> {

    /**
     * 查询单个角色接口
     * @author wangjunming
     * @since 2020/7/31 9:39
     */
    BuirRole getOneBuirRole(BuirRole buirRole);


}
