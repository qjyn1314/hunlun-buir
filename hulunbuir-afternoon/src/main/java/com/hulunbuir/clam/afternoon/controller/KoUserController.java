package com.hulunbuir.clam.afternoon.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/koUser")
public class KoUserController {

   /* @Reference(check = false,timeout = 500000,retries = 0)
    private TongKeTicketService tongKeTicketService;

    @ApiOperation("获取当前时间")
    @GetMapping("/nowDate")
    @ResponseBody
    public String getNowDateTime() {
        String orderid = "";
        List<VcPurindtVo> vcPurindtVo = tongKeTicketService.getVcPurindtVo(orderid);
        return JSON.toJSONString(vcPurindtVo);
    }*/


}

