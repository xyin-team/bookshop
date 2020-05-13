package com.book.shop.dao;

import com.book.shop.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<BookOrder, Integer> {
    List<BookOrder> findAllByUserId(Integer userId);
}

