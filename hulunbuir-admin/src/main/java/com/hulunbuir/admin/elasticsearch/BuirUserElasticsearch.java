package com.hulunbuir.admin.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
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

    @Field(type = FieldType.Keyword, analyzer = "ik_max_name")
    private String name;
    @Field(type = FieldType.Keyword, analyzer = "ik_max_password")
    private String password;
    @Field(type = FieldType.Keyword, analyzer = "ik_max_status")
    private Integer status;
    @Field(type = FieldType.Keyword, analyzer = "ik_max_version")
    private Long version;

    private String searchKey;

}
