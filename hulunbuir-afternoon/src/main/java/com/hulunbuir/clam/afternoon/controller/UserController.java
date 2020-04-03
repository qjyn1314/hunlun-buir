package com.hulunbuir.clam.afternoon.controller;


import com.hulunbuir.clam.afternoon.params.qo.LoginUser;
import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import com.hulunbuir.clam.common.config.RedisHelper;
import com.hulunbuir.clam.common.mail.MailConstants;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户表信息 前端控制器
 * </p>
 *
 * @author wangjunming
 * @since 2020-02-12
 */
@Slf4j
@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/user")
public class UserController {



}

