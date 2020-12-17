package com.hulunbuir.admin.study;

/**
 * <p>
 * Explain:参考：https://www.cnblogs.com/xiaoxi/p/6164383.html
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-20 21:35
 */
public class SpringMvcStudy {


    /**
     * springmvc的处理流程：
     * springmvc的配置：
     *
     *  在web.xml中进行配置
     *      <servlet>
     *         <servlet-name>SpringMVC</servlet-name>
     *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     *         <init-param>
     *             //如果没有指定配置项，则默认使用WEB-INF/名称-servlet.xml(springmvc-servlet.xml)
     *             <param-name>contextConfigLocation</param-name>
     *             <param-value>classpath:config/spring-mvc.xml</param-value>
     *         </init-param>
     *         <load-on-startup>1</load-on-startup>
     *     </servlet>
     *     <servlet-mapping>
     *         <servlet-name>SpringMVC</servlet-name>
     *         //处理的请求拦截，
     *         <url-pattern>/</url-pattern>
     *     </servlet-mapping>
     * @param args
     */
    public static void main(String[] args) {

    }

}
