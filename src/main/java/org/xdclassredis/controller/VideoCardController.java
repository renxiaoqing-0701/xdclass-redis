package org.xdclassredis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdclassredis.model.VideoCardDO;
import org.xdclassredis.service.VideoCardService;
import org.xdclassredis.util.JsonData;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author renxiaoqing
 * @Date 2026/8/14 13:53
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/api/v1/video/card")
public class VideoCardController {
    @Autowired
    private VideoCardService videoCardService;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String VIDEO_CARD_CACHE_KEY = "video:card:key";

    /**
     *
     * @return
     */
    @GetMapping("list_cache")
    public JsonData listVideoCardCache(){
        Object cacheObj = redisTemplate.opsForValue().get(VIDEO_CARD_CACHE_KEY);
        if (cacheObj != null){
            List<VideoCardDO> videoCards = (List<VideoCardDO>) cacheObj;
           return JsonData.buildSuccess(videoCards);
        }else {
            log.info("缓存中无数据");
            List<VideoCardDO> videoCards = videoCardService.listVideoCard();
            redisTemplate.opsForValue().set(VIDEO_CARD_CACHE_KEY,videoCards,10, TimeUnit.MINUTES);
            return JsonData.buildSuccess(videoCards);
        }

    }

    @GetMapping("list_nocache")
    public JsonData listVideoCardNoCache(){
        List<VideoCardDO> videoCards = videoCardService.listVideoCard();
        return JsonData.buildSuccess(videoCards);
    }

}
