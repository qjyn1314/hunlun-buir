package com.hulunbuir.clam.afternoon.login;

import cn.hutool.core.util.RandomUtil;
import com.hulunbuir.clam.afternoon.params.qo.EmailQo;
import com.hulunbuir.clam.afternoon.params.qo.RegUser;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUser;
import com.hulunbuir.clam.afternoon.persistence.service.IBuirUserService;
import com.hulunbuir.clam.common.config.RedisConfig;
import com.hulunbuir.clam.common.mail.MailConstants;
import com.hulunbuir.clam.distributed.admin.AdminMailProvider;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.parent.result.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
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
public class RegisteredController {

    /**
     * 调用的dubbo服务接口，必须这样定义，check：初始化时不进行检测，timeout：超时时间，retries：重试次数
     */
    @Reference
    private AdminMailProvider mailProvider;

    @Autowired
    private RedisConfig redisHelper;
    @Autowired
    private IBuirUserService buirUserService;

    /**
     * 发送注册的邮箱验证码
     *
     * @param email:
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
        try {
            buirUserService.validate(BuirUser.buildByMail(userMail),0);
        } catch (HulunBuirException e) {
            return JsonResult.error(e.getMessage());
        }
        String randomNumbers = RandomUtil.randomNumbers(6);
        String strValue = (String) redisHelper.getStrValue(MailConstants.VERIFICATION.name() + userMail);
        if (StringUtils.isNotBlank(strValue)) {
            randomNumbers = strValue;
        }
        log.info("发送邮箱：{}，验证码：{}", userMail, randomNumbers);
        redisHelper.setStrKey(MailConstants.VERIFICATION.name() + userMail, randomNumbers, 3000);
        try {
//            mailProvider.sendSimpleMail(userMail, MailConstants.VERIFICATION.getSubject(), String.format(MailConstants.VERIFICATION.getContent(), randomNumbers));
            return JsonResult.successMsg("已发送邮箱验证码！！验证码是："+randomNumbers);
        } catch (Exception e) {
            log.error("发送邮箱验证码失败-系统异常，", e);
            return JsonResult.error("发送邮箱验证码失败，系统异常!");
        }
    }

    /**
     * 注册用户
     *
     * @param regUser:
     * @author wangjunming
     * @since 2020/2/12 17:36
     */
    @ApiOperation("注册用户")
    @PostMapping("/regUser")
    public JsonResult regUser(@Valid RegUser regUser) throws Exception {
        boolean regUserFlag = false;
        boolean validate = redisHelper.validationStrValue(MailConstants.VERIFICATION.name() + regUser.getUserMail());
        if (validate) {
            throw HulunBuirException.build("验证码已失效，请重新点击获取验证码");
        }
        String strValue = (String) redisHelper.getStrValue(MailConstants.VERIFICATION.name() + regUser.getUserMail());
        if (!strValue.equals(regUser.getVerification())) {
            throw HulunBuirException.build("验证码不正确，请输入正确的验证码！！");
        }
        try {
            BuirUser buirUser = new BuirUser(regUser);
            buirUserService.validate(buirUser,1);
            regUserFlag = buirUserService.regUser(buirUser);
        } catch (HulunBuirException e) {
            log.error("业务异常!!", e);
            return JsonResult.error(e.getMessage());
        } catch (Exception e) {
            log.error("系统异常!!", e);
            return JsonResult.error("系统异常!");
        }
        return regUserFlag ? JsonResult.successMsg("注册成功，请登录!") : JsonResult.successMsg("注册失败，请联系qjyn1314@163.com");
    }


}
