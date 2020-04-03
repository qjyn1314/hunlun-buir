//package com.hulunbuir.clam.afternoon.config.shiro;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Description : Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
// * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
// */
//@Configuration
//@Component
//public class ShiroConfig {
//
//    private static String loginUrl = "/login.html";
//    private static String successUrl = "/console.html";
//    private static String unauthorizedUrl = "/";
////    private static String logoutUrl = "/logout";
//
//    /**
//     * ShiroFilterFactoryBean 处理拦截资源文件问题。
//     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
//     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager Filter Chain定义说明
//     * 1、一个URL可以配置多个Filter，使用逗号分隔
//     * 2、当设置多个过滤器时，全部验证通过，才视为通过
//     * 3、部分过滤器可指定参数，如perms，roles
//     * <p>
//     * shiroFilter：此处使用ShiroFilterFactoryBean来创建ShiroFilter过滤器；filters属性用于
//     * 定义自己的过滤器
//     *
//     * @author wangjunming
//     * @since 2020/3/23 16:48
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 登录的 url
//        shiroFilterFactoryBean.setLoginUrl(loginUrl);
//        // 登录成功后跳转的 url
//        shiroFilterFactoryBean.setSuccessUrl(successUrl);
//        // 未授权 url
//        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
//        //验证码过滤器
////        Map<String, Filter> filtersMap = shiroFilterFactoryBean.getFilters();
////        filtersMap.put("jwt", new JwtTokenFilter());
////        filtersMap.put("logouts", new LogoutFilter());
////        shiroFilterFactoryBean.setFilters(filtersMap);
//
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        //首页信息以及静态文件
//        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/login.html", "anon");
//        filterChainDefinitionMap.put("/static/**", "anon");
//        //分布式事务的配置文件
//        filterChainDefinitionMap.put("/src/main/resources/file.conf", "anon");
//        filterChainDefinitionMap.put("/src/main/resources/registry.conf", "anon");
//        // swagger接口文档--免认证 url
//        filterChainDefinitionMap.put("/v2/api-docs", "anon");
//        filterChainDefinitionMap.put("/webjars/**", "anon");
//        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
//        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//        filterChainDefinitionMap.put("/doc.html", "anon");
//        filterChainDefinitionMap.put("/csrf", "anon");
//        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
////        filterChainDefinitionMap.put(logoutUrl, "out");
//        // 自定义过滤器-拦截所有请求-除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
////        filterChainDefinitionMap.put("/**", "authc");
////        filterChainDefinitionMap.put("/**", "user");
////        filterChainDefinitionMap.put("/**", "jwt");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 安全管理器；即所有与安全有关的操作都会与SecurityManager 交互；
//     * <p>
//     * 且它管理着所有Subject；可以看出它是Shiro 的核心，它负责与后边介绍的其他组件进行
//     * 交互，如果学习过SpringMVC，你可以把它看成DispatcherServlet前端控制器；
//     *
//     * @author wangjunming
//     * @since 2020/3/23 16:48
//     */
//    @Bean
//    public SecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 配置 shiro realm 数据源管理器
//        securityManager.setRealm(afternoonsRealm());
//        // 配置 shiro session 会话管理器
//        securityManager.setSessionManager(sessionManager());
//        // 配置 shiro cacheManager 缓存管理类
//        securityManager.setCacheManager(cacheManager());
//        return securityManager;
//    }
//
//    /**
//     * 域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager
//     * 要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；
//     * 也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。
//     * <p>
//     * 可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；
//     * 可以是JDBC 实现，也可以是LDAP 实现，或者内存实现等等；由用户提供；
//     * 注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm；
//     *
//     * @author wangjunming
//     * @since 2020/3/24 10:19
//     */
//    @Bean
//    public AfternoonRealm afternoonsRealm() {
//        return new AfternoonRealm();
//    }
//
//    /**
//     * Shiro生命周期处理器
//     *
//     * @author wangjunming
//     * @since 2020/3/25 14:07
//     */
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    /**
//     * AOP这样的切面
//     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
//     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
//     *
//     * @author wangjunming
//     * @since 2020/3/25 14:07
//     */
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
//    /**
//     * 开启shiro aop注解支持. 使用代理方式; 所以需要开启代码支持;
//     * Shiro 提供了相应的注解用于权限控制，如果使用这些注解就需要使用AOP 的功能来进行判断，
//     * 如Spring AOP；Shiro 提供了Spring AOP 集成用于权限注解的解析和验证。
//     *
//     * @author wangjunming
//     * @since 2020/3/24 10:19
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//
//    /*redis配置文件中的信息*/
//
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.password}")
//    private String password;
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//    @Value("${spring.redis.database}")
//    private int database;
//
//    /**
//     * shiro 中配置 redis 缓存
//     *
//     * @author wangjunming
//     * @since 2020/3/24 10:51
//     */
//    private RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPassword(password);
//        redisManager.setTimeout(timeout);
//        redisManager.setDatabase(database);
//        return redisManager;
//    }
//
//    /**
//     * 缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本
//     * 上很少去改变，放到缓存中后可以提高访问的性能
//     *
//     * @author wangjunming
//     * @since 2020/3/24 10:51
//     */
//    private RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }
//
//
//    /**
//     * SessionDAO：DAO 大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session
//     * 保存到数据库，那么可以实现自己的SessionDAO，通过如JDBC 写到数据库；比如想把
//     * Session 放到Memcached 中，可以实现自己的Memcached SessionDAO；另外SessionDAO
//     * 中可以使用Cache进行缓存，以提高性能；
//     *
//     * @author wangjunming
//     * @since 2020/3/24 11:28
//     */
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }
//
//    /*session会话超时时间*/
//    private static long SESSION_TIMEOUT = 3000000L;
//
//    /**
//     * SessionDAO：如果写过Servlet就应该知道Session的概念，Session呢需要有人去管理
//     * 它的生命周期，这个组件就是SessionManager；而Shiro 并不仅仅可以用在Web 环境，也
//     * 可以用在如普通的JavaSE 环境、EJB 等环境；所有呢，Shiro 就抽象了一个自己的Session
//     * 来管理主体与应用之间交互的数据；这样的话，比如我们在Web 环境用，刚开始是一台
//     * Web 服务器；接着又上了台EJB 服务器；这时想把两台服务器的会话数据放到一个地方，
//     * 这个时候就可以实现自己的分布式会话（如把数据放到Memcached服务器）；
//     *
//     * @author wangjunming
//     * @since 2020/3/24 11:26
//     */
//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        // 设置 session会话超时时间
//        sessionManager.setGlobalSessionTimeout(SESSION_TIMEOUT);
//        sessionManager.setSessionDAO(redisSessionDAO());
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//        return sessionManager;
//    }
//
//}
