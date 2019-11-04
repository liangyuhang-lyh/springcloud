package com.lyh.product_service.controller;

import com.lyh.product_service.domain.Product;
import com.lyh.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("/list")
    public Object list(){
        return productService.listProduct();
    }

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Product findById(@RequestParam("id") int id){
        return productService.findById(id);
    }
}
