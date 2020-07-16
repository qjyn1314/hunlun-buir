package com.hulunbuir.clam.afternoon.persistence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hulunbuir.clam.afternoon.persistence.entity.BuirUserThird;
import com.hulunbuir.clam.common.base.QueryRequest;

/**
 * <p>
 * 第三方登录与用户关联表 服务类
 * </p>
 *
 * @author wangjunming
 * @since 2020-06-12
 */
public interface IBuirUserThirdService {

    /**
     * 第三方登录与用户关联表分页列表
     *
     * @author wangjunming
     * @since 2020/7/16 10:07
     */
    IPage<BuirUserThird> buirUserThirdPage(QueryRequest queryRequest, BuirUserThird buirUserThird);


    /**
     * 保存
     *
     * @author wangjunming
     * @since 2020/7/16 11:03
     */
    boolean saveBuirUserThird(BuirUserThird buirUserThird);

    /**
     * 修改
     *
     * @author wangjunming
     * @since 2020/7/16 11:06
     */
    boolean updateBuirUserThird(BuirUserThird buirUserThird);


    /**
     * 获取单个
     *
     * @author wangjunming
     * @since 2020/7/16 11:06
     */
    BuirUserThird getOneBuirUserThird(BuirUserThird buirUserThird);


}
