package com.hulunbuir.admin.springstudy.iocconfig;

import lombok.Data;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/2/7 23:29
 */
@Data
public class DiyDb {

    private String datasourceName;

    public DiyDb() {
    }

    public DiyDb(String datasourceName) {
        this.datasourceName = datasourceName;
    }

}
