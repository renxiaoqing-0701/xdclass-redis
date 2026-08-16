package org.xdclassredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xdclassredis.model.ProductDO;
import org.xdclassredis.service.ProductService;
import org.xdclassredis.util.JsonData;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("add")
    public JsonData addProduct(@RequestBody ProductDO productDO){
       int add =  productService.save(productDO);
       return JsonData.buildSuccess(String.format("添加商品成功【%s】条",add));
    }
    @PostMapping("updateById")
    public JsonData updateById(@RequestBody ProductDO productDO){
        int update =  productService.updateById(productDO);
        return JsonData.buildSuccess(String.format("修改商品成功【%s】条",update));
    }
    @DeleteMapping("delById")
    public JsonData delById(Integer productId){
        int del =  productService.delById(productId);
        return JsonData.buildSuccess(String.format("删除商品成功【%s】条",del));
    }
    @GetMapping("findById")
    public JsonData findById(Integer productId){
        ProductDO product =  productService.findById(productId);
        return JsonData.buildSuccess(product);
    }
    @GetMapping("page")
    public JsonData page(Integer page,Integer size){
        Map<String,Object>  pageMaps =  productService.page(page,size);
        return JsonData.buildSuccess(pageMaps);
    }

}
