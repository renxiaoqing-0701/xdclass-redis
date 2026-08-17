package org.xdclassredis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.xdclassredis.model.UserDO;
import org.xdclassredis.model.VideoDO;
import org.xdclassredis.model.vo.UserPointVO;

import java.math.BigDecimal;
import java.util.Set;
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
        UserDO userDo = new UserDO(1L,"桃子","女");
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

    /**
     * List实战添加数据
     */
    @Test
    public void saveRank(){
        String DAILY_RANK_KEY = "video:rank:daily";
        VideoDO video1 = new VideoDO(3L,"PaaS工业级微服务大课","xdclass.net",new BigDecimal(1099));
        VideoDO video2 = new VideoDO(5L,"AlibabaCloud全家桶实战","xdclass.net",new BigDecimal(59));
        VideoDO video3 = new VideoDO(53L,"SpringBoot2.X+Vue3综合实战","xdclass.net",new BigDecimal(49));
        VideoDO video4 = new VideoDO(15L,"玩转23种设计模式+最近实战","xdclass.net",new BigDecimal(99));
        VideoDO video5 = new VideoDO(45L,"Nginx网关+LVS+KeepAlive","xdclass.net",new BigDecimal(89));
        redisTemplate.opsForList().leftPushAll(DAILY_RANK_KEY,video4,video5,video3,video2,video1);
    }

    /**
     * List实战替换榜单第二名
     */
    @Test
    void replaceRank(){
        String DAILY_RANK_KEY = "video:rank:daily";
        VideoDO video = new VideoDO(42L,"小滴课堂面试专题第一季高级工程师","xdclass.net",new BigDecimal(89));
        //在集合的指定位置插入元素,如果指定位置已有元素，则覆盖，没有则新增
        redisTemplate.opsForList().set(DAILY_RANK_KEY,1,video);
    }

    /**
     * Sets实战用户新增画像
     */
    @Test
    public void userProfile(){
        //设置key
       BoundSetOperations boundHashOps = redisTemplate.boundSetOps("user:tags:peach");
       //设置属性
       boundHashOps.add("beautiful","car","house","dog","shenzhen","car");
       //获取成员
        Set<String> getMembers = boundHashOps.members();
        System.out.println("桃子用户的属性:"+getMembers);
        //删除成员
        boundHashOps.remove("shenzhen");
        System.out.println("桃子不是深圳的"+boundHashOps.members());
        //重新设置成员
        boundHashOps.add("HongKong");
        System.out.println("桃子是香港的"+boundHashOps.members());
    }

    /**
     * Set实战差集、交集、并集
     */
    @Test
    public void testSet(){
        String peachKey = "user:tags:peach0701";
        String kireKey = "user:tags:kire0710";
        BoundSetOperations peachBound = redisTemplate.boundSetOps(peachKey);
        BoundSetOperations kireBound = redisTemplate.boundSetOps(kireKey);
        peachBound.add("R","X","Q","A","I","N","I");
        kireBound.add("L","S","K","A","I","N","I");
        System.out.println("桃子的粉丝:"+peachBound.members());
        System.out.println("诗凯的粉丝:"+kireBound.members());
        //差集
        Set<String> peachSet = peachBound.diff(kireKey);
        System.out.println("桃子的专属粉丝"+peachSet);
        Set<String> kireSet = kireBound.diff(peachKey);
        System.out.println("诗凯的专属粉丝"+kireSet);
        //并集
        Set<String> unionSet = peachBound.union(kireKey);
        System.out.println("桃子和诗凯的全部粉丝:"+unionSet);
        //交集
        Set<String> intersectSet = peachBound.intersect(kireKey);
        System.out.println("桃子和诗凯的共同粉丝:"+intersectSet);
        System.out.println("S是不是桃子的粉丝："+peachBound.isMember("S"));
        System.out.println("S是不是诗凯的粉丝："+kireBound.isMember("S"));

    }


    /**
     * 用户排行榜单数据新增
     */
    @Test
    void testData() {
        UserPointVO p1 = new UserPointVO("旺旺","18827493988");
        UserPointVO p2 = new UserPointVO("桃子","18529546280");
        UserPointVO p3 = new UserPointVO("诗凯","15817284490");
        UserPointVO p4 = new UserPointVO("俩娃","13886247418");
        UserPointVO p5 = new UserPointVO("思璇","18771511178");
        UserPointVO p6 = new UserPointVO("Alex","15928227894");
        UserPointVO p7 = new UserPointVO("倩倩","19823564320");
        UserPointVO p8 = new UserPointVO("小可","15275323327");
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps("point:rank:real");
        operations.add(p1,92);
        operations.add(p2,108);
        operations.add(p3,299);
        operations.add(p4,30);
        operations.add(p5,22);
        operations.add(p6,194);
        operations.add(p7,92);
        operations.add(p8,8);

    }
}
