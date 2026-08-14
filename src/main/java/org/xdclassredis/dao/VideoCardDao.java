package org.xdclassredis.dao;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.xdclassredis.model.VideoCardDO;
import org.xdclassredis.model.VideoDO;

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
    public List<VideoCardDO> listVideoCard(){

        try {
            TimeUnit.NANOSECONDS.sleep(200);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<VideoCardDO> videoCards = new ArrayList<>();
        VideoCardDO videoCard = new VideoCardDO();
        videoCard.setId(1L);
        videoCard.setTitle("热门课程");
        List<VideoDO> videoDos = new ArrayList<>();
        VideoDO video1 = new VideoDO(1L,"AI智能面试平台","AI_imgxxxx",new BigDecimal(6299));
        VideoDO video2 = new VideoDO(2L,"AI智能云盘LLM","AI_YP_imgxxxx",new BigDecimal(5699));
        VideoDO video3 = new VideoDO(3L,"中间件项目","ZJ_imgxxxx",new BigDecimal(5299));
        videoDos.add(video1);
        videoDos.add(video2);
        videoDos.add(video3);
        videoCard.setVideoList(videoDos);

        VideoCardDO videoCard2 = new VideoCardDO();
        videoCard2.setId(2L);
        videoCard2.setTitle("新课上线");
        List<VideoDO> videoDos2 = new ArrayList<>();
        VideoDO video4 = new VideoDO(4L,"零基础学AI大模型","AI_LLM_imgxxxx",new BigDecimal(69));
        VideoDO video5 = new VideoDO(5L,"零基础学Python","Python_imgxxxx",new BigDecimal(49));
        VideoDO video6 = new VideoDO(6L,"Activiti7.X","Activiti7_imgxxxx",new BigDecimal(49));
        videoDos2.add(video4);
        videoDos2.add(video5);
        videoDos2.add(video6);
        videoCard2.setVideoList(videoDos2);

        VideoCardDO videoCard3 = new VideoCardDO();
        videoCard3.setId(3L);
        videoCard3.setTitle("20K面试必备");
        List<VideoDO> videoDos3 = new ArrayList<>();
        VideoDO video7 = new VideoDO(7L,"手撕大厂算法","SF_imgxxxx",new BigDecimal(2599));
        VideoDO video8 = new VideoDO(8L,"全栈多端低代码","DDM_imgxxxx",new BigDecimal(2899));
        VideoDO video9 = new VideoDO(9L,"Kafka","Kafka_imgxxxx",new BigDecimal(98));
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
