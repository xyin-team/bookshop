package com.book.shop.dao;

import com.book.shop.entity.Book;
import com.book.shop.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Integer> {
    List<Book> findAllByBookNameLike(String name);
}
