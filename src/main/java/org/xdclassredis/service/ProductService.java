package org.xdclassredis.service;

import org.xdclassredis.model.ProductDO;

import java.util.Map;

public interface ProductService {
    int save(ProductDO productDO);
    int delById(int id);
    int updateById(ProductDO productDO);
    ProductDO findById(int id);
    Map<String, Object> page(int page, int size);
}
