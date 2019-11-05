package com.lyh.order_service.service;

import com.lyh.order_service.domain.ProductOrder;

public interface ProductOrderService {
    ProductOrder save(int userId,int productId);
}
