package com.book.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book_order")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_at", nullable = true)
    private Date createdTime;

    public BookOrder(Integer bookId, Integer userId, String address, Integer count,
                     Integer status) {
        this.bookId = bookId;
        this.userId = userId;
        this.address = address;
        this.count = count;
        this.status = status;
    }
}
