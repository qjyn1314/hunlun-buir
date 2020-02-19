package com.hulunbuir.clam.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.netty.http.client.HttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @EnableWebMvc
    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
            registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        }
    }

    @Configuration
    public static class JacksonMVCConfig implements WebMvcConfigurer {
        @Override
        public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
            MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.APPLICATION_JSON);
            mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
            mediaTypes.add(MediaType.TEXT_HTML);
            mediaTypes.add(new MediaType("application", "xml"));
            mediaTypes.add(new MediaType("text", "xml"));
            mediaTypes.add(new MediaType("application", "*+xml"));
            mediaTypes.add(MediaType.ALL);
            jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
            ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
            simpleModule.addSerializer(Registration.class, ToStringSerializer.instance);
            simpleModule.addDeserializer(Registration.class, new RegistrationDeserializer());
            objectMapper.registerModule(simpleModule);
            jackson2HttpMessageConverter.setObjectMapper(objectMapper);
            converters.add(0, jackson2HttpMessageConverter);
        }
    }

    /*@Bean
    public ReactorClientHttpConnector reactorClientHttpConnector() {
        return new ReactorClientHttpConnector(HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient
                        .bootstrap(bootstrap -> bootstrap
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000))
                        .doOnConnected(connection -> connection
                                .addHandler(new ReadTimeoutHandler(300000, TimeUnit.SECONDS))
                                .addHandler(
                                        new WriteTimeoutHandler(300000, TimeUnit.SECONDS)))));
    }*/

}
