package com.hulunbuir.clam.common.config;

import com.hulunbuir.clam.common.utils.CommonUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Explain:工程中的swagger文档配置
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-17 10:20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private HttpServletRequest request;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("X-Auth-Token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * 添加swagger-ui的资源文件访问权限
     *
     * @param registry:
     * @author wangjunming
     * @since 2020/1/17 10:59
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        String licenseUrl = "http://%s:%s/swagger-ui.html";
        return new ApiInfoBuilder()
                .title("称，个个棒棒哒~--RESTful APIS")
                .description("称，个个棒棒哒~~搭建springboot2.2.2+mybatis-plus3.3.0")
                .licenseUrl(String.format(CommonUtils.getIpAddr(request), licenseUrl, port))
                .version("1.0")
                .build();
    }

    /**
     * 允许多请求地址多加斜杠  比如 /msg/list   //msg/list
     *
     * @author wangjunming
     * @since 2020/5/26 15:37
     */
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }

}
