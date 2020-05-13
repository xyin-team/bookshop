package com.book.shop.service;

import com.alibaba.fastjson.JSON;
import com.book.shop.constant.ErrorResponse;
import com.book.shop.constant.STATUS;
import com.book.shop.dao.OrderDao;
import com.book.shop.entity.BookOrder;
import com.book.shop.entity.User;
import com.book.shop.vo.CommonResponse;
import com.book.shop.vo.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderDao orderDao;

    public CommonResponse createOrder(String token, OrderRequest orderRequest) {
        if (!orderRequest.validate()) {
            return new CommonResponse(ErrorResponse.IllParam);
        }
        // 先从 redis 中拿到对应的 userId
        User user = getUserFromToken(token);
        assert user != null;
        Integer userId = user.getId();
        // 创建 order 记录到 sql
        BookOrder bookOrder = new BookOrder(
                orderRequest.getBookId(),
                userId,
                orderRequest.getAddress(),
                orderRequest.getCount(),
                STATUS.AVAILABLE
        );
        bookOrder = orderDao.save(bookOrder);
        return new CommonResponse(0, "创建订单成功", bookOrder);
    }

    public CommonResponse removeOrder(Integer orderId) {
        Optional<BookOrder> o = orderDao.findById(orderId);
        if (!o.isPresent()) {
            return new CommonResponse(ErrorResponse.NOT_EXIST_ORDER);
        }
        BookOrder bookOrder = o.get();
        bookOrder.setStatus(STATUS.UNAVAILABLE);
        bookOrder = orderDao.save(bookOrder);
        return new CommonResponse(0, "删除成功", bookOrder);
    }

    public CommonResponse getOrderList(String token) {
        // 先从 redis 中拿到对应的 userId
        User user = getUserFromToken(token);
        assert user != null;
        Integer userId = user.getId();
        List<BookOrder> bookOrderList = orderDao.findAllByUserId(userId);
        bookOrderList = bookOrderList.stream().filter(o -> o.getStatus()
                .equals(STATUS.AVAILABLE)).collect(Collectors.toList());
        return new CommonResponse(0, "ok", bookOrderList);
    }

    private User getUserFromToken (String token) {
        String s = redisTemplate.opsForValue().get(token);
        User user = JSON.parseObject(s, User.class);
        return user;
    }
}
