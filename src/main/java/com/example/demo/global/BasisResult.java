package com.example.demo.global;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 接口统一返回类
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:56
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasisResult {

    private Integer code;   //状态
    private Object data;    //返回对象
    private String message; //配合API加的一个状态
    private boolean success = false;//配合API加的一个状态

    public BasisResult(){

    }

    public BasisResult(Integer code) {
        this.code = code;
    }

    public BasisResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BasisResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BasisResult setResultCode(BasisResultCode basisResultCode) {
        this.code = basisResultCode.getCode();
        this.message = basisResultCode.getMessage();
        if (BasisResultCode.SUCCESS.getCode().equals(basisResultCode.getCode())) {
            this.success = true;
        }
        return this;
    }

    public BasisResult setData(Object data) {
        this.data = data;
        return this;
    }

    public static BasisResult Default() {
        return new BasisResult();
    }

    public static BasisResult Success() {
        return	BasisResult.Default().setResultCode(BasisResultCode.SUCCESS);
    }

    public static BasisResult Success(Object data) {
        return BasisResult.Success().setData(data);
    }

    public static BasisResult Fail() {
        return BasisResult.Default().setResultCode(BasisResultCode.FAIL);
    }

    public static BasisResult Fail(BasisResultCode code) {
        return BasisResult.Default().setResultCode(code);
    }

    public static BasisResult Fail(Object data) {
        return	BasisResult.Fail().setData(data);
    }

    public static BasisResult Fail(Object data, BasisResultCode code) {
        return BasisResult.Fail().setData(data).setResultCode(code);
    }

    public BasisResult error(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }


    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
