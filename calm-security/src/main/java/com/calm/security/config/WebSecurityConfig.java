package com.calm.security.config;

import com.calm.security.Auth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("l1").build());
        manager.createUser(User.withUsername("lisi").password("123").authorities("l1").build());
        return manager;
    }

    /**
     * 密码编译器
     *
     * @author wangjunming
     * @since 2020/9/16 10:34
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
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
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/login/form")
                    .defaultSuccessUrl(Auth.LOGIN_SUCCESS_URL)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/login","/login/form").permitAll()
                .anyRequest().authenticated()
            .and().csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/error/**","/static/**",
                "/font/**","/icon/**","/images/**","/js/**","/json/**","/layui/**");
    }



}
