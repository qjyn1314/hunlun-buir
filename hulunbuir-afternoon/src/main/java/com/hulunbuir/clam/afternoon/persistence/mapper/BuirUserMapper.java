package com.hulunbuir.clam.afternoon.persistence.mapper;

import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * <p>
 * 用户信息表信息 Mapper 接口
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
public interface BuirUserMapper extends BaseMapper<BuirUser> {

    BuirUser selectBuirUser(@Param("queryQo") HashMap<String, Object> queryMap);

}
