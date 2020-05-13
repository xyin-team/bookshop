package com.book.shop.vo;

import com.book.shop.constant.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Integer code;
    private String message;
    private Object data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(BaseResponse baseResponse) {
        this.code = baseResponse.getCode();
        this.message = baseResponse.getMessage();
    }
}
