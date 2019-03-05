package com.example.demo.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:09
 */

@Component
public class AuthFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("=======初始化过滤器=========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;

        long start = System.currentTimeMillis();

        MyHttpRequest myrequest = new MyHttpRequest((HttpServletRequest) request);

    }

    @Override
    public void destroy() {

    }
}
