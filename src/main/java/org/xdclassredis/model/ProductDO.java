package org.xdclassredis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("product")
public class ProductDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面图
     */
    private String coverImg;
    /**
     * 详情
     */
    private String detail;
    /**
     * 新价格
     */
    private Integer amount;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 创建时间
     */
    private Date createTime;
}
