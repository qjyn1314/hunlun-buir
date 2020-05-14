package com.hulunbuir.clam.afternoon.login;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.hulunbuir.clam.afternoon.config.shiro.AfternoonShiroUtil;
import com.hulunbuir.clam.afternoon.params.qo.EmailQo;
import com.hulunbuir.clam.afternoon.params.qo.LoginUser;
import com.hulunbuir.clam.afternoon.params.qo.RegUser;
import com.hulunbuir.clam.afternoon.persistence.entity.User;
import com.hulunbuir.clam.afternoon.persistence.service.IUserService;
import com.hulunbuir.clam.common.config.RedisConfig;
import com.hulunbuir.clam.common.mail.MailConstants;
import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * Explain:呼伦贝尔大草原的下午登录控制层
 * </p >
 *
 * @author wangjunming
 * @since 2020-03-03 12:11
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class AfterLoginController {

    /**
     * 调用的dubbo服务接口，必须这样定义，check：初始化时不进行检测，timeout：超时时间，retries：重试次数
     */
    @Reference(check = false, timeout = 500000, retries = 0)
    private AdminMailProvider mailProvider;

    @Autowired
    private RedisConfig redisHelper;

    /**
     * 发送登录的邮箱验证码
     *
     * @param email:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/3/3 13:12
     */
    @ApiOperation("发送邮箱验证码")
    @PostMapping("/sendMailCode")
    public JsonResult sendMailCode(EmailQo email) {
        if (StringUtils.isBlank(email.getUserMail())) {
            return JsonResult.error("请填写正确的邮箱!!!");
        }
        String userMail = email.getUserMail();
        String randomNumbers = RandomUtil.randomNumbers(6);
        String strValue = (String) redisHelper.getStrValue(MailConstants.VERIFICATION.name() + userMail);
        if (StringUtils.isNotBlank(strValue)) {
            randomNumbers = strValue;
        }
        log.info("发送邮箱：{}，验证码{}", userMail, randomNumbers);
        redisHelper.setStrKey(MailConstants.VERIFICATION.name() + userMail, randomNumbers, 3000);
        try {
//            mailProvider.sendSimpleMail(userMail, MailConstants.VERIFICATION.getSubject(), String.format(MailConstants.VERIFICATION.getContent(), randomNumbers));
            return JsonResult.successMsg("发送成功邮箱验证码！！");
        } catch (Exception e) {
            log.error("发送邮箱验证码失败-系统异常，", e);
            return JsonResult.error("发送邮箱验证码失败，系统异常!");
        }
    }

    @Autowired
    private IUserService userService;

    /**
     * 注册用户
     *
     * @param regUser:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/2/12 17:36
     */
    @ApiOperation("注册用户")
    @PostMapping("/regUser")
    public JsonResult regUser(@Valid RegUser regUser) throws Exception {
        boolean regUserFlag = false;
        boolean vaildate = redisHelper.validationStrValue(MailConstants.VERIFICATION.name() + regUser.getUserMail());
        if (vaildate) {
            throw HulunBuirException.build("验证码已失效，请重新点击获取验证码");
        }
        String strValue = (String) redisHelper.getStrValue(MailConstants.VERIFICATION.name() + regUser.getUserMail());
        if (!strValue.equals(regUser.getVerification())) {
            throw HulunBuirException.build("验证码不正确，请输入正确的验证码！！");
        }
        try {
            User user = new User();
            BeanUtils.copyProperties(regUser, user);
            regUserFlag = userService.regUser(user);
        } catch (HulunBuirException e) {
            log.error("业务异常!!", e);
            return JsonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常!!", e);
            return JsonResult.error("系统异常!");
        }
        return regUserFlag ? JsonResult.successMsg("注册成功，请登录!") : JsonResult.successMsg("注册失败，请联系qjyn1314@163.com");
    }


    /**
     * 用户登录
     *
     * @param user:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/3/3 13:07
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public JsonResult login(@Valid LoginUser user) throws Exception {
        log.info("用户登录前端传参：{}", JSON.toJSONString(user));
        User queryUser = userService.queryUser(user.getUserMail());
        if (null == queryUser) {
            return JsonResult.error("该用户还未注册，请使用该邮箱进行注册!");
        }
        boolean matches = AfternoonShiroUtil.matches(user.getUserMail(), queryUser.getUserPassword(), queryUser.getPasswordSalt(), user.getUserPassword());
        if (matches) {
            return JsonResult.error("账号或密码不正确!");
        }
        return JsonResult.success("登录成功");
    }


}
