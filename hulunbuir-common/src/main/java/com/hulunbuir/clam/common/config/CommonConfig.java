package com.hulunbuir.clam.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Explain:公共的配置类，
 * </p >
 *
 * @author wangjunming
 * @since 2020-02-12 11:47
 */
@Configuration
public class CommonConfig {
    /**增加资源访问的路径*/
    @EnableWebMvc
    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
            registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        }
    }
    /**用于配置admin监控的信息*/
    @Configuration
    public static class JacksonMVCConfig implements WebMvcConfigurer {
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


}
