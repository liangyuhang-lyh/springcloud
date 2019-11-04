package com.lyh.product_service.service.impl;

import com.lyh.product_service.domain.Product;
import com.lyh.product_service.service.ProductService;

import java.util.*;

public class ProductServiceImpl implements ProductService{

    private static final Map<Integer,Product> daoMap=new HashMap<>();
    static{
        Product p1=new Product(1,"手机",8888,200);
        Product p2=new Product(2,"电脑",28888,100);
        Product p3=new Product(3,"衬衫",888,220);
        Product p4=new Product(4,"牛仔裤",999,370);
        Product p5=new Product(5,"空调",68888,180);

        daoMap.put(p1.getId(),p1);
        daoMap.put(p2.getId(),p2);
        daoMap.put(p3.getId(),p3);
        daoMap.put(p4.getId(),p4);
        daoMap.put(p5.getId(),p5);
    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection=daoMap.values();
        List<Product> list=new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(int id) {
        return daoMap.get(id);
    }
}
