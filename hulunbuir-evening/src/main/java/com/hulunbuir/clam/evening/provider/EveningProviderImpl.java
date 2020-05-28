package com.hulunbuir.clam.evening.provider;

import com.hulunbuir.clam.distributed.evening.EveningProvider;
import com.hulunbuir.clam.distributed.model.OrgQo;
import com.hulunbuir.clam.parent.tool.DateUtils;
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
@Service(interfaceClass = EveningProvider.class,timeout = 500000)
@Component
@Slf4j
public class EveningProviderImpl implements EveningProvider {


}
