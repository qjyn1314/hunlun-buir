package com.hulunbuir.security.config;

import com.hulunbuir.security.handle.AuthFailureHandler;
import com.hulunbuir.security.handle.AuthLogoutHandler;
import com.hulunbuir.security.handle.AuthSuccessHandler;
import com.hulunbuir.security.handle.AuthTokenFilter;
import com.hulunbuir.security.support.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * explain:spring security 核心配置类
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/16 9:58
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户信息
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new SecurityUserService();
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**安全拦截放过的URL路径*/
    private static final String[] MATCHERS_PERMIT_ALL = {"/auth/login", "/login/form", "/auth/fail", "/auth/register",
            "/instances/**","/instances", "/actuator/**","/actuator/health", "/details"};
    /**静态资源的请求路径*/
    private static final String[] IGNORING_MATCHERS = {"/css/**", "/error/**", "/static/**", "/instances/**", "/actuator/**",
            "/font/**", "/icon/**", "/images/**", "/js/**", "/json/**", "/layui/**"};

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 安全拦截机制-form表单登录的核心配置信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 //处理前端请求跨域的问题，参考：https://blog.csdn.net/davylee2008/article/details/61420751
            http.headers().frameOptions().sameOrigin()
                //配置表单登录
                .and().formLogin()
                    //指form表单中input框name属性的值
                    .usernameParameter("username")
                    .passwordParameter("password")
                    //登录页路径
                    .loginPage(Auth.LOGIN_URL)
                    //指定登录form表单的请求路径
                    .loginProcessingUrl(Auth.LOGIN_FORM_URL)
                    //登陆成功的处理--不能与defaultSuccessUrl同时配置，如果同时配置，则优先使用defaultSuccessUrl的配置。此配置主要是将用户信息放置pc端的cookie中
                    .successHandler(authSuccessHandler())
                    //登录失败的处理--不能与failureUrl同时配置，如果同时配置，则优先使用failureUrl的配置。此配置主要是将用户的登陆失败原因返回给前端
//                    .failureHandler(authFailureHandler())
                    //认证成功成功后的请求路径
//                    .defaultSuccessUrl(Auth.LOGIN_SUCCESS_URL,true)
                    //认证失败的跳转
                    .failureUrl(Auth.LOGIN_FAIL_URL)
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
                .and().csrf().disable()
                // 添加JWT过滤器
                .addFilter(new AuthTokenFilter(authenticationManager()));
    }

    /**
     * 放过静态资源的请求
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORING_MATCHERS);
    }

    /**
     * 登陆成功处理器
     */
    @Bean
    public AuthSuccessHandler authSuccessHandler(){
        return new AuthSuccessHandler();
    }

    /**
     * 登陆失败处理器
     */
    @Bean
    public AuthFailureHandler authFailureHandler(){
        return new AuthFailureHandler();
    };

    /**
     * 退出登录处理器
     */
    @Bean
    public AuthLogoutHandler authLogoutHandler(){
        return new AuthLogoutHandler();
    };


}
