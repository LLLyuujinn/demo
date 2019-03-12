package com.example.demo.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 自己创建的线程是不受spring容器进行管理的，所有无法进行依赖注入
 * 可以通过使用本类来获取spring容器中的类
 * 例：ConfigProperties configProperties = SpringContextHolder.getBean("configProperties");
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/6 10:49
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    /**
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.
     */
    private static ApplicationContext context;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    @Override
    public void setApplicationContext(ApplicationContext context) {
        SpringContextHolder.context = context;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }
}
