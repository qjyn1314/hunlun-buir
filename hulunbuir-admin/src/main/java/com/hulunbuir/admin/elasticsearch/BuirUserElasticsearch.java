package com.hulunbuir.admin.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/6/1 17:39
 */
@Data
@Component
@Document(indexName = "userindex")
public class BuirUserElasticsearch implements Serializable {

    @Id
    private String id;

    private String name;

    private String password;

    private Integer status;

    private Long version;

}
