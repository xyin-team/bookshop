package com.book.shop.service;

import com.alibaba.fastjson.JSON;
import com.book.shop.constant.Constant;
import com.book.shop.constant.ErrorResponse;
import com.book.shop.constant.STATUS;
import com.book.shop.dao.UserDao;
import com.book.shop.entity.User;
import com.book.shop.vo.CommonResponse;
import com.book.shop.vo.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public CommonResponse register(UserRequest userRequest) throws NoSuchAlgorithmException {
        if (!userRequest.validate()) {
            return new CommonResponse(ErrorResponse.IllParam);
        }
        // 将 password md5 处理一下
        String cryptoPwd = getMd5(userRequest.getPassword() + Constant.PWD_SALT);
        User user = new User(userRequest.getUsername(), cryptoPwd,
                STATUS.AVAILABLE);
        try {
            user = userDao.save(user);
        } catch (Exception e) {
            return new CommonResponse(ErrorResponse.UserNameRepeat);
        }

        log.info(JSON.toJSONString(user));
        return new CommonResponse(0, "注册成功",
                JSON.toJSONString(user));
    }

    public CommonResponse login(UserRequest userRequest) throws NoSuchAlgorithmException {
        if (!userRequest.validate()) {
            return new CommonResponse(ErrorResponse.IllParam);
        }
        // 将 password md5 处理一下
        String cryptoPwd = getMd5(userRequest.getPassword() + Constant.PWD_SALT);
        User user = userDao.findByUsername(userRequest.getUsername());
        if (!user.getPassword().equals(cryptoPwd)) {
            return new CommonResponse(ErrorResponse.LoginError);
        }
        // 生成 token
        Map<String, String> data = new HashMap<>();
        String token = getMd5(new Date().getTime() + Constant.TOKEN_SALT +
                userRequest.getUsername());
        data.put("token", token);

        // 设置到 redis 当中，并设置过期时间为 24 小时
        redisTemplate.opsForValue().set(token, JSON.toJSONString(user));
        redisTemplate.expire(token, 60 * 60 * 24, TimeUnit.SECONDS);

        return new CommonResponse(0, "登陆成功", data);
    }

    private String getMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);
    }
}
