package com.hulunbuir.common.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * explain:
 *
 * 参考：
 *
 * https://blog.csdn.net/jinjiniao1/article/details/100849237
 *
 * https://www.cnblogs.com/wyq178/p/9058030.html
 *
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:55
 */
@Slf4j
@Configuration
public class RestTemplateService {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(50000);
        factory.setConnectTimeout(60000);
        RestTemplate restTemplate = new RestTemplate(factory);
        reInitMessageConverter(restTemplate);
        log.info("初始化成功_RestTemplate-{}", restTemplate);
        return restTemplate;
    }

    private void reInitMessageConverter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (item.getClass() == StringHttpMessageConverter.class) {
                converterTarget = item;
                break;
            }
        }
        if (converterTarget != null) {
            converterList.remove(converterTarget);
        }
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(converter);
    }

}
