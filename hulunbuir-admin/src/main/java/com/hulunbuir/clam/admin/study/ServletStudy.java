package com.hulunbuir.clam.admin.study;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-14 22:21
 */
public class ServletStudy extends HttpServlet {


    /**
     * servlet特征：
     * 1、servlet是单例多线程的
     * 2、一个servlet实例只会执行一次无参构造器与init()方法，并且是在第一次访问的时候执行
     * 3、用户每提交一次对当前servlet的请求，就会执行一次service方法
     * 4、一个servlet只会执行一次destroy方法，在应用停止的时候执行
     * 5、由于servlet是单例多线程的，所以为了保证其线程安全性，一般不为servlet创建可变的成员变量，因为每个线程都来改变这个值会出现线程安全问题
     * 6、servlet在web容器启动的时候是不会被创建的，不会被实例化的
     *
     *  servlet的执行原理：
     *      两个map：
     *          web容器中存在两个map，这两个map的key军事sevlet注册时的url-pattern值，但其value是不同的
     *          第一个map的value是servlet实例对象的引用，第二个map的value为servler-class 标签内的值，
     *          即servlet类的全限定性类名，
     *      原理：
     *      当对servlet的请求到达servlet容器时，会先对请求进行解析，使用解析出来的uri，作为比较对象，从
     *          第一个map中查找是否有相同的key
     *       若是存在相同的key，那么读取其value，即servlet对象的引用，执行该servlet的service方法
     *       若是不存在相同的key，那么再从第二个map中查找是够有相同的key，若存在，则读取value，即访问的 servlet的全限定性类名
     *       使用反射机制创建该servlet实例，并将该实例写入到第一个map中，然后执行该servlet的service方法
     *       class.forname()进行创建对象
     *       若第二个map中也没有找到同名的key，那么跳转到系统错误处理页面，404
     *
     * <p>
     * 配置servlet在启动的时候
     * 进行加载创建对象，实例化，<load-on-startup>1</load-on-startup>
     * 大于等于0 的正整数，0,正无穷；
     * 数字越小，创建对象的优先级越高，，0的级别最高
     * 若是为负数则不创建实例
     * <p>
     * servlet的请求url-pattern的模式两种以及原则：
     * 精准路径模式；
     * 可以匹配到多个url-pattern
     * /some/sss/
     * <p>
     * 通配符路径模式：
     * /*  是真正的全路径模式，可以拦截所有请求，无论是动态资源的还是静态资源的，都会进行拦截
     * /   只会拦截静态资源，对于动态资源请求是不进行拦截的
     * <p>
     * 后缀模式:
     * *.do
     * <p>
     * 若是没有拦截，则放过请求，不进行拦截，不会进入到servlet的service方法
     * <p>
     * 路径模式不能与后缀模式一起使用，若是一起使用则tomcat不会启动起来
     * <p>
     * 路径的匹配原则：
     * 1、路径通配符优先后缀的匹配原则
     * 2、精确路径优先路径通配符匹配原则
     * 3、路径匹配符与路径匹配符，路径长度优先匹配原则
     * <p>
     * httprequest请求的生命周期很短暂，每次请求所创建的实例都不一致
     * <p>
     * HttpServlerRequest请求参数：
     * 1、请求参数是放在map中的
     * 2、map中的key是请求参数的名称，为String
     *    map中的value为请求参数的所有值，为String[]类型
     * 3、使用最多的getParameter()方法，其等价于getParameterValues()[0]
     *
     *
     *
     * servlet中使用的设计模式：
     *  懒汉式的单例模式：
     *  适配器模式：GenericServlet
     *
     */
    public ServletStudy() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        *
        <servlet>
            <servlet-name>studyServlet</servlet-name>
            <servlet-class>com.StudyServlet</servlet-class>
        </servlet>
        <servlet-mapping>
            <servlet-name>studyServlet</servlet-name>
            <url-pattern>/study</url-pattern>
        </servlet-mapping>
        *
        */

        //获取的信息是servlet标签内的数据
        ServletConfig servletConfig = this.getServletConfig();
        //获取context-param标签内的内容
        this.getInitParameter("");

        req.setAttribute("name", "王俊明");
        req.setAttribute("sex", "男");
//      req.getRequestDispatcher("/nishi").forward(req,resp);
        //获取请求的url
        StringBuffer requestURL = req.getRequestURL();
        System.out.println("requestURL = " + requestURL);
        //获取请求的uri，就是url去掉请求协议及主机后的剩余部分
        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        //获取当前web应用的根路径
        String contextPath = req.getContextPath();
        System.out.println("contextPath = " + contextPath);
        //获取客户端IP
        String remoteAddr = req.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
        //请求路径的非精确部分，通配符部分
        String servletPath = req.getServletPath();
        System.out.println("servletPath = " + servletPath);
        //请求路径的精确部分
        String pathInfo = req.getPathInfo();
        System.out.println("pathInfo = " + pathInfo);

        //乱码的产生原因：
        //tomcat默认是ISO-8859-1 与前端传参的不是同一编码，这个时候出现了乱码
        //解决乱码问题
        //会设置请求中的编码问题，只能解决post请求方式的乱码
        req.setCharacterEncoding("UTF-8");
        /*
         * 解决get请求方式的乱码问题，
         * 1、将tomcat的server.xml的默认端口号链接，加上URIEncoding="UTF-8"
         * 2、
            //打散：按照原编码解析
            byte[] names = req.getParameter("name").getBytes("ISO8859-1");
            //组装：将bytes字节数组按照指定字符编码进行组装
            String s = new String(names, "UTF-8");
         */
        /*
         * 解决响应的乱码：
         * 1、
         *   resp.setContentType("text/html;charset=UTF-8");
         *   等同于：
         *   resp.setContentType("text/html");
         *   resp.setCharacterEncoding("UTF-8");
         *   一般使用：
         *   resp.setContentType("text/html;charset=UTF-8");
         *
         */
        //设置MIME的编码类型，即响应的编码类型
        resp.setContentType("text/html;charset=UTF-8");
        //标准输出流,将数据回显到浏览器的页面,会出现乱码，
        PrintWriter writer = resp.getWriter();
        writer.print("写入数据到浏览器！！");
        //将请求的URL路径变成浏览器中的请求,用于将重定向中的乱码进行解决
        URLEncoder.encode("", "UTF-8");
        URLDecoder.decode("", "UTF-8");

        //web应用内部的转发
        req.getRequestDispatcher("/other").forward(req,resp);
        req.getRequestDispatcher("/other").include(req,resp);
        //web应用外部的重定向，可以转向本项目中的其他servlet，也可以转向到其他项目的servlet
        resp.sendRedirect("/other");
        resp.sendRedirect(req.getContextPath()+"/other");

        /*
         * 转发：
         * 1、浏览器只发出一次请求，收到一次响应
         * 2、请求转发到的资源中可以直接获取到请求中所携带的数据
         * 3、浏览器地址栏显示的为用户所提交的路径
         * 4、只能跳转到当前的应用的资源中
         * 重定向：
         * 1、浏览器发出两次请求，接收到两次响应
         * 2、重定向到的资源不能直接获取到用户提交请求中所携带的数据
         * 3、浏览器地址栏显示的为重定向的地址，而非用户提交请求的路径，也正是如此，重定向的一个很重要的作用就是 ： 防止表单重复提交
         * 4、重定向不仅可以跳转到当前应用的其他资源，也可以跳转到其他应用中的资源
         *
         *
         * 应用内的请求转发来说：
         * req.getRequestDispatcher("/other").forward(req,resp);
         * req.getRequestDispatcher("/other").include(req,resp);
         *  forward 与 include 对request来说，都是一样的，都会对请求和转发进行了装饰、增强
         * 它们的不同点主要集中在响应对象response
         *
         * forward：
         *    表示向前的意思，说明当前的请求还未结束，需要继续“向前”，所以服务器就不会在第一个servlet中打开标准的输出流，所以在写入到writer中的时候就不会想浏览器中将第一个servlet中的数据输出到客户端
         *    即：使用forward方法的servlet，其标准输出流还未开启，对客户端的响应肯定不是使用forward方法的servlet给出的
         * include：
         *    表示包含的意思，说明当前的请求已经结束，可以对客户端进行响应了。其不仅可以将自己的数据写入到标准的输出流中，还可以讲其他数据包含到自己输出流中。
         *    即：使用include方法的Servlet，其标准输出流已经打开，对客户端的响应是由使用include方法的servlet给出的
         *
         * URL：统一的资源定位符
         *      一般情况下，在URL和URI中，
         *        最后一个斜杠后的部分成为资源名称，
         *        而其他部分则为资源路径
         *
         *  绝对路径：是指根据给出的访问路径可以准确的定位到资源的路径
         *           web中的绝对路径，则是指带协议的路径，即：URL
         *
         *
         *  相对路径：是指根据访问路径无法准确定位到资源路径，必须经过结合其参照路径才能准确的定位到资源的绝对路径
         *       1、以斜杠开头的路径
         *              前台路径：参照路径是：web服务器的根路径，即：http://127.0.0.1:8080
         *                      <img alt="情景" src="/ServletStudy/images/123.jpeg">
         *                  其本质访问的是：http://127.0.0.1:8080/ServletStudy/images/123.jpeg  -->所以要加上项目名称
         *              后台路径：参照路径是：web应用的根路径，即：http://127.0.0.1:8080/servlet  -->servlet是项目名称
         *              web项目的根路径是指WebContent目录
         *          一般访问的都是绝对路径： 绝对路径 = 参照路径 + 相对路径 ;
         *
         * 注意：
         * response：
         *      response的sendRedirect()方法，完成的重定向，若其参数是以斜杠开头的相对路径，
         *             这个后台路径是一个特例，是有sendRedirect()方法的本质决定的，这个方法不仅可以完成在当前项目中资源的跳转，还可以跳转到其他项目中
         *       所以这个后台的参照路径是“当前的web服务器的根”
         *      这种写法就比较好
         *      resp.sendRedirect(req.getContextPath()+"/other");
         *
         *       2、以路径名成开头的相对路径
         *             前台路径：
         *              参照路径是：当前访问路径的资源路径
         *
         *             后台路径：
         *              参照路径是：当前访问路径的资源路径
         *
         * 注意：
         * response：
         *       response的sendRedirect()方法，完成的重定向，若参数路径不以斜杠开头
         *      其实满足当前的路径理论，下面的路径可以完成跳转
         *         resp.sendRedirect(other");
         *
         * 若是相对路径加上斜杠与不加斜杠，都可以完成跳转，那么，就要加上斜杠。
         *
         *
         * servlet的线程问题：
         *      1、存在多线程并发访问
         *      2、存在可修改的共享数据
         *      在堆内存中，和方法区会让你容易出现线程安全问题
         *
         * 线程安全问题的解决方案：
         *      1、对于一般性的类，不要定义为单例的，除非项目有特殊需求，
         *          或该类对象属于重量级对象，重量级对象是指，创建该类对象时需要占用交到的系统资源，
         *      2、无论是否为单例类，尽量不要使用静态变量
         *      3、若需要定义为单例类，咋单例类中尽量不要使用成员变量
         *      4、若单例类中必须要使用曾元变量，则对成员变量的操作，需要添加上
         *           串行化锁  synchronized，实现线程同步，不过最好不使用线程同步机制，因为一旦操作进入串行化的排队状态，将大大降低程序的执行效率
         *      5、
         *
         *
         */



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
