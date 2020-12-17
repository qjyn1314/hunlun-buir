package com.hulunbuir.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/1 20:55
 */
@Slf4j
@Configuration
public class RestTemplateService {

    //连接超时时间
    private static Integer connectionTimeout = 50000;
    // 信息读取超时时间
    private static Integer readTimeout = 10000;
    private static RestTemplate restTemplates;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param reqParam
     * @param hasHeader
     * @param headersMap
     * @param method
     * @param url
     * @return String
     * @throws Exception ada
     * @author wangjunming
     * @since 2020/12/1 21:05
     */
    public static String restRequest(Object reqParam, Boolean hasHeader, HashMap<String, String> headersMap, HttpMethod method, String url) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            //设置token值
            if (hasHeader) {
                final Set<Map.Entry<String, String>> headersEntries = headersMap.entrySet();
                headersEntries.forEach(headersMaps -> {
                    headers.add(headersMaps.getKey(), headersMaps.getValue());
                });
            }
            RequestEntity<Object> request = null;
            if (null != reqParam) {
                request = new RequestEntity<>(reqParam, headers, method, new URI(url));
            } else {
                request = new RequestEntity<>(headers, method, new URI(url));
            }
            ResponseEntity<String> resp = restTemplates.exchange(request, new ParameterizedTypeReference<String>() {

            });
            System.out.println("resp status:" + resp.getStatusCode());
            if (resp.getStatusCodeValue() == 200) {
                return resp.getBody();
            }
        } catch (Exception e) {
            throw new Exception("认证服务失败！");
        }
        return null;
    }

    @PostConstruct
    public void init() {
        restTemplates = restTemplate;
    }

    /**
     * 注册restTemplate服务
     *
     * @author wangjunming
     * @since 2020/12/1 20:57
     */
    @Bean
    public RestTemplate registerTemplate() {
        RestTemplate restTemplate = new RestTemplate(getFactory());
        //这个地方需要配置消息转换器，不然收到消息后转换会出现异常
        restTemplate.setMessageConverters(getConverts());
        return restTemplate;
    }

    /**
     * 初始化请求工厂
     *
     * @author wangjunming
     * @since 2020/12/1 20:57
     */
    private SimpleClientHttpRequestFactory getFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

    /**
     * 设置数据转换器，我再这里只设置了String转换器
     *
     * @author wangjunming
     * @since 2020/12/1 20:57
     */
    private List<HttpMessageConverter<?>> getConverts() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        // String转换器
        StringHttpMessageConverter stringConvert = new StringHttpMessageConverter();
        List<MediaType> stringMediaTypes = new ArrayList<MediaType>() {{
            //配置text/plain和text/html类型的数据都转成String
            add(new MediaType("text", "plain", Charset.forName("UTF-8")));
            add(MediaType.TEXT_HTML);
        }};
        stringConvert.setSupportedMediaTypes(stringMediaTypes);
        messageConverters.add(stringConvert);
        return messageConverters;
    }


}
