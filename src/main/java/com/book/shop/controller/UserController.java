package com.book.shop.controller;

import com.alibaba.fastjson.JSON;
import com.book.shop.service.UserService;
import com.book.shop.vo.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private String register (@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException {
        return JSON.toJSONString(userService.register(userRequest));
    }

    @PostMapping("/login")
    private String login(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException {
        return JSON.toJSONString(userService.login(userRequest));
    }

    @GetMapping("/hello")
    private String hello() {
        return "hello world!";
    }
}
