package com.hulunbuir.clam.admin.study;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-17 20:55
 */
public class FilterStudy implements Filter {

    /**
     * @param filterConfig
     * @throws ServletException
     *
     * 用来获取当前的过滤器信息
     * 初始化参数，以及配置信息
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        String servletContextName = servletContext.getServletContextName();
        String namespace = servletContext.getInitParameter("namespace");
    }

    /**
     * 拦截器的学习
     *
     * @param request 请求
     * @param response 重定向
     * @param chain 过滤器
     * @throws IOException
     * @throws ServletException
     *
     * 1、首先会在web.xml里面进行声明filter
     *    <filter>
     *         <filter-name>filterStudy</filter-name>
     *         <filter-class>com.FilterStudy</filter-class>
     *     </filter>
     *     <filter-mapping>
     *         <filter-name>filterStudy</filter-name>
     *        // <url-pattern>/*</url-pattern>
     *         <servlet-name>studyServlet</servlet-name>
     *     </filter-mapping>
     *
     * Filter特征：
     *      1、单例多线程的
     *      2、在应用启动的时候被加载和初始化init方法，只会被执行一次
     *      3、在应用被停止的时候执行destory方法，filter被销毁，即destroy只会被执行一次
     *
     *  url-pattern拦截：
     *      <url-pattern>/*</url-pattern>
     *      规定：若需要urlpattern为全路径匹配方式，那么url-pattern只能写为  /*  ，不能写为  /
     *      在filter-mapping中可以不使用url-pattern，但需要指定servlet-name，
     *                        即：当前过滤器拦截的是对指定servlet的请求进行拦截
     *  dispatcher：
     *  FORWORD：表示当前过滤器只会拦截由一个servlet通过,request.getRequestDispatcher("").forward(request,response); 完成的跳转
     *  INCLUDE：表示当前过滤器只会拦截由一个servlet通过,request.getRequestDispatcher("").include(request,response); 完成的跳转
     *  REQUEST：表示当前拦截器会拦截普通的请求，但对于forward与include的跳转不进行拦截，为默认值
     *  ERROR：表示跳转到指定的错误的处理页面的时候，这个跳转请求会被当前过滤器拦截
     *      <error-page>
     *         <error-code>404</error-code>
     *         <location>/study</location>
     *     </error-page>
     *
     *        //将请求放行到下一个资源
     *         chain.doFilter(request,response);
     *
     *    过滤器的作用就是将请求进行修改，对重定向进行修改
     *
     *
     *  多个filter存在的时候：
     *      其执行顺序与其注册顺序一致；
     *
     *  filter的执行原理：
     *      一个map
     *      一个数组
     *
     *  装饰者  模式的基本要求
     *
     *  1、装饰着要实现与目标类相同的接口
     *  2、装饰者类中要有目标对象作为成员变量，且目标对象是由带参构造器传入的
     *
     * 装饰者的使用目的，就是增强目标对象
     * 静态代理的使用目的，是为了保护和隐藏目标对象
     *
     * 在filter中使用装饰者模式主要是为了处理get请求的乱码问题，
     *      将声明装饰者增强类，继承HttpServletRequestWrapper重写request中获取参数的方法，每次请求将参数中的编码都进行编码
     *       post请求的：request.setCharacterEncoding("UTF-8");
     *
     *  重定向的get请求方式，post请求方式-->乱码解决方法，response.setContentType("text/html;charset=UTF-8");
     *
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.getRequestDispatcher("").forward(request,response);
        request.getRequestDispatcher("").include(request,response);
        response.setContentType("text/html;charset=UTF-8");
        //将请求放行到下一个资源
        chain.doFilter(request,response);
        request.setCharacterEncoding("UTF-8");
    }

}
