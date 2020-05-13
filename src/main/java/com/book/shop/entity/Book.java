package com.book.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publish", nullable = false)
    private String publish;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "cover", nullable = false)
    private String cover;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "status", nullable = true)
    private Integer status;

    public Book(String bookName, String author, String publish, Double price,
                String cover, Integer stock, String description, Integer status) {
        this.bookName = bookName;
        this.author = author;
        this.publish = publish;
        this.price = price;
        this.cover = cover;
        this.stock = stock;
        this.description = description;
        this.status = status;
    }
}
