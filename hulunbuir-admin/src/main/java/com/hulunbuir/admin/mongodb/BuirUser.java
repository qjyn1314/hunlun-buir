package com.hulunbuir.admin.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/5/31 17:43
 */
@Data
//@Document(collection = "buir_user")
public class BuirUser implements Serializable {

    @Id
    private String id;

    private String name;

    private String password;

    private Integer status;

}
