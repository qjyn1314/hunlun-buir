package com.hulunbuir.admin.design.strategy.spring.client;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierEnum;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierDecorationService;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierService;
import com.hulunbuir.common.config.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * explain: 通过spring将
 * </p>
 *
 * @author wangjunming
 * @since 2020/12/26 19:58
 */
@Slf4j
@Configuration
@DependsOn("applicationUtil")
public class SupplierContext<T> implements InitializingBean {

    /**被装饰的类的具体实现bean*/
    private Map<String, SupplierService> supplierHandle = new ConcurrentHashMap<>();
    /**装饰的类的具体实现bean*/
    private Map<String, SupplierDecorationService> supplierDecorationHandle = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() {
        supplierHandle = ApplicationUtil.getApplicationContext().getBeansOfType(SupplierService.class);
        supplierDecorationHandle = ApplicationUtil.getApplicationContext().getBeansOfType(SupplierDecorationService.class);
        log.info("通过[getBeansOfType]获取到的[被装饰的类的具体实现bean]是：{}", supplierHandle);
        log.info("通过[getBeansOfType]获取到的[装饰的类的具体实现bean]是：{}", supplierDecorationHandle);
    }

    /**
     * 使用map中所定的策略在此根据key获取指定的具体实现类，此处使用被装饰的类进行处理
     *
     * @param tag 策略的标识
     * @param t 策略所需要的参数
     */
    public Boolean doHandler(String tag,T t) {
        final SupplierEnum supplierEnum = supplierHandle.get(tag).getType();
        log.info("此次保存供应商使用的[SupplierEnum]是:{}", supplierEnum);
        return supplierHandle.get(tag).saveSupplier(t);
    }

    /**
     * 使用map中所定的策略在此根据key获取指定的具体实现类，此处使用装饰的类进行处理
     *
     * @param tag 策略的标识
     * @param t 策略所需要的参数
     */
    public Boolean doDecorationHandler(String tag,T t) {
        final SupplierEnum supplierEnum = supplierHandle.get(tag).getType();
        log.info("此次保存供应商使用的[SupplierEnum]是:{}", supplierEnum);
        return supplierDecorationHandle.get(tag).saveSupplier(t);
    }

}
