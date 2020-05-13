package com.book.shop.constant;

public interface ErrorResponse {
    public BaseResponse IllParam = new BaseResponse(-1, "参数不合法");
    public BaseResponse UserNameRepeat = new BaseResponse(-2, "用户名重复");
    public BaseResponse LoginError = new BaseResponse(-3, "用户名或者密码错误");
    public BaseResponse TOKEN_EXPIRED = new BaseResponse(-4, "token 已过期，请重新登录");
    public BaseResponse NO_BOOK = new BaseResponse(-5, "没有对应 id 的书籍");
    public BaseResponse NOT_EXIST_ORDER = new BaseResponse(-6, "订单不存在");

}
