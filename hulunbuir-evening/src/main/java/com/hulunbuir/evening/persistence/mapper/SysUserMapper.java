package com.hulunbuir.evening.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.evening.persistence.entity.SysUser;
import com.hulunbuir.evening.persistence.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户表 Mapper
 *
 * @author Mr.Wang
 * @since 2020-09-18 10:33:50
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUserVo queryOne(SysUserVo sysUser);
}
