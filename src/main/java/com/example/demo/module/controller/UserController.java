package com.example.demo.module.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.global.BasisResult;
import com.example.demo.global.LogicGroup;
import com.example.demo.module.vo.user.UserVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/6 10:59
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 接口的地址为 http://localhost:8080/user/test/123456
     * @param id
     * @return
     */
    @GetMapping("test/{id}")
    public BasisResult getTestById(@PathVariable Long id) {
        return BasisResult.Success("getTestById");
    }

    @GetMapping("test")
    public BasisResult test(@RequestParam String username, @RequestParam String password) {
        return BasisResult.Success("test");
    }

    @PutMapping("test")
    public BasisResult updateTest(@RequestBody @Validated({LogicGroup.updateGroup.class}) UserVo testVo) {
        return BasisResult.Success("updateTest");
    }

    @PostMapping("test")
    public BasisResult addTest(@RequestBody @Validated({LogicGroup.addGroup.class}) UserVo testVo) {
        return BasisResult.Success("addTest" + JSON.toJSON(testVo));
    }

    @DeleteMapping("test/{id}")
    public BasisResult deleteTest(@PathVariable Long id) {
        return BasisResult.Success("deleteTest");
    }
}
