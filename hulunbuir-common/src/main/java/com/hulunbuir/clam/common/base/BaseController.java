package com.hulunbuir.clam.common.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 14:25
 */
public abstract class BaseController {

    /**
     * 返回的分页列表
     *
     * @author wangjunming
     * @since 2020/3/23 16:29
     */
    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
//        page: 1 页码
//        pageSize: 10 每页条数
//        totalPage: 0 总页数 (total + pageSize - 1)/pageSize
//        totalRows: 0 总条数
//        rows: []
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("page",pageInfo.getCurrent());
        rspData.put("pageSize", pageInfo.getSize());
        rspData.put("totalPage", ((pageInfo.getTotal() +pageInfo.getSize() -1) / pageInfo.getSize()));
        rspData.put("totalRows", pageInfo.getTotal());
        rspData.put("rows", pageInfo.getRecords());
        return rspData;
    }

}
