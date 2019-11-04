package com.lyh.product_service.service;

import com.lyh.product_service.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<Product> listProduct();
    Product findById(int id);
}
