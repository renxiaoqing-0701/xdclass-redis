package org.xdclassredis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.xdclassredis.mapper.ProductMapper;
import org.xdclassredis.model.ProductDO;
import org.xdclassredis.service.ProductService;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public int save(ProductDO productDO) {
        return productMapper.insert(productDO);
    }

    @Override
    public int delById(int id) {
        return productMapper.deleteById(id);
    }

    @Override
    public int updateById(ProductDO productDO) {
        return productMapper.updateById(productDO);
    }

    @Override
    @Cacheable(value = {"product"},key = "#root.args [0]",cacheManager = "cacheManager5Minute")
    //@Cacheable(value = {"product"}, keyGenerator = "springCacheCustomkeyGenerator", cacheManager = "cacheManager5Minute",sync = true)
    /*@Caching(
            cacheable = {
                    @Cacheable(value = {"product"},key ="#root.aras [0]" )
            },
            put = {
                @CachePut(value = {""},key = "",cacheManager = "cacheManager5Minute")
            }
    )*/
    public ProductDO findById(int id) {
        return productMapper.selectById(id);
    }

    @Override
    @Cacheable(value = {"product_page"},key="#root.methodName + #page+'_'+#size")
    public Map<String, Object> page(int page, int size) {
        Page<ProductDO> pageInfo = new Page<>(page, size);
        IPage<ProductDO> productDOIPage = productMapper.selectPage(pageInfo, null);
        Map<String, Object> pageMap = new HashMap<>(3);
        pageMap.put("total_record", productDOIPage.getTotal());
        pageMap.put("total_page", productDOIPage.getPages());
        pageMap.put("current_data", productDOIPage.getRecords());
        return pageMap;
    }
}
