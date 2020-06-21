package com.hulunbuir.clam.route.config.shiro;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.hulunbuir.clam.route.config.jwt.CurrentUser;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @author wangjunming
 * @since 2020/5/25 17:02
 */
@Configuration
//@Order(1)
public class ShiroConfig {

    /**
     * 配置kaptcha图片验证码框架提供的Servlet,,这是个坑,很多人忘记注册(注意)
     *
     * @author wangjunming
     * @since 2020/5/25 17:02
     */
    @Bean
    public ServletRegistrationBean<KaptchaServlet> kaptchaServlet() {
        ServletRegistrationBean<KaptchaServlet> servlet = new ServletRegistrationBean<>(new KaptchaServlet(), "/kaptcha.jpg");
        servlet.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);//session key
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");//字体大小
        servlet.addInitParameter(Constants.KAPTCHA_BORDER, "no");
        servlet.addInitParameter(Constants.KAPTCHA_BORDER_COLOR, "105,179,90");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "45");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        servlet.addInitParameter(Constants.KAPTCHA_IMAGE_WIDTH, "125");
        servlet.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        //可以设置很多属性,具体看com.google.code.kaptcha.Constants
//		kaptcha.border  是否有边框  默认为true  我们可以自己设置yes，no
//		kaptcha.border.color   边框颜色   默认为Color.BLACK
//		kaptcha.border.thickness  边框粗细度  默认为1
//		kaptcha.producer.impl   验证码生成器  默认为DefaultKaptcha
//		kaptcha.textproducer.impl   验证码文本生成器  默认为DefaultTextCreator
//		kaptcha.textproducer.char.string   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
//		kaptcha.textproducer.char.length   验证码文本字符长度  默认为5
//		kaptcha.textproducer.font.names    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
//		kaptcha.textproducer.font.size   验证码文本字符大小  默认为40
//		kaptcha.textproducer.font.color  验证码文本字符颜色  默认为Color.BLACK
//		kaptcha.textproducer.char.space  验证码文本字符间距  默认为2
//		kaptcha.noise.impl    验证码噪点生成对象  默认为DefaultNoise
//		kaptcha.noise.color   验证码噪点颜色   默认为Color.BLACK
//		kaptcha.obscurificator.impl   验证码样式引擎  默认为WaterRipple
//		kaptcha.word.impl   验证码文本字符渲染   默认为DefaultWordRenderer
//		kaptcha.background.impl   验证码背景生成器   默认为DefaultBackground
//		kaptcha.background.clear.from   验证码背景颜色渐进   默认为Color.LIGHT_GRAY
//		kaptcha.background.clear.to   验证码背景颜色渐进   默认为Color.WHITE
//		kaptcha.image.width   验证码图片宽度  默认为200
//		kaptcha.image.height  验证码图片高度  默认为50
        return servlet;
    }

    /**
     * 注入异常处理类
     *
     * @author wangjunming
     * @since 2020/5/25 17:03
     */
    @Bean
    public MyExceptionResolver myExceptionResolver() {
        return new MyExceptionResolver();
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     * @author wangjunming
     * @since 2020/5/25 17:29
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器
        //rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等。
        //port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
        //perms：比如/admins/user/**=perms[user：add：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：add：*,user：modify：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
        //roles：比如/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
        //anon：比如/admins/**=anon 没有参数，表示可以匿名使用。
        //authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
        //authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
        //ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
        //user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/userLogout", "userLogout");
        //配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/console.html", "user");
        //登录的接口进入拦截器进行验证用户名，密码，验证码
        filterChainDefinitionMap.put("/login", "kaptchaFilter");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;

        //开放的静态资源
        filterChainDefinitionMap.put("/favicon.ico", "anon");//网站图标
        filterChainDefinitionMap.put("/static/**", "anon");//配置static文件下资源能被访问的，这是个例子
        //需要放开的请求路径
        filterChainDefinitionMap.put("/kaptcha.jpg", "anon");//图片验证码(kaptcha框架)
        filterChainDefinitionMap.put("/", "anon");//首页
        filterChainDefinitionMap.put("/login/sendMailCode", "anon");//注册时的发送验证码
        filterChainDefinitionMap.put("/login/regUser", "anon");//注册用户

        // swagger接口文档
        filterChainDefinitionMap.put("/api/v1/**", "anon");//API接口
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/doc.html", "anon");
//        springbootAdmin的监控访问的url
//        这段是配合 actuator框架使用的，配置相应的角色才能访问
        filterChainDefinitionMap.put("/actuator/**/**", "anon");//服务器健康状况页面
        filterChainDefinitionMap.put("/health", "anon");//服务器健康状况页面
        filterChainDefinitionMap.put("/instances/**/**", "anon");
        filterChainDefinitionMap.put("/info", "anon");//服务器信息页面
        filterChainDefinitionMap.put("/env", "anon");//应用程序的环境变量
        filterChainDefinitionMap.put("/metrics", "anon");
        filterChainDefinitionMap.put("/configprops", "anon");
        filterChainDefinitionMap.put("/logfile/**/**", "anon");
        filterChainDefinitionMap.put("/liquibase/**/**", "anon");
        filterChainDefinitionMap.put("/dump/**/**", "anon");
        filterChainDefinitionMap.put("/trace/**/**", "anon");
        filterChainDefinitionMap.put("/refresh/**/**", "anon");
        filterChainDefinitionMap.put("/flyway/**/**", "anon");
        filterChainDefinitionMap.put("/httptrace/**/**", "anon");
        filterChainDefinitionMap.put("/auditevents/**/**", "anon");

        // 其他的进行认证
        filterChainDefinitionMap.put("/**", "user");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/console.html");

        // 未授权界面，不生效(详情原因看MyExceptionResolver)
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/404");

        //验证码过滤器
        Map<String, Filter> filtersMap = shiroFilterFactoryBean.getFilters();
        filtersMap.put("kaptchaFilter", new KaptchaFilter());
        filtersMap.put("userLogout", new HulunBuirLogout());
        //实现自己规则roles,这是为了实现or的效果
        //RoleFilter roleFilter = new RoleFilter();
        //filtersMap.put("roles", roleFilter);
        shiroFilterFactoryBean.setFilters(filtersMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro框架的核心管理器
     *
     * @author wangjunming
     * @since 2020/5/24 12:22
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器
//        securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        //为了解决：UnavailableSecurityManagerException
        ThreadContext.bind(securityManager);
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @author wangjunming
     * @since 2020/5/25 17:03
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     *
     * @author wangjunming
     * @since 2020/5/25 17:03
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroTool.HASH_ALGORITHM_NAME);// 散列算法:MD5
//        hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);//表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }

    /**
     * 开启shiro aop注解支持. 使用代理方式; 所以需要开启代码支持;
     *
     * @author wangjunming
     * @since 2020/5/25 17:04
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * cookie对象;
     *
     * @author wangjunming
     * @since 2020/5/25 17:04
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie(CurrentUser.USER_REMEMBER_ME);
        simpleCookie.setHttpOnly(true);
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(CurrentUser.USER_COOKIE_TIME_OUT);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     *
     * @author wangjunming
     * @since 2020/5/25 17:04
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j3Y+R1aSn5BOlAA=="));
        return cookieRememberMeManager;
    }

    /**
     * 会话管理器
     *
     * @author wangjunming
     * @since 2020/5/25 17:05
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(18000000);
        // url中是否显示session Id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(18000000);
        sessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler());
        //设置SessionIdCookie 导致认证不成功，不从新设置新的cookie,从sessionManager获取sessionIdCookie
//        sessionManager.setSessionIdCookie(simpleIdCookie());
        sessionManager.getSessionIdCookie().setName("hulunbuir-session-z-id");
        sessionManager.getSessionIdCookie().setPath("/");
        sessionManager.getSessionIdCookie().setMaxAge(60 * 60 * 24 * 7);
        return sessionManager;
    }

    /**
     * Shiro 提供了会话验证调度器，用于定期的验证会话是否已过期，如果过期将停止会话；
     * 出于性能考虑，一般情况下都是获取会话时来验证会话是否过期并停止会话的；
     * 但是如在web环境中，如果用户不主动退出是不知道会话是否过期的，因此需要定期的检测会话是否过期，
     * Shiro 提供了会话验证调度器SessionValidationScheduler来做这件事情。
     *
     * @author wangjunming
     * @since 2020/5/25 17:06
     */
    @Bean(name = "sessionValidationScheduler")
    public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(900000);
        return scheduler;
    }

/*    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("hulunBuirLogout"));
        registration.setFilter(new DelegatingFilterProxy("kaptchaFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
//        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        //支持异步
        registration.setAsyncSupported(true);
//        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.ERROR);
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        return registration;
    }*/

    /**
     * 必须（thymeleaf页面使用shiro标签控制按钮是否显示）
     * 未引入thymeleaf包，Caused by: java.lang.ClassNotFoundException: org.thymeleaf.dialect.AbstractProcessorDialect
     * @return
     */
    /*@Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }*/


}
