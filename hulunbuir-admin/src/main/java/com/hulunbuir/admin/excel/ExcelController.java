package com.hulunbuir.admin.controller;

import com.hulunbuir.admin.worktest.SupplierPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/6/4 11:23
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class ExcelController {

    @ApiOperation("导入excel")
    @PostMapping("/import/excel/001")
    public void importExcel001(@RequestBody List<SupplierPo> supplierPo) {

        


    }


}
