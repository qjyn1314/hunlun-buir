package com.hulunbuir.clam.afternoon.provider;

import com.hulunbuir.clam.distributed.afternoon.AfternoonProvider;
import com.hulunbuir.clam.distributed.model.UserQo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-16 13:13
 */
@Service(interfaceClass = AfternoonProvider.class,timeout = 500000)
@Component
@Slf4j
public class AfternoonProviderImpl implements AfternoonProvider {


}
