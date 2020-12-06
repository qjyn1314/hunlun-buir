package com.hulunbuir.clam.admin.study;


import com.hulunbuir.clam.admin.design.proxy.DynaProxy;
import com.hulunbuir.clam.admin.design.proxy.IHello;
import com.hulunbuir.clam.admin.design.proxy.HelloServiceImpl;

/**
 * <p>
 * 说明：巩固maven知识
 * </p >
 *
 * @author wangjunming
 * @since 2019-07-10
 */
public class MavenStudy {
    static long j;
    int         i;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    static{
        System.out.println("加载类字节码文件到内存，并只加载一次...");
        pripro();
    }

    public static void pripro(){
        System.out.println("静态代码块调用");
    }

    {
        //在每一次创建对象的之前执行，也就是在构造函数之前执行
        System.out.println("创建对象之前......");
    }

    public MavenStudy(String name) {
        this.name = name;
    }

    public MavenStudy(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public MavenStudy() {
    }

    /**
     * maven-study
     *
     * maven是什么：
     * 是构建工具，依赖管理工具，项目信息管理工具
     *
     * maven的优点--选择maven的原因：
     * 1、简单，maven暴露了一组一致、简洁的操作接口，使用maven成熟的、稳定的组件可以简化构建系统的复杂度
     * 2、交流与反馈，
     * 3、测试驱动开发，
     * 4、十分钟构建，
     * 5、持续集成，
     * 6、富有信息的工作区，
     *
     * maven的坐标，依赖，仓库，周期，插件：
     * 核心pom中的坐标：
     * 代码第一行是xml开头的，指定了xml文档的版本和编码方式
     * 标签project是所有pom的根元素，声明啦pom的相关的命名空间和xsd元素，这个标签不是必须的，但是可以让第三方的编译器给我们标签提示
     * 标签modelVersion指定了当前pom的版本，对于maven2和maven3来说只能是：4.0.0
     * 标签groupId定义了当前maven项目隶属于的实际项目
     * 标签artifactId定义了实际项目中的一个maven项目（模块），推荐做法是，将实际项目名称的前缀作为artifactId的前缀
     * 标签version定义了当前maven项目的所处的版本
     * 标签packaging定义当前maven项目的打包方式，jar、war、pom
     * 标签classfier定义帮助当前maven项目构建输出的一些附属构件
     * 标签name定义了当前maven项目的名称，这个标签不是必须的，但是为了对于用户更为友好的项目名称，推荐为每一个maven项目添加名称
     * 注意：
     * groupId、artifactId、version是必须定义的
     * packaging是可选的，默认是jar包
     * classfier是不能直接定义
     *
     * maven的依赖：
     * 引入的依赖方法：
     * 所需要依赖的jar包的坐标是放在depencendy标签内
     * groupId、artifactId、version是必须的
     * type是依赖的类型，默认是jar
     * scope：依赖的范围
     * optional：标记依赖是否可选
     * exclusions：用来排除传递性依赖
     *
     * 引入依赖的范围（控制项目所使用的classpath以及需要的class文件）：
     *
     * compile：编译依赖范围，若没有指定则默认使用该依赖范围，对于编译、测试、运行、三种classpath都有效，例子：spring-core，什么时候都需要这个依赖
     * test：测试依赖范围，对于测试的classpath有效，在编译、运行项目的时无效，例子，典型的JUnit测试
     * provided：已提供依赖范围，对于编译和测试的classpath有效，在运行项目时无效，例子，servlet-api
     * runtime：运行时依赖范围，对于测试和运行的classpath有效，在编译项目的时候无效，例子，典型的jdbc驱动实现
     * system：系统依赖范围，此依赖与三种classpath的关系和provided的依赖范围完全一致，但是对于此依赖范围时，必须通过systemPath标签显示的指定依赖文件的路径，
     *         由于此依赖不是通过maven仓库解析的，而是往往与本机系统绑定，可能造成构建的不可移植，应当谨慎使用
     * import：导入依赖范围，此依赖范围不会对三种classpath产生实际影响
     *
     * maven传递性依赖机制：
     * 引入的A依赖于B，则为传递依赖
     * 间接依赖：
     * 引入的A依赖于B，B依赖于C，则C是A的间接依赖
     *
     * maven依赖调解原则：
     * 1、路径最近者优先
     * 2、第一声明者优先
     *
     * 查看maven已解析依赖， mvn dependency:list
     * 查看maven的依赖树，mvn dependency:tree
     * 分析maven的依赖，mvn dependency:analyze  只会分析编译的主代码和测试代码需要用到的依赖，一些执行测试和运行时的依赖他发现不了的
     *
     * maven可选依赖，则是A依赖于B，B依赖于C和D，这个时候，依赖将不会传递，即C和D依赖不会影响到A，这种现象就是可选依赖，A选择C和D哪个依赖，
     *          C和D依赖只会对B产生影响，当实际应用中A所需要的依赖是C，那么就需要将C这个依赖添加到A的pom文件中
     *
     * maven仓库私服：
     *  节省自己外网的带宽，加速maven构建，部署第三方构建，提高稳定性、增强控制，降低中央仓库的负荷
     *
     * maven周期：
     * maven的生命周期就是为了对所有的构建过程进行抽象和统一
     * maven的生命周期是抽象的，也就意味着生命周期本身不做任何实际的工作，实际的任务（编译源码，清除打包的文件）是由插件完成的，
     * maven的生命周期包含了更多的步骤和更复杂的逻辑，但他们的理念都相同，生命周期是抽象了各个构建的步骤，定义了他们的次序，但是没有提供具体的实现，那么这个步骤是谁来完成的，肯定不是开发人员，
     * maven考虑到了这一点，因此他设计了插件机制，每一个构建步骤绑定了多个插件行为，而且maven为大多数的构建绑定了默认的构建插件，
     * 若当用户有特殊需求的时候则配置插件的构建行为，甚至自己编写插件
     * 命令行输入的往往对应了生命周期，
     *
     * maven拥有三套相互独立的生命周期:
     *     Clean Lifecycle 在进行真正的构建之前进行一些清理工作。
     *
     *     Default Lifecycle 构建的核心部分，编译，测试，打包，部署等等。
     *
     *     Site Lifecycle 生成项目报告，站点，发布站点。
     *
     *
     *
     *
     *
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 巩固动态代理知识
     /*   DynamicProxy dy = new DynamicProxy();
        HelloServiceImpl dynamicService = new HelloServiceImpl();
        IHello bind = (IHello) dy.bind(dynamicService);
        bind.sayHello("mr.wang");*/
        HelloServiceImpl dynamicServices = new HelloServiceImpl();
        DynaProxy dys = new DynaProxy(dynamicServices);
        IHello binds = (IHello) dys.bind();
        String k = "";
        k = binds.function("\t\t kokoko   mr.wang  \t\t  ", k);
        System.out.println(k);














    }
}


//~ Formatted by Jindent --- http://www.jindent.com
