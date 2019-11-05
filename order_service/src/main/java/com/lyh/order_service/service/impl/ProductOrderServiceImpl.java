package com.lyh.order_service.service.impl;

import com.lyh.order_service.domain.ProductOrder;
import com.lyh.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;
@Service
public class ProductOrderServiceImpl implements ProductOrderService{
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ProductOrder save(int userId, int productId) {
        Object object = restTemplate.getForObject("http://product-service/api/v1/product/findById?id=" + productId, Object.class);
        System.out.println(object);
        ProductOrder productOrder=new ProductOrder();
        productOrder.setCreatTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        return productOrder;
    }
}
