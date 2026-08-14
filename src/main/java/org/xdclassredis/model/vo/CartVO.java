package org.xdclassredis.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Description TODO
 * @Author renxiaoqing
 * @Date 2026/8/14 17:58
 * @Version 1.0
 **/
@Getter
@Setter
public class CartVO implements Serializable {
    /**
     * 购物项
     */
    private List<CartItemVO> cartItems;
    /**
     * 购物车总价格
     */
    private Integer totalAmount;
}
