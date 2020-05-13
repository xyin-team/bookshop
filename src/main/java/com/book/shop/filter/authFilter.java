package com.book.shop.filter;

import com.alibaba.fastjson.JSON;
import com.book.shop.constant.Constant;
import com.book.shop.constant.ErrorResponse;
import com.book.shop.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@WebFilter
@Slf4j
public class authFilter implements Filter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 请求路径是否在白名单路径中
        String path = request.getRequestURI();
        List<String> paths = Constant.WHITE_PATHS.stream().filter(p -> p.equals(path))
                .collect(Collectors.toList());
        log.info(JSON.toJSONString(paths));
        if (!CollectionUtils.isEmpty(paths)) {
            chain.doFilter(request, response);
        } else {
            // 进行 token 的权限校验
            String token = request.getHeader("token");
            if (redisTemplate.opsForValue().get(token) != null) {
                chain.doFilter(request, response);
            } else {
                CommonResponse r = new CommonResponse(ErrorResponse.TOKEN_EXPIRED);
                res.setCharacterEncoding("UTF-8");
                res.getWriter().write(JSON.toJSONString(r));
                return;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
