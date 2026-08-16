package org.xdclassredis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xdclassredis.model.ProductDO;
@Mapper
public interface ProductMapper extends BaseMapper<ProductDO> {
}
