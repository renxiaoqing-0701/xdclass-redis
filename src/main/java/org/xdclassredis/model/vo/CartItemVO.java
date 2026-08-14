package org.xdclassredis.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author renxiaoqing
 * @Date 2026/8/14 17:56
 * @Version 1.0
 **/
@Setter
@Getter
public class CartItemVO implements Serializable {
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 购买数量
     */
    private Integer buyNum;
    /**
     * 商品标题
     */
    private String productTitle;
    /**
     * 图片
     */
    private String productImg;
    /**
     * 商品单价
     */
    private BigDecimal price ;
    /**
     * 总价格，单价+数量
     */
    private Integer totalPrice;
}
