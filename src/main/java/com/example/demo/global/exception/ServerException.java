package com.example.demo.global.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * service层要操作事务的统一抛异常
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:34
 */
public class ServerException extends BaseException {

    public ServerException() {
        super();
    }

    private String info;

    public ServerException(String info, Throwable cause) {
        super(info, cause);
        this.info = info;
    }

    public String getInfo () {
        if (StringUtils.isBlank(this.info)) {
            return this.getMessage();
        }
        return info;
    }

    public void setInfo ( String info ) {
        this.info = info;
    }

    public ServerException(String msg) {
        super(msg);
        this.errorMsg = msg;
    }

    @Override
    protected void initDefaultCode() {
        this.errorCode = 5001;
    }

    @Override
    public String toString() {
        return "业务异常：错误码：" + errorCode + "；错误信息：" + errorMsg;
    }
}
