package com.book.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer bookId;
    private String address;
    private Integer count;

    public boolean validate() {
        return bookId != null
                && address != null
                && address.length() > 0
                && count != null
                && count > 0;
    }
}
