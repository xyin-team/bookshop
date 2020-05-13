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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = true)
    private Integer status;

    @Column(name = "created_at", nullable = true)
    private Date createdTime;

    @Column(name = "updated_at", nullable = true)
    private Date updatedTime;

    public User(String username, String password, Integer status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }
}
