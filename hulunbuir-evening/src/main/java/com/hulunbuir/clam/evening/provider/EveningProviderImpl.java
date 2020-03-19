package com.hulunbuir.clam.evening.provider;

import com.hulunbuir.clam.distributed.evening.EveningProvider;
import com.hulunbuir.clam.distributed.model.OrgQo;
import com.hulunbuir.clam.evening.persistence.service.IOrgService;
import com.hulunbuir.clam.parent.tool.DateUtils;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 13:12
 */
@Service(interfaceClass = EveningProvider.class)
@Component
@Slf4j
public class EveningProviderImpl implements EveningProvider {

    @Autowired
    private IOrgService orgService;

    /**
     * 获取当前时间
     *
     * @return java.lang.String
     * @author wangjunming
     * @since 2020/1/17 10:17
     */
    @Override
    public String getDateTimes() {
        return DateUtils.getDateTimes();
    }

    /**
     * 添加组织信息
     *
     * @param orgQo :
     * @return int
     * @author wangjunming
     * @since 2020/1/18 12:09
     */
    @Override
    public int insertOrg(OrgQo orgQo) {
        log.info("EveningProviderImpl--->全局事务XID："+ RootContext.getXID());
//        int i = 10/0;
        return orgService.insertOrg(orgQo);
    }
}
