package org.xdclassredis.dao;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.xdclassredis.model.VideoCardDo;
import org.xdclassredis.model.VideoDo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author renxiaoqing
 * @Date 2026/8/14 11:50
 * @Version 1.0
 **/
@Slf4j
@Repository
public class VideoCardDao {
    public List<VideoCardDo> listVideoCard(){

        try {
            TimeUnit.NANOSECONDS.sleep(200);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<VideoCardDo> videoCards = new ArrayList<>();
        VideoCardDo videoCard = new VideoCardDo();
        videoCard.setId(1L);
        videoCard.setTitle("热门课程");
        List<VideoDo> videoDos = new ArrayList<>();
        VideoDo video1 = new VideoDo(1L,"AI智能面试平台","AI_imgxxxx",new BigDecimal(6299));
        VideoDo video2 = new VideoDo(2L,"AI智能云盘LLM","AI_YP_imgxxxx",new BigDecimal(5699));
        VideoDo video3 = new VideoDo(3L,"中间件项目","ZJ_imgxxxx",new BigDecimal(5299));
        videoDos.add(video1);
        videoDos.add(video2);
        videoDos.add(video3);
        videoCard.setVideoList(videoDos);

        VideoCardDo videoCard2 = new VideoCardDo();
        videoCard2.setId(2L);
        videoCard2.setTitle("新课上线");
        List<VideoDo> videoDos2 = new ArrayList<>();
        VideoDo video4 = new VideoDo(4L,"零基础学AI大模型","AI_LLM_imgxxxx",new BigDecimal(69));
        VideoDo video5 = new VideoDo(5L,"零基础学Python","Python_imgxxxx",new BigDecimal(49));
        VideoDo video6 = new VideoDo(6L,"Activiti7.X","Activiti7_imgxxxx",new BigDecimal(49));
        videoDos2.add(video4);
        videoDos2.add(video5);
        videoDos2.add(video6);
        videoCard2.setVideoList(videoDos2);

        VideoCardDo videoCard3 = new VideoCardDo();
        videoCard3.setId(3L);
        videoCard3.setTitle("20K面试必备");
        List<VideoDo> videoDos3 = new ArrayList<>();
        VideoDo video7 = new VideoDo(7L,"手撕大厂算法","SF_imgxxxx",new BigDecimal(2599));
        VideoDo video8 = new VideoDo(8L,"全栈多端低代码","DDM_imgxxxx",new BigDecimal(2899));
        VideoDo video9 = new VideoDo(9L,"Kafka","Kafka_imgxxxx",new BigDecimal(98));
        videoDos3.add(video7);
        videoDos3.add(video8);
        videoDos3.add(video9);
        videoCard3.setVideoList(videoDos3);
        videoCards.add(videoCard);
        videoCards.add(videoCard2);
        videoCards.add(videoCard3);
        log.info(JSON.toJSONString(videoCards));
        return videoCards;
    }
}
