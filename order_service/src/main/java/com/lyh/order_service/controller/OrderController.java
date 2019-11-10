package com.lyh.order_service.controller;

import com.lyh.order_service.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id") int userId,@RequestParam("product_id") int productId){
        Map<String,Object> data=new HashMap<>();
        data.put("code",0);
        data.put("data",productOrderService.save(userId,productId));
        return data;
    }
    private Object saveOrderFail(int userId, int productId, HttpServletRequest request){
        String saveOrderKye="save-order";
        String sendValue=redisTemplate.opsForValue().get(saveOrderKye);
        final String ip=request.getRemoteAddr();
        new Thread( ()->{
            if (StringUtils.isBlank(sendValue)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ip);
                //发送一个http请求，调用短信服务 TODO
                redisTemplate.opsForValue().set(saveOrderKye, "save-order-fail", 20, TimeUnit.SECONDS);

            }else{
                System.out.println("已经发送过短信，20秒内不重复发送");
            }
        }).start();
        Map<String,Object> msg=new HashMap<>();
        msg.put("code",-1);
        msg.put("msg","当前访问人数过多，请稍后重试");
        return msg;
    }
}
