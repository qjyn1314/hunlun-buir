package com.hulunbuir.clam.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QueryRequest implements Serializable {

    private int pageSize = 10;
    private int current = 1;

    private Date startTime;
    private Date endTime;


}
