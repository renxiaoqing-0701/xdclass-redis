package org.xdclassredis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xdclassredis.dao.VideoCardDao;
import org.xdclassredis.model.VideoCardDo;
import org.xdclassredis.service.VideoCardService;

import java.util.List;

/**
 * @Description 热门视频实现类
 * @Author renxiaoqing
 * @Date 2026/8/14 13:51
 * @Version 1.0
 **/
@Service
public class VideoCardServiceImpl implements VideoCardService {
    @Autowired
    private VideoCardDao videoCardDao;

    public List<VideoCardDo> listVideoCard() {
        return videoCardDao.listVideoCard();
    }
}
