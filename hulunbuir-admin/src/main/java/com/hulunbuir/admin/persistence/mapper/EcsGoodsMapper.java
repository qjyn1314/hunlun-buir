package com.hulunbuir.admin.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hulunbuir.admin.persistence.entity.EcsGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcsGoodsMapper extends BaseMapper<EcsGoods> {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(EcsGoods record);

    int insertSelective(EcsGoods record);

    EcsGoods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(EcsGoods record);

    int updateByPrimaryKey(EcsGoods record);

    Long selectIdByForUpdate(Long id);

    EcsGoods selectByIdAndCode(@Param("id") Long id,@Param("code") String code);

}