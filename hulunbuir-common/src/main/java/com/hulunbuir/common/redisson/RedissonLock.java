package com.hulunbuir.common.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * explain: 使用redission实现分布式锁，参考与：https://blog.csdn.net/zhangcongyi420/article/details/89980469
 * 在分布式场景下为了保证数据最终一致性。
 * 在单进程的系统中，存在多个线程可以同时改变某个变量（可变共享变量）时，
 * 就需要对变量或代码块做同步(lock—synchronized)，使其在修改这种变量时能够线性执行消除并发修改变量。
 * 但分布式系统是多部署、多进程的，开发语言提供的并发处理API在此场景下就无能为力了。
 * </p>
 *
 * @author wangjunming
 * @since 2020/8/5 16:36
 */
@Component
public final class RedissonLock {

    @Autowired(required = false)
    private RedissonClient redissonClient;

    /**
     * lock(), 拿不到lock就不罢休，不然线程就一直block
     *
     * @param lockKey 锁key
     * @return RLock
     */
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * leaseTime为加锁时间，单位为秒
     *
     * @param lockKey   key
     * @param leaseTime 单位为秒
     * @return RLock
     */
    public RLock lock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return lock;
    }

    /**
     * timeout为加锁时间，时间单位由unit确定
     *
     * @param lockKey key
     * @param unit    时间单位
     * @param timeout 时间
     * @return RLock
     */
    public RLock lock(String lockKey, TimeUnit unit, long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    public void unlock(RLock lock) {
        lock.unlock();
    }


}
