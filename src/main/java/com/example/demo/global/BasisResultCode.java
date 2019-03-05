package com.example.demo.global;

/**
 * 系统消息统一返回枚举类
 *
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 11:13
 */
public enum BasisResultCode {
    FAIL(0, "Fail"),    //失败状态码
    SUCCESS(1, "Success"),  //成功状态码
    ;

    private Integer code;
    private String message;

    BasisResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
