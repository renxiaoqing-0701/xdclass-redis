package org.xdclassredis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.xdclassredis.model.UserDo;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class XdclassRedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void testStringSet(){
//        redisTemplate.opsForValue().set("name","xdclass");
//        stringRedisTemplate.opsForValue().set("lls:name","rxq");
        UserDo userDo = new UserDo(1L,"桃子","女");
        redisTemplate.opsForValue().set("userinfo:user:1",userDo);
        System.out.println("设置key成功");
    }
    @Test
    void testStringGet(){
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    public void testDLock(){
       boolean flag =  stringRedisTemplate.opsForValue().setIfAbsent("coupon:1001","100",30, TimeUnit.SECONDS);
       System.out.println("获取锁:"+flag);
    }
}
