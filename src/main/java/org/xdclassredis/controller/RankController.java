package org.xdclassredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdclassredis.model.VideoDO;
import org.xdclassredis.model.vo.UserPointVO;
import org.xdclassredis.util.JsonData;

import java.util.List;
import java.util.Set;

/**
 * @Description List&Set实战排行榜
 * @Author renxiaoqing
 * @Date 2026/8/14 17:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/rank")
public class RankController {

    private static final String REAL_RANK = "point:rank:real";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * List非实时榜单
     * @return
     */
    @GetMapping("daily_rank")
    public JsonData videoRank(){
        String DAILY_RANK_KEY = "video:rank:daily";
        List<VideoDO> list = redisTemplate.opsForList().range(DAILY_RANK_KEY,0,-1);
        return JsonData.buildSuccess(list);
    }

    /**
     * 获取排行榜从小到大
     * @return
     */
    @GetMapping("user_rank")
    public JsonData userRank(Integer rank){
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps(REAL_RANK);
        return JsonData.buildSuccess(operations.range(0,rank));
    }

    /**
     * 获取排行榜从大到小
     * @return
     */
    @GetMapping("user_reverse_rank")
    public JsonData userReverseRank(Integer rank){
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps(REAL_RANK);
        return JsonData.buildSuccess(operations.reverseRange(0,rank));
    }

    /**
     * 查询某个用户的排名正序
     * @param name
     * @param phone
     * @return
     */
    @GetMapping("find_my_rank")
    public JsonData findRank(String name,String phone){
        UserPointVO user = new UserPointVO(name,phone);
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps(REAL_RANK);
        Long rank = operations.rank(user);
        rank = rank+1;
        return JsonData.buildSuccess(String.format("查询用户【%s】积分有【%s】正序排名：【%s】",name,operations.score(user),rank));
    }

    /**
     * 查询某个用户的排名 倒序
     * @param name
     * @param phone
     * @return
     */
    @GetMapping("find_my_reverse_rank")
    public JsonData findReverseRank(String name,String phone){
        UserPointVO user = new UserPointVO(name,phone);
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps(REAL_RANK);
        Long reverseRank = operations.reverseRank(user);
        reverseRank = reverseRank+1;
        return JsonData.buildSuccess(String.format("查询用户【%s】倒序排名：【%s】",name,reverseRank));
    }

    /**
     * 给指定用户增加指定积分
     * @param phone
     * @param point
     * @return
     */
    @GetMapping("user_add_point")
    public JsonData userAddPoint(String name,String phone,Integer point){
        UserPointVO user = new UserPointVO(name,phone);
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps(REAL_RANK);
        Double score =  operations.incrementScore(user,point);
        return JsonData.buildSuccess(String.format("给用户【%s】增加【%s】积分,当前积分【%s】】",name,point,score));
    }


    /**
     * 给指定用户增加指定积分
     * @param phone
     * @param name
     * @return
     */
    @GetMapping("get_user_score")
    public JsonData getUserScore(String name,String phone){
        UserPointVO user = new UserPointVO(name,phone);
        BoundZSetOperations<String, UserPointVO> operations = redisTemplate.boundZSetOps(REAL_RANK);
        Double score =  operations.score(user);
        return JsonData.buildSuccess(String.format("给用户【%s】,当前积分【%s】】",name,score));
    }
}
