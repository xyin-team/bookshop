package com.book.shop.constant;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String PWD_SALT = "*njknsdf&";
    public static final String TOKEN_SALT = "?&*sdo^-T";
    public static final List<String> WHITE_PATHS = Arrays.asList("/register", "/login");
    public static class RedisKey {
        public static final String USER_INFO = "user_info";
    }
}
