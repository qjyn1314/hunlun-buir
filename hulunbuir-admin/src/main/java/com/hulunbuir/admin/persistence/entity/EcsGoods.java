package com.hulunbuir.admin.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcsGoods {
    private Integer goodsId;

    private Short catId;

    private String goodsSn;

    private String goodsName;

    private String goodsNameStyle;

    private Integer clickCount;

    private Short brandId;

    private String providerName;

    private Integer goodsNumber;

    private BigDecimal goodsWeight;

    private BigDecimal marketPrice;

    private Short virtualSales;

    private BigDecimal shopPrice;

    private BigDecimal promotePrice;

    private Integer promoteStartDate;

    private Integer promoteEndDate;

    private Byte warnNumber;

    private String keywords;

    private String goodsBrief;

    private String goodsDesc;

    private String goodsThumb;

    private String goodsImg;

    private String originalImg;

    private Byte isReal;

    private String extensionCode;

    private Boolean isOnSale;

    private Boolean isAloneSale;

    private Boolean isShipping;

    private Integer integral;

    private Integer addTime;

    private Short sortOrder;

    private Boolean isDelete;

    private Boolean isBest;

    private Boolean isNew;

    private Boolean isHot;

    private Boolean isPromote;

    private Byte bonusTypeId;

    private Integer lastUpdate;

    private Short goodsType;

    private String sellerNote;

    private Integer giveIntegral;

    private Integer rankIntegral;

    private Short suppliersId;

    private Boolean isCheck;

}