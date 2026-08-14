package org.xdclassredis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.xdclassredis.model.UserDo;
import org.xdclassredis.model.VideoDo;

import java.math.BigDecimal;
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
    @Test
    public void saveRank(){
        String DAILY_RANK_KEY = "video:rank:daily";
        VideoDo video1 = new VideoDo(3L,"PaaS工业级微服务大课","xdclass.net",new BigDecimal(1099));
        VideoDo video2 = new VideoDo(5L,"AlibabaCloud全家桶实战","xdclass.net",new BigDecimal(59));
        VideoDo video3 = new VideoDo(53L,"SpringBoot2.X+Vue3综合实战","xdclass.net",new BigDecimal(49));
        VideoDo video4 = new VideoDo(15L,"玩转23种设计模式+最近实战","xdclass.net",new BigDecimal(99));
        VideoDo video5 = new VideoDo(45L,"Nginx网关+LVS+KeepAlive","xdclass.net",new BigDecimal(89));
        redisTemplate.opsForList().leftPushAll(DAILY_RANK_KEY,video4,video5,video3,video2,video1);
    }

    /**
     * 替换榜单第二名
     */
    @Test
    void replaceRank(){
        String DAILY_RANK_KEY = "video:rank:daily";
        VideoDo video = new VideoDo(42L,"小滴课堂面试专题第一季高级工程师","xdclass.net",new BigDecimal(89));
        //在集合的指定位置插入元素,如果指定位置已有元素，则覆盖，没有则新增
        redisTemplate.opsForList().set(DAILY_RANK_KEY,1,video);
    }
}
