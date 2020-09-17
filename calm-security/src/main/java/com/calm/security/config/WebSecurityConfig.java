package com.calm.security.config;

import com.calm.security.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/16 9:58
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户信息
     *
     * @author wangjunming
     * @since 2020/9/16 10:33
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password(passwordEncoder().encode("123")).authorities("/").build());
        manager.createUser(User.withUsername("lisi").password(passwordEncoder().encode("456")).authorities("/").build());
        return manager;
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制
     *
     * @author wangjunming
     * @since 2020/9/16 10:34
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                //登录页路径
                .loginPage("/auth/login")
                //指定登录form表单的请求路径
                .loginProcessingUrl("/login/form")
                //认证成功成功后的跳转，为true是可以在任何时候都可以跳转
                .defaultSuccessUrl(Auth.LOGIN_SUCCESS_URL,true)
                //认证失败的跳转
                .failureUrl("/auth/fail")
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login", "/login/form", "/auth/fail").permitAll()
                .anyRequest().authenticated()
                //跨域
                .and().cors()
                //解决跨站请求
                .and().csrf().disable();
    }

    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/error/**", "/static/**",
                "/font/**", "/icon/**", "/images/**", "/js/**", "/json/**", "/layui/**");
    }


}
