package com.book.shop.controller;

import com.alibaba.fastjson.JSON;
import com.book.shop.service.OrderService;
import com.book.shop.vo.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    private String createOrder(@RequestHeader("token") String token,
                               @RequestBody OrderRequest orderRequest) {
        return JSON.toJSONString(orderService.createOrder(token, orderRequest));
    }

    @PostMapping("/remove")
    private String removeOrder(@RequestParam("id") Integer id) {
        return JSON.toJSONString(orderService.removeOrder(id));
    }

    @GetMapping("/list")
    private String getOrderList(@RequestHeader("token") String token) {
        return JSON.toJSONString(orderService.getOrderList(token));
    }
}
