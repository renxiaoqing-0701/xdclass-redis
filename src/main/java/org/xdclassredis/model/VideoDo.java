package org.xdclassredis.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description 首页视频展示详情
 * @Author renxiaoqing
 * @Date 2026/8/14 11:45
 * @Version 1.0
 **/
@Getter
@Setter
public class VideoDo implements Serializable {
    private Long id;
    private String title;
    private String img;
    private BigDecimal price;

    public VideoDo() {
    }

    public VideoDo(Long id, String title, String img, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.price = price;
    }
}
