package org.xdclassredis.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 首页视频展示标题
 * @Author renxiaoqing
 * @Date 2026/8/14 11:47
 * @Version 1.0
 **/
@Getter
@Setter
public class VideoCardDO implements Serializable {
    private Long id;
    private String title;
    private Integer weight;
    private List<VideoDO> videoList;

    public VideoCardDO() {
    }

    public VideoCardDO(Long id, String title, Integer weight, List<VideoDO> videoList) {
        this.id = id;
        this.title = title;
        this.weight = weight;
        this.videoList = videoList;
    }
}
