package com.hulunbuir.clam.evening.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.hulunbuir.clam.distributed.evening.EveningProvider;
import com.hulunbuir.clam.parent.tool.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 13:12
 */
@Service
@Component
@Slf4j
public class EveningProviderImpl implements EveningProvider {


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
}
