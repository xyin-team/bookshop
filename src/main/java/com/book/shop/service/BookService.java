package com.book.shop.service;

import com.book.shop.constant.ErrorResponse;
import com.book.shop.constant.STATUS;
import com.book.shop.dao.BookDao;
import com.book.shop.entity.Book;
import com.book.shop.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public CommonResponse getsBookList(String keyword) {
        List<Book> books = new ArrayList<>();
        if (keyword == null) {
            books = bookDao.findAll();
        } else {
            log.info("keyword is {}", keyword);
            List<Book> book = bookDao.findAllByBookNameLike("%" + keyword + "%");
            log.info("bookDao is {}", book);
            if (book != null) {
                book = book.stream().filter(b -> b.getStatus().equals(STATUS.AVAILABLE))
                        .collect(Collectors.toList());
                books.addAll(book);
            }
        }
        return new CommonResponse(0, "ok", books);
    }

    public CommonResponse getBookDetail(Integer id) {
        Optional<Book> b = bookDao.findById(id);
        if (!b.isPresent()) {
            return new CommonResponse(ErrorResponse.NO_BOOK);
        }
        Book book = b.get();
        return new CommonResponse(0, "ok", book);
    }
}
