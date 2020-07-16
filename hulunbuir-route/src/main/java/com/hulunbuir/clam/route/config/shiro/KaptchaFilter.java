package com.hulunbuir.clam.route.config.shiro;

import com.google.code.kaptcha.Constants;
import com.hulunbuir.clam.distributed.model.UserManager;
import com.hulunbuir.clam.parent.exception.HulunBuirException;
import com.hulunbuir.clam.route.config.jwt.CurrentUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 验证码过滤器此过滤器已经在shiro中配置，这里不需要再次配置拦截路径
 *
 * @author wangjunming
 * @since 2020/5/25 16:50
 */
@Slf4j
public class KaptchaFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    //登录验证
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        CaptchaUsernamePasswordToken token = createToken(request, response);
        try {
            log.info("KaptchaFilter.executeLogin");
            /*图形验证码验证*/
            doCaptchaValidate(httpServletRequest, token);
            token.setRememberMe(true);
            Subject subject = getSubject(httpServletRequest, response);
            subject.login(token);//正常验证
            //到这里就算验证成功了,把用户信息放到session中
            UserManager user = (UserManager) ShiroTool.getUser();
            log.info("登录成功的用户信息是：{}", user);
            Integer[] statues = {0,2};
            final int status = null == user ? 0 : null == user.getStatus() ? 0 : user.getStatus();
            if(Arrays.asList(statues).contains(status)){
                throw new NotApprovedException(status);
            }
            //将用户信息转换成为JWT形式的token，放到redis、cookie中，并返回给页面这个token
            httpServletResponse = CurrentUser.handleUserMessage(user, httpServletResponse);
            return onLoginSuccess(token, subject, request, httpServletResponse);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, httpServletResponse);
        }
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }

    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, "/", null, true);
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
        log.info("KaptchaFilter.doCaptchaValidate");
        //session中的图形码字符串
        String captcha = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        String tokenCaptcha = token.getCaptcha();
        log.info("session中的图形码字符串:{}", captcha);
        log.info("用户输入的图形码字符串:{}", tokenCaptcha);
        //比对
        if (captcha == null || !captcha.equalsIgnoreCase(tokenCaptcha)) {
            throw new IncorrectCaptchaException();
        }
    }

    @SneakyThrows
    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        if (StringUtils.isBlank(username)) {
            HulunBuirException.build("请填写登录邮箱！");
        }
        String password = getPassword(request);
        if (StringUtils.isBlank(password)) {
            HulunBuirException.build("请填写登录密码！");
        }
        String captcha = getCaptcha(request);
        if (StringUtils.isBlank(captcha)) {
            HulunBuirException.build("请填写验证码！");
        }
        boolean rememberMe = true;//isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    //保存异常对象到request，即，  shiroLoginFailure   则需要在login方法中进行获取不同的异常并返回给前端
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

}
