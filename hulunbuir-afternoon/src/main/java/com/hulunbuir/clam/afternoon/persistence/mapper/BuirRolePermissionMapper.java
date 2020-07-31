package com.hulunbuir.clam.afternoon.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirRolePermission;
import org.springframework.stereotype.Repository;

/**
 * 角色权限关联表 Mapper
 *
 * @author wangjunming
 * @since 2020-07-23 17:58:42
 */
@Repository
public interface BuirRolePermissionMapper extends BaseMapper<BuirRolePermission> {

    boolean deleteByRoleId(Integer roleId);

}
