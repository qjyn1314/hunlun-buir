package com.hulunbuir.clam.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/9/16 10:57
 */
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Autowired
    private AuthIntercept authIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个拦截器
        registry.addInterceptor(authIntercept).addPathPatterns("/**");
    }
    /**
     * 静态资源的加载问题
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger-ui的资源文件访问权限
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //项目中的静态资源
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/**").addResourceLocations(
                ResourceUtils.CLASSPATH_URL_PREFIX + "/resources/",
                ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
    }

    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600 * 24);
    }

    /**
     * json转换
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        mediaTypes.add(new MediaType("application", "xml"));
        mediaTypes.add(new MediaType("text", "xml"));
        mediaTypes.add(new MediaType("application", "*+xml"));
        mediaTypes.add(MediaType.ALL);
        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Registration.class, ToStringSerializer.instance);
        simpleModule.addDeserializer(Registration.class, new RegistrationDeserializer());
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(0, jackson2HttpMessageConverter);
    }
}