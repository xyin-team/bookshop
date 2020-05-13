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
@Table(name = "user_role_mapping")
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role_id", nullable =  false)
    private Integer roleId;

    @Column(name = "user_id",  nullable = false)
    private Integer bookId;

    public UserRoleMapping(Integer roleId, Integer bookId) {
        this.roleId = roleId;
        this.bookId = bookId;
    }
}
