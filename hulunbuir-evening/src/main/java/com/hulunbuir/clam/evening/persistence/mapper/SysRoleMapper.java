package com.hulunbuir.clam.evening.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.clam.evening.persistence.entity.SysRole;
import com.hulunbuir.clam.evening.persistence.vo.SysRoleVo;
import org.springframework.stereotype.Repository;

/**
 * 角色表 Mapper
 *
 * @author Mr.Wang
 * @since 2020-09-22 11:04:50
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    SysRoleVo selOne(SysRole sysRole);

}
