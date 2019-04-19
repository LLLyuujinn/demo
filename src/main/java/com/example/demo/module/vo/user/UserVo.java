package com.example.demo.module.vo.user;

import com.example.demo.global.LogicGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/6 10:28
 */
public class UserVo {

    @NotNull(message = "username is null", groups = {LogicGroup.addGroup.class, LogicGroup.updateGroup.class})
    private String username;

    @NotNull(message = "password is null", groups = {LogicGroup.addGroup.class})
    private String password;

    @NotBlank(message = "age is null", groups = {LogicGroup.addGroup.class})
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
