package com.hulunbuir.clam.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name(AuthConstants.HEADER_TOKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }
    private ApiInfo apiInfo() {
        String licenseUrl = "http://%s:%s/swagger-ui.html";
        String address = "127.0.0.1";
        return new ApiInfoBuilder()
                .title("Mr.Wang~--RESTful APIS")
                .description("Mr.Wang~~搭建springboot2.2.2+mybatis-plus3.3.0")
                .licenseUrl(String.format(licenseUrl, address, BuirProperties.getPort()))
                .version("1.0")
                .build();
    }
}
