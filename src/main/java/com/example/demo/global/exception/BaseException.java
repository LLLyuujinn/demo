package com.example.demo.global.exception;

/**
 * 抽象异常类
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:35
 */
public abstract class BaseException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected int errorCode;
    protected String errorMsg;

    public BaseException() {
        initDefaultCode();
    }

    public BaseException(int code) {
        this.errorCode = code;
    }

    public BaseException(String msg) {
        this.errorMsg = msg;
        initDefaultCode();
    }

    public BaseException(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BaseException(Throwable cause) {
        initDefaultCode();
    }

    public BaseException(int code, Throwable cause) {
        this.errorCode = code;
    }

    public BaseException(String msg, Throwable cause) {
        this.errorMsg = msg;
        initDefaultCode();
    }

    public BaseException(int code, String msg, Throwable cause) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    /**
     * 初始化默认错误码
     */
    protected void initDefaultCode() {
        this.errorCode = 4000;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "Expection，error code：" + errorCode + "；error info：" + errorMsg;
    }
}
