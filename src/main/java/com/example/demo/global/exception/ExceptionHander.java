package com.example.demo.global.exception;

import com.example.demo.global.BasisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 异常拦截器
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:47
 */
@ControllerAdvice
public class ExceptionHander {

    public static final Logger logger = LoggerFactory.getLogger(ExceptionHander.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BasisResult handlerMethodArgumentException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer errMes = new StringBuffer();
        if(bindingResult.hasErrors()){
            List<ObjectError> ls=bindingResult.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                errMes.append("["+ls.get(i).getDefaultMessage()+"]");
            }
            logger.error("[参数异常]{}",errMes);
            return null;
            //return new BasisResult().error(1000,errMes.toString());
        }else{
            logger.error("[系统异常]{}",e);
            return null;
            //return BasisResult.Fail();
        }
    }
}
