package com.hulunbuir.admin.elasticsearch;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * <p>
 * explain:  es 配置类
 * </p>
 *
 * @author wangjunming
 * @since 2021/7/3 20:09
 */
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "elasticsearch")
//public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
//
//    private String host;
//    private Integer port;
//
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        RestClientBuilder builder = RestClient.builder(new HttpHost(host,port));
//        return new RestHighLevelClient(builder);
//    }
//}
