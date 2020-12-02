package cn.cunchang.lock;

import cn.cunchang.RedissonUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class LockTest {

    static long doSomethingTime = 10L;
    // 锁租约时间
    static long leaseTime = 10L;

    public static void main(String[] args) {
        RedissonClient redisson = RedissonUtil.getRedissonClient();
        RLock lock = redisson.getLock("myLock");
        RBucket<Object> rBucket = redisson.getBucket("myLock");
        try {
            lock.tryLock( leaseTime, TimeUnit.SECONDS);
//            lock.tryLock(0L, leaseTime, TimeUnit.SECONDS);
            long ttl = rBucket.remainTimeToLive();
            System.out.println("lock | lockkey:myLock,ttl:" + ttl);
            for (int i = 1; i <= doSomethingTime; i++) {
                Thread.sleep(995L);
                ttl = rBucket.remainTimeToLive();
                System.out.println("do something " + i + " 秒 | lockkey:myLock,ttl:" + ttl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                lock.unlock();
                long ttl = rBucket.remainTimeToLive();
                System.out.println("unlock lockkey:myLock,ttl:" + ttl);
            }
            redisson.shutdown();
        }
    }

}
