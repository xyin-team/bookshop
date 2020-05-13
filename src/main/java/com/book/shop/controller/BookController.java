package com.book.shop.controller;

import com.alibaba.fastjson.JSON;
import com.book.shop.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    private String getBookList(@RequestParam(value = "keyword", required = false)
                                           String keyword) {
        return JSON.toJSONString(bookService.getsBookList(keyword));
    }

    @GetMapping("/detail")
    private String getBookDetail(@RequestParam("id") Integer bookId) {
        return JSON.toJSONString(bookService.getBookDetail(bookId));
    }
}
