package com.hulunbuir.security.config;

import com.hulunbuir.security.handle.AuthFailureHandler;
import com.hulunbuir.security.handle.AuthLogoutHandler;
import com.hulunbuir.security.handle.AuthSuccessHandler;
import com.hulunbuir.security.support.Auth;
import com.hulunbuir.security.util.AuthUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * explain:spring security 核心配置类
 * <p>
 * 本质上是由多个过滤器链组成的
 * FilterSecurityInterceptor  最后一个执行的过滤器
 * ExceptionTranslationFilter 异常抛出的过滤器
 * UsernamePasswordAuthenticationFilter 对login的post请求拦截，检验表单中的用户名和密码
 *
 *
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/16 9:58
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户信息
     */
    @Bean
    public UserDetailsService securityUserService() {
        return new SecurityUserService();
    }

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆成功处理器
     */
    @Bean
    public AuthSuccessHandler authSuccessHandler() {
        return new AuthSuccessHandler();
    }

    /**
     * 登陆失败处理器
     */
    @Bean
    public AuthFailureHandler authFailureHandler() {
        return new AuthFailureHandler();
    }


    /**
     * 退出登录处理器
     */
    @Bean
    public AuthLogoutHandler authLogoutHandler() {
        return new AuthLogoutHandler();
    }

    /**
     * 安全拦截放过的URL路径
     */
    private static final String[] MATCHERS_PERMIT_ALL = {"/auth/login", "/login/form", "/auth/fail", "/auth/register",
            "/instances/**", "/instances", "/actuator/**", "/actuator/health", "/details"};
    /**
     * 静态资源的请求路径
     */
    private static final String[] IGNORING_MATCHERS = {"/css/**", "/error/**", "/static/**", "/instances/**", "/actuator/**",
            "/font/**", "/icon/**", "/images/**", "/js/**", "/json/**", "/layui/**"};

    /**
     * 用户认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserService())
            .passwordEncoder(passwordEncoder());
    }

    /**
     * 权限拦截
     * <p>
     * 安全拦截机制-form表单登录的核心配置信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //未授权的页面
        http.exceptionHandling().accessDeniedPage("/error/403");
        //跨域的支持
        // --https://blog.csdn.net/qq_42007742/article/details/106390553
        // -- https://www.cnblogs.com/famary/p/10336223.html
        // -- https://blog.csdn.net/u013185616/article/details/70446392
        // -- https://blog.csdn.net/caplike/article/details/106144789
        http.cors();
        //处理前端请求跨域的问题，参考：https://blog.csdn.net/davylee2008/article/details/61420751
        http.headers().frameOptions().sameOrigin()
                //配置表单登录
                .and().formLogin()
                //指form表单中input框name属性的值
//                    .usernameParameter("username")
//                    .passwordParameter("password")
                //登录页路径
                .loginPage(Auth.LOGIN_URL)
                //指定登录form表单的请求路径
                .loginProcessingUrl(Auth.LOGIN_FORM_URL)
                //登陆成功的处理--不能与defaultSuccessUrl同时配置，如果同时配置，则优先使用defaultSuccessUrl的配置。此配置主要是将用户信息放置pc端的cookie中
                .successHandler(authSuccessHandler())
                //登录失败的处理--不能与failureUrl/同时配置，如果同时配置，则优先使用failureUrl的配置。此配置主要是将用户的登陆失败原因返回给前端
                .failureHandler(authFailureHandler())
                //认证成功成功后的请求路径
//                    .defaultSuccessUrl(Auth.LOGIN_SUCCESS_URL,true)
                //认证失败的跳转
//                    .failureUrl(Auth.LOGIN_FAIL_URL)
                .and()
                .logout()
                .logoutUrl(Auth.LOGOUT_URL)
                //退出登录的处理--不能与logoutSuccessUrl同时使用，如果同时配置，则优先使用logoutSuccessUrl的配置。此配置主要是将pc端的cookie中用户信息进行清除
                .logoutSuccessHandler(authLogoutHandler())
                //退出登录成功的跳转路径
//                    .logoutSuccessUrl(Auth.LOGIN_URL)
                //配置拦截的路径
                .and()
                //不需要登录认证的路径-之后将配置到配置文件中
                .authorizeRequests().antMatchers(MATCHERS_PERMIT_ALL).permitAll()
                //其余请求都需要登录认证通过
                .anyRequest().authenticated()
                //跨域
                .and().cors()
                //禁用跨站csrf攻击防御
                .and().csrf().disable();
                // 添加JWT过滤器
//        http.addFilterAt(new AuthTokenFilter(),);
        //开启记住我的功能，默认时间是两周
        http.rememberMe()
                .alwaysRemember(Boolean.TRUE)
                //cookie的过期秒数
                .tokenValiditySeconds(AuthUserUtil.AUTH_COOKIE_TIME)
                //form表单中的记住我input框的name属性值
                .rememberMeCookieName("hulunbuir_user")
//                .key(AuthUserUtil.AUTH_TOKEN_KEY)
                //配置用户service，用于在关闭浏览器再次打开时，使用此service型数据库中根据名称查询用户数据，
                .userDetailsService(userDetailsService());
    }

    /**
     * 放过静态资源的请求
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORING_MATCHERS);
    }


}
