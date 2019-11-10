package com.lyh.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.lyh.order_service.domain.ProductOrder;
import com.lyh.order_service.service.ProductClient;
import com.lyh.order_service.service.ProductOrderService;
import com.lyh.order_service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
@Service
public class ProductOrderServiceImpl implements ProductOrderService{
    //@Autowired
    //private RestTemplate restTemplate;
   // @Autowired
    //private LoadBalancerClient loadBalancer;
    @Autowired
    private ProductClient productClient;
    @Override
    public ProductOrder save(int userId, int productId) {
        if(userId == 1){
            return null;
        }
        //調用方式一
        //Map<String,Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/findById?id=" + productId, MAp.class);
        //调用方式二
        //ServiceInstance instance = loadBalancer.choose("product-service");
        //String url = String.format("http://%s:%s/api/v1/product/findById?id=" + productId, instance.getHost(), instance.getPort());
        //RestTemplate restTemplate=new RestTemplate();
        //Map<String,Object> productMap=restTemplate.getForObject(url,Map.class);
        //System.out.println(object);
        /*ProductOrder productOrder=new ProductOrder();
        productOrder.setCreatTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(productMap.get("name").toString());
        productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));*/
        //feign調用方式
        String response = productClient.findById(productId);

        JsonNode jsonNode = JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreatTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
