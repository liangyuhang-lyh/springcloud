package com.lyh.order_service.fallback;

import com.lyh.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallBack implements ProductClient {
    @Override
    public String findById(int id) {
        System.out.println("feign调用product-service findById异常");
        return null;
    }
}
