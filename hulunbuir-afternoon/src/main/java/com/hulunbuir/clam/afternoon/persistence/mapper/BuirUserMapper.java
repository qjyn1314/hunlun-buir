package com.hulunbuir.clam.afternoon.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.vo.PermissionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表信息 Mapper 接口
 * </p>
 *
 * @author wangjunming
 * @since 2020-05-25
 */
@Repository
public interface BuirUserMapper extends BaseMapper<BuirUser> {

    BuirUser selectBuirUser(@Param("queryQo") HashMap<String, Object> queryMap);


    List<PermissionVo> getPermissionTreeList(@Param("userName")String userName, @Param("parentId")Integer parentId);

}
