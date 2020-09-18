package com.calm.security.config;

import com.calm.security.Auth;
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
 * explain:
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
                .formLogin()
                //指form表单中input框name属性的值
                .usernameParameter("username")
                .passwordParameter("password")
                //登录页路径
                .loginPage(Auth.LOGIN_URL)
                //指定登录form表单的请求路径
                .loginProcessingUrl(Auth.LOGIN_FORM_URL)
                //认证成功成功后的请求路径
                .defaultSuccessUrl(Auth.LOGIN_SUCCESS_URL)
                //认证失败的跳转
                .failureUrl(Auth.LOGIN_FAIL_URL)
                .and()
                .authorizeRequests()
                //不需要登录认证的路径-之后将配置到配置文件中
                .antMatchers("/actuator/health","/details","/instances/**/**",
                        "/auth/login", "/login/form", "/auth/fail","/auth/register").permitAll()
                //其余请求都需要登录认证通过
                .anyRequest().authenticated()
                .and() .httpBasic()
                //跨域
                .and().cors()
                //解决跨站请求
                .and().csrf().disable();
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


}
