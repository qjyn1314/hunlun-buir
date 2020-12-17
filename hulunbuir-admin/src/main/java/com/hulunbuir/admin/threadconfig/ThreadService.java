package com.hulunbuir.admin.threadconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * explain:
 * </p>
 *
 * @author wangjunming
 * @since 2020/11/22 21:10
 */
@Slf4j
@Configuration
public class ThreadService {

    /**
     * 实际上，@Async还有一个参数，通过Bean名称来指定调用的线程池-比如上例中设置的线程池参数不满足业务需求，
     * 可以另外定义合适的线程池，调用时指明使用这个线程池-缺省时springboot会优先使用名称为'taskExecutor'的线程池，
     * 如果没有找到，才会使用其他类型为TaskExecutor或其子类的线程池。
     *
     * @author wangjunming
     * @since 2020/11/22 21:37
     */
    @Async(value = "hulunExecutor")
    public void sayHello(String name) {
        log.info(name + "Say : Hello !!! ~~");
    }

    @Async(value = "hulunExecutor")
    public CompletableFuture<String> sayHelloAndReturn(String name) {
        String res = name + "：Say : Hello World!!! ~~";
        log.info(res);
        final AsyncResult<String> asyncResult = new AsyncResult<>(res);
        String asyncResultStr = "";
        try {
            asyncResultStr = asyncResult.get();
        } catch (Exception e) {
            log.error("获取线程池中的结果：{}",asyncResultStr);
        }
        return CompletableFuture.completedFuture(asyncResultStr);
    }

}