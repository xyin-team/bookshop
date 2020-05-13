package com.book.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;

    public boolean validate() {
        return this.username.length() < 10 && password.length() >= 4;
    }
}
