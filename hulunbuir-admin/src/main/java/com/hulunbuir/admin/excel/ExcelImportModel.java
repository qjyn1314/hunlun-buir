package com.hulunbuir.admin.excel;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2021/6/4 11:24
 */
@Data
public class ExcelImportModel implements Serializable {

    private Integer number;
    private Integer ratingType;
    private String scoringItem;
    private String ratingDescription;
    private Integer score;
    private BigDecimal weights;
    private String scoringDepartment;

}

