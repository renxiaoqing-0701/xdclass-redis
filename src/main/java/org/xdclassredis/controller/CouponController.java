package org.xdclassredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xdclassredis.util.JsonData;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description Luna脚本+redis实现分布式锁
 * @Author renxiaoqing
 * @Date 2026/8/14 16:05
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("add")
    public JsonData saveCoupon(@RequestParam(value = "couponId",required = true)Integer couponId){
        String uuid = UUID.randomUUID().toString();
        String lockKey = "lock:coupon:" + couponId;

        // 最多重试 3 次，每次间隔 3 秒
        boolean locked = tryLock(lockKey, uuid, 3, 3);

        if (!locked) {
            return JsonData.buildError("系统繁忙，请稍后重试");
        }

        // 成功获取锁，执行业务
        try {
            // TODO 执行业务操作
            TimeUnit.SECONDS.sleep(10L);
            return JsonData.buildSuccess();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return JsonData.buildError("业务执行失败");
        } finally {
            unlock(lockKey, uuid);
        }
    }

    /**
     * 尝试获取锁，支持重试
     */
    private boolean tryLock(String lockKey, String uuid, int maxRetries, long retryIntervalSeconds) {
        int retryCount = 0;

        while (retryCount < maxRetries) {
            Boolean locked = redisTemplate.opsForValue()
                    .setIfAbsent(lockKey, uuid, Duration.ofSeconds(30));

            if (Boolean.TRUE.equals(locked)) {
                System.out.println(String.format("线程【%s】获取锁成功", uuid));
                return true;
            }

            retryCount++;
            System.out.println(String.format("线程【%s】获取锁失败，第 %d 次重试", uuid, retryCount));

            if (retryCount < maxRetries) {
                try {
                    TimeUnit.SECONDS.sleep(retryIntervalSeconds);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
        }

        System.out.println(String.format("线程【%s】重试 %d 次后仍未获取到锁", uuid, maxRetries));
        return false;
    }

    /**
     * 释放锁（Lua 脚本保证原子性）
     */
    private void unlock(String lockKey, String uuid) {
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        Object result = redisTemplate.execute(
                new DefaultRedisScript<>(script, Long.class),
                Arrays.asList(lockKey),
                uuid
        );
        System.out.println(String.format("线程【%s】解锁状态【%s】", uuid, result));
    }

    //以下是小滴课堂教的，但有缺陷，改造成以上了
    /*private void lock(String lockKey,String uuid){
        //设置线程uuid用于删除时防止误删

        Boolean nativeLock =redisTemplate.opsForValue().setIfAbsent(lockKey,uuid, Duration.ofSeconds(30));
        System.out.println(String.format("线程【%s】,锁名【%s】,锁的状态【%s】",uuid,lockKey,nativeLock==Boolean.TRUE?"成功":"失败"));
        //lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        if (nativeLock){
            //获取锁成功
            try {
                //TODO 执行业务操作
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Object result =  redisTemplate.execute(new DefaultRedisScript<>(script,Long.class), Arrays.asList(lockKey),uuid);
                System.out.println(String.format("线程【%s】,解锁状态【%s】",uuid,result));
            }
        }else {
            //获取锁失败，睡眠5s后，自旋尝试获取锁
            try {
                System.out.println(String.format("线程【%s】加锁失败,进入自旋",uuid));
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //尝试获取锁
            lock(lockKey,uuid);
        }
    }*/
}
