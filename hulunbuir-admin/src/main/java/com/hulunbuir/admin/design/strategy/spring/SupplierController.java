package com.hulunbuir.admin.design.strategy.spring;

import com.hulunbuir.admin.design.strategy.spring.client.SupplierContext;
import com.hulunbuir.admin.design.strategy.spring.model.SupplierDto;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierDecorationService;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierService;
import com.hulunbuir.parent.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * explain: 使用策略模式，+ 装饰者模式。进行保存供应商信息
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/26 20:51
 */
@RestController
public class SupplierController {

    @Autowired
    private SupplierContext<SupplierDto> supplierContext;

    @Autowired
    private SupplierDecorationService<SupplierDto> supplierDecorationService;

    /**
     * 使用被装饰的类保存供应商信息
     *
     * @author wangjunming
     * @since 2020/12/26 21:02
     */
    @PostMapping("/save")
    public JsonResult save(@RequestBody SupplierDto supplierDto) {
        Boolean flag = supplierContext.doHandler(SupplierService.ALTERNATIVE, supplierDto);
        return JsonResult.success(flag);
    }

    /**
     * 使用装饰的类保存供应商信息
     *
     * @author wangjunming
     * @since 2020/12/26 21:02
     */
    @PostMapping("/saveByDecoration")
    public JsonResult saveByDecoration(@RequestBody SupplierDto supplierDto) {
        final Boolean flag = supplierContext.doDecorationHandler(SupplierDecorationService.ALTERNATIVE_DECORATION, supplierDto);
        return JsonResult.success(flag);
    }


}
