package com.hulunbuir.clam.afternoon.login;

import cn.hutool.core.util.RandomUtil;
import com.hulunbuir.clam.afternoon.params.qo.LoginUser;
import com.hulunbuir.clam.common.config.RedisHelper;
import com.hulunbuir.clam.common.config.submit.NoRepeatSubmit;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    @Reference(check = false)
    private AdminMailProvider mailProvider;

    @Autowired
    private RedisHelper redisHelper;


    /**
     * 发送登录的邮箱验证码
     *
     * @param userMail:
     * @return com.hulunbuir.clam.parent.result.JsonResult
     * @author wangjunming
     * @since 2020/3/3 13:12
     */
    @ApiOperation("发送邮箱验证码")
    @PostMapping("/sendMailCode")
    public JsonResult sendMailCode(@RequestParam String userMail) {
        if(StringUtils.isBlank(userMail)){
            return JsonResult.error("请填写正确的邮箱!!!");
        }
        String randomNumbers = RandomUtil.randomNumbers(6);
        redisHelper.setStrKey(MailConstants.VERIFICATION.name(), randomNumbers, 3000);
        try {
            mailProvider.sendSimpleMail(userMail, MailConstants.VERIFICATION.getSubject(), String.format(MailConstants.VERIFICATION.getContent(), randomNumbers));
            return JsonResult.success("发送邮箱验证码成功!!!");
        } catch (Exception e) {
            log.error("发送邮箱验证码失败-系统异常，", e);
            return JsonResult.error("发送邮箱验证码失败，系统异常!");
        }
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
        boolean vaildate = redisHelper.vaildate(MailConstants.VERIFICATION.name());
        if (vaildate) {
            throw HulunBuirException.build("验证码已失效，请重新点击获取验证码");
        }
        String strValue = (String) redisHelper.getStrValue(MailConstants.VERIFICATION.name());
        if (!strValue.equals(user.getVerification())) {
            throw HulunBuirException.build("验证码不正确，请重新点击获取验证码");
        }


        return JsonResult.success("登录成功");
    }


}
