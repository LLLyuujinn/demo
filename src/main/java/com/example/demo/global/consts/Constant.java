package com.example.demo.global.consts;

/**
 * 常量类
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:24
 */
public class Constant {

    /**
     * 域名
     */
    public static final String DOMAIN = "www.bigxigua.com";

    /**
     * 用户登录在redis中存放key的前缀，以及返回到前端的前缀
     */
    public static final String USER_TOKEN = "user_token_";

    /** 用户登录的过期时间 */
    public static final Integer USER_TOKEN_TIMEOUT = 30 * 60;
}
