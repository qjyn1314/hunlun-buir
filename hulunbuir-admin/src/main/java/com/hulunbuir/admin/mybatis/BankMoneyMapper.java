package com.hulunbuir.admin.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/4/13 16:24
 */
public interface BankMoneyMapper {

    List<Bankmoney> bankMoneyLists(@Param("map") HashMap<String, Object> map);


}
