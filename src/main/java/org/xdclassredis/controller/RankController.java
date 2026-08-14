package org.xdclassredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdclassredis.model.VideoDO;
import org.xdclassredis.util.JsonData;

import java.util.List;

/**
 * @Description List实战排行榜
 * @Author renxiaoqing
 * @Date 2026/8/14 17:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/rank")
public class RankController {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("daily_rank")
    public JsonData videoRank(){
        String DAILY_RANK_KEY = "video:rank:daily";
        List<VideoDO> list = redisTemplate.opsForList().range(DAILY_RANK_KEY,0,-1);
        return JsonData.buildSuccess(list);
    }
}
