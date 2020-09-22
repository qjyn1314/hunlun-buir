package com.calm.security.config;

import com.calm.security.Auth;
import com.calm.security.handle.AuthFailureHandler;
import com.calm.security.handle.AuthLogoutHandler;
import com.calm.security.handle.AuthSuccessHandler;
import com.calm.security.handle.AuthTokenFilter;
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

    /**
     * 安全拦截机制-核心配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                //不需要登录认证的路径-之后将配置到配置文件中
                .antMatchers("/actuator/health", "/details", "/instances/**/**",
                        "/auth/login", "/login/form", "/auth/fail", "/auth/register").permitAll()
                //其余请求都需要登录认证通过
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指form表单中input框name属性的值
//                .usernameParameter("username")
//                .passwordParameter("password")
                //登录页路径
                .loginPage(Auth.LOGIN_URL)
                //指定登录form表单的请求路径
                .loginProcessingUrl(Auth.LOGIN_FORM_URL)
                //登陆成功的处理
//                .successHandler(authSuccessHandler())
                //登录失败的处理
//                .failureHandler(authFailureHandler())
                //认证成功成功后的请求路径
                .defaultSuccessUrl(Auth.LOGIN_SUCCESS_URL,true)
                //认证失败的跳转
                .failureUrl(Auth.LOGIN_FAIL_URL)
                .and()
                .logout()
                .logoutUrl(Auth.LOGOUT_URL)
                //退出登录的处理
//                .logoutSuccessHandler(authLogoutHandler())
                .and().httpBasic()
                //跨域
                .and().cors()
                //解决跨站请求
                .and().csrf().disable();
        // 添加JWT过滤器
        http.addFilter(new AuthTokenFilter(authenticationManager()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 放过静态资源的请求
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/error/**", "/static/**", "/instances/**", "/actuator/**",
                "/font/**", "/icon/**", "/images/**", "/js/**", "/json/**", "/layui/**");
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
