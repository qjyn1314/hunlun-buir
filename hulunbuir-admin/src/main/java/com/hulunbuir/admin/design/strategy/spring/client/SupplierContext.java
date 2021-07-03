package com.hulunbuir.admin.design.strategy.spring.client;

import com.hulunbuir.admin.design.strategy.spring.model.SupplierEnum;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierDecorationService;
import com.hulunbuir.admin.design.strategy.spring.service.SupplierService;
import com.hulunbuir.common.config.ApplicationUtil;
import com.hulunbuir.parent.exception.BuirException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.IMessageHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;
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

    private Map<Integer, SupplierService<T>> handlerMap = new ConcurrentHashMap<>();

    @Autowired
    public void initHandlerMap(List<SupplierService<T>> handlers) {
        log.info("进入initHandlerMap：{}",handlers);
        if (handlers == null || handlers.isEmpty()) {
            BuirException.build("不存在任何消息处理器,请检查配置是否正确.");
        }
        for (SupplierService<T> handler : handlers) {
            if (handler.getType() == null) {
                log.info("未配置可处理，消息处理器:{}无法注册.", handler.getType().getDesc());
                continue;
            }
            SupplierService<T> tmpHandler = handlerMap.get(handler.getType().getCode());
            if (tmpHandler != null) {
                log.info("{}类型消息处理器{}已配置，无法再注册{}.",
                        tmpHandler.getType().getCode(),
                        tmpHandler.getType().getDesc(),
                        handler.getType());
                continue;
            }
            handlerMap.put(handler.getType().getCode(), handler);
        }
    }

}
