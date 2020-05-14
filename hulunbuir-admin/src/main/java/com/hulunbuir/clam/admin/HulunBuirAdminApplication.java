package com.hulunbuir.clam.admin;

import com.hulunbuir.clam.parent.tool.DateUtils;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
@Slf4j
@ComponentScan(basePackages = {
        "com.hulunbuir.clam.admin",
        "com.hulunbuir.clam.parent",
        "com.hulunbuir.clam.common",
        "com.hulunbuir.clam.route.config",
        "com.hulunbuir.clam.distributed",
})
@EnableDubbo
@Configuration
@EnableAdminServer
@SpringBootApplication
public class HulunBuirAdminApplication {

    public HulunBuirAdminApplication(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    private final AdminServerProperties adminServer;

    public static void main(String[] args) {
        log.info("开始启动-->{}", DateUtils.getDateTimes());
        SpringApplication.run(HulunBuirAdminApplication.class, args);
        log.info("启动结束-->{}", DateUtils.getDateTimes());
    }

    @Configuration
    public class SecurityAdminConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler
                    = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");

            final String adminServerContextPath = adminServer.getContextPath();
            successHandler.setDefaultTargetUrl(adminServerContextPath + "/");
            successHandler.setDefaultTargetUrl("/");
            http.authorizeRequests()
                    .antMatchers("/assets/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated().and()
                    .formLogin().loginPage("/login")
                    .successHandler(successHandler).and()
                    .logout().logoutUrl("/logout").and()
                    .httpBasic().and()
                    .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .ignoringAntMatchers(
                            "/instances",
                            "/actuator/**"
                    );
        }
    }

}
