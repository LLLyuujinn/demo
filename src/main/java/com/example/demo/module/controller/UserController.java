package com.example.demo.module.controller;

import com.example.demo.global.BasisResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/6 10:59
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("test")
    public BasisResult test(@RequestParam String username, @RequestParam String password) {
        return BasisResult.Success();
    }
}
