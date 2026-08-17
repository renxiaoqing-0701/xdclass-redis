package org.xdclassredis.dao;

import org.springframework.stereotype.Repository;
import org.xdclassredis.model.VideoDO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author renxiaoqing
 * @Date 2026/8/14 17:58
 * @Version 1.0
 **/
@Repository
public class VideoDao {
    private static Map<Long, VideoDO> map = new HashMap<>();
    static {
        map.put(1L,new VideoDO(1L,"工业级PaaS云平台+SpringCloudAlibaba 综合项目实战(完结)","https://xdclass.net",new BigDecimal(1099)));
        map.put(2L,new VideoDO(2L,"玩转新版高性能RabbitMQ容器化分布式集群实战","https://xdclass.net",new BigDecimal(79)));
        map.put(3L,new VideoDO(3L,"新版后端提效神器MybatisPlus+SwaggerUI3.X+Lombok","https://xdclass.net",new BigDecimal(49)));
        map.put(4L,new VideoDO(4L,"玩转Nginx分布式架构实战教程 零基础到高级","https://xdclass.net",new BigDecimal(49)));
        map.put(5L,new VideoDO(5L,"ssm新版SpringBoot2.3/spring5/mybatis3","https://xdclass.net",new BigDecimal(49)));
        map.put(6L,new VideoDO(6L,"新一代微服务全家桶AlibabaCloud+SpringCloud实战","https://xdclass.net",new BigDecimal(59)));
    }

    /**
     * 模拟从数据库找
     * @param videoId
     * @return
     */
    public VideoDO findDetailById(Long videoId) {
        return map.get(videoId);
    }
}
