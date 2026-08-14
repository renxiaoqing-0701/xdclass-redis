package org.xdclassredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xdclassredis.dao.VideoDao;
import org.xdclassredis.model.VideoDO;
import org.xdclassredis.model.vo.CartItemVO;
import org.xdclassredis.util.JsonData;
import org.xdclassredis.util.JsonUtil;

/**
 * @Description 购物车接口
 * @Author renxiaoqing
 * @Date 2026/8/14 18:03
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private VideoDao videoDao;

    @RequestMapping("add")
    public JsonData addCart(@RequestParam(value = "videoId",required = true ) Long videoId,
                            @RequestParam(value = "buyNum",required = true ) Integer buyNum){
        //获取购物车
        BoundHashOperations<String, Object, Object> myCart = getMyCartOps();
        Object cacheObj = myCart.get(videoId+"");
        String result = "";
        if (cacheObj != null) {
            result = (String) cacheObj;
        }
        if (cacheObj == null) {
            //不存在则新建一个购物项
            CartItemVO cartItem = new CartItemVO();
            //从数据库查询详情，我们这边直接随机写个
            VideoDO videoDO = videoDao.findDetailById(videoId);
            videoDO.setId(videoId);
            cartItem.setPrice(videoDO.getPrice());
            cartItem.setBuyNum(buyNum);
            cartItem.setProductId(videoId);
            cartItem.setProductImg(videoDO.getImg());
            cartItem.setProductTitle(videoDO.getTitle());
            myCart.put(videoId+"", JsonUtil.objectToJson(cartItem));
        } else {
            //存在则新增数量
            CartItemVO cartItem = JsonUtil.jsonToPojo(result, CartItemVO.class);
            cartItem.setBuyNum(cartItem.getBuyNum() + buyNum);
            myCart.put(videoId+"", JsonUtil.objectToJson(cartItem));
        }
        return JsonData.buildSuccess();
    }

    private BoundHashOperations<String,Object,Object> getMyCartOps(){
        String cartKey = getCartKey();
        return redisTemplate.boundHashOps(cartKey);
    }
    private String getCartKey(){
        //真实环境拦截器获取
        Integer userId = 1882;
        String cartKey = String.format("video:cart:%s",userId);
        return cartKey;
    }
}
