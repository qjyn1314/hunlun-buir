package com.hulunbuir.clam.afternoon.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirPermission;
import com.hulunbuir.clam.afternoon.persistence.qo.BuirPermissionTree;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限表 Mapper
 *
 * @author wangjunming
 * @since 2020-07-22 14:23:29
 */
@Repository
public interface BuirPermissionMapper extends BaseMapper<BuirPermission> {

    /**
     * 查询权限list
     *
     * @author wangjunming
     * @since 2020/7/29 9:29
     */
    List<BuirPermissionTree> getPermissionTree(BuirPermissionTree permissionTree);

}
