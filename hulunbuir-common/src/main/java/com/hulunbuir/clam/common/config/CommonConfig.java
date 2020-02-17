package com.hulunbuir.clam.common.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.entities.SnapshottingInstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.eventstore.InMemoryEventStore;
import de.codecentric.boot.admin.server.eventstore.InstanceEventStore;
import de.codecentric.boot.admin.server.services.*;
import de.codecentric.boot.admin.server.services.endpoints.ChainingStrategy;
import de.codecentric.boot.admin.server.services.endpoints.ProbeEndpointsStrategy;
import de.codecentric.boot.admin.server.services.endpoints.QueryIndexEndpointStrategy;
import de.codecentric.boot.admin.server.web.client.*;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Explain:
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
            registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
            registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        }
    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    // 微服务信息获取后 使用该类 注册到该系统 其中
//    //InstanceRepository这个实例 我们在这里使用的是下方一个子类SnapshottingInstanceRepository 我们获取的微服务信息 都是存到这这里
//    //前端获取的服务信息 也要从这里取
//    public InstanceRegistry instanceRegistry(InstanceRepository instanceRepository,
//                                             InstanceIdGenerator instanceIdGenerator) {
//        return new InstanceRegistry(instanceRepository, instanceIdGenerator);
//    }
//    @Bean
//    @ConditionalOnMissingBean
//    public InstanceIdGenerator instanceIdGenerator() {
//        return new HashingInstanceUrlIdGenerator();
//    }
//
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean
//    //这个是webclient 在向对应的微服务请求数据的时候 添加的头 用来做安全验证
//    public CompositeHttpHeadersProvider httpHeadersProvider(Collection<HttpHeadersProvider> delegates) {
//        if(delegates!=null&&delegates.size()>0) {
//            for(HttpHeadersProvider tt:delegates)
//                System.out.println("CompositeHttpHeadersProvider--->"+tt.getClass().getName());
//        }
//        return new CompositeHttpHeadersProvider(delegates);
//    }
//
//    @Bean
//    @Order(0)
//    @ConditionalOnMissingBean
//    public BasicAuthHttpHeaderProvider basicAuthHttpHeadersProvider() {
//        return new BasicAuthHttpHeaderProvider();
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    @ConditionalOnMissingBean
//    // 看这个类貌似 这里还自己定义了事件分发 以及自动的触发机制
//    public EndpointDetectionTrigger endpointDetectionTrigger(EndpointDetector endpointDetector,
//                                                             Publisher<InstanceEvent> events) {
//        System.out.println("EndpointDetectionTrigger--->"+events.getClass().getName()+"---hash:"+events.hashCode());
//        return new EndpointDetectionTrigger(endpointDetector, events);
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    @ConditionalOnMissingBean
//    //同样的事件触发机制
//    public InfoUpdateTrigger infoUpdateTrigger(InfoUpdater infoUpdater, Publisher<InstanceEvent> events) {
//        System.out.println("InfoUpdateTrigger--->"+events.getClass().getName()+"---hash:"+events.hashCode());
//        return new InfoUpdateTrigger(infoUpdater, events);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(InstanceEventStore.class)
//    // 事件存储
//    public InMemoryEventStore eventStore() {
//        return new InMemoryEventStore();
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "stop")
//    @ConditionalOnMissingBean(InstanceRepository.class)
//    public SnapshottingInstanceRepository instanceRepository(InstanceEventStore eventStore) {
//        return new SnapshottingInstanceRepository(eventStore);
//    }


}
