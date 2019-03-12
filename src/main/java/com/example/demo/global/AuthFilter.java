package com.example.demo.global;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.global.consts.Constant;
import com.example.demo.module.vo.user.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liuzy <liuzy@g2l-service.com>
 * @Date: Created in 2019/3/5 10:09
 */

@Component
public class AuthFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("=======初始化过滤器=========");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;

        long start = System.currentTimeMillis();

        MyHttpRequest myrequest = new MyHttpRequest((HttpServletRequest) request);
        if(this.beforeDone(myrequest, resp)) {
            chain.doFilter(myrequest, response);
            this.afterDone(myrequest, response);
        }
        logger.info("filter 耗时：" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        logger.info("=======销毁过滤器=========");
    }

    Boolean beforeDone(ServletRequest request, ServletResponse response) throws IOException, ServletException {

        response.setCharacterEncoding("utf-8");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Boolean flag = true;

        //如果是预请求就直接放行
        if("OPTIONS".equals(req.getMethod())) {
            return flag;
        }

        String method = req.getMethod();
        String url = req.getRequestURI();// 当前请求的url
        String params = this.getLog(this.getReqeustParameters(req));
        if (params == null) {
            params = getPayload(req);
        }

        logger.debug("--------------> request method : " + method);
        logger.debug("--------------> request url : " + url);
        logger.debug("--------------> request params : " + params);

/*        if (!checkIgnore(url)) {

            Object user = getSession(req);
            if (user == null) {
                //跳转至未登录接口 并返回到前端
                request.getRequestDispatcher(notLogin).forward(request, response);
                flag = false;
            } else if (!checkPermission(url, user)) {
                request.getRequestDispatcher(PermissionExceptionUri).forward(request, response);
                flag = false;
            }

        }*/

        return flag;
    }

    /**
     * Output Control 1.Wrap Result
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    void afterDone(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        //
    }

    // 配置系统级别无需校验的URI列表
    static List<String> excludeUri = null;
    static String PermissionExceptionUri = "/exception";
    static String notLogin = "/exception/not-login";
    static {
        excludeUri = new ArrayList<>();
        excludeUri.add(PermissionExceptionUri);
        excludeUri.add(notLogin);
        excludeUri.add(".html");
        excludeUri.add(".js");
        excludeUri.add(".css");
        excludeUri.add(".jpg");
        excludeUri.add(".jpeg");
        excludeUri.add(".pdf");
        excludeUri.add("/login");
        excludeUri.add("/logout");
    }

    /**
     * 判断请求是否拦截
     * @param url
     * @return
     */
    boolean checkIgnore(String url) {
        boolean flag = false;
        for (String exclude : excludeUri) {
            if (url.contains(exclude)) {
                flag = true;
                break;
            }
        }
        System.out.println(flag);
        return false;
    }

    /**
     * 根据token获取登录的用户信息
     * @param request
     * @return
     */
    Object getSession(HttpServletRequest request) {

        String token = request.getHeader("token");
        //cookie 仅用于方便后端开发测试 线上建议删除
        if (token == null) {
            if (request.getCookies() == null) {
                return null;
            }
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (Constant.USER_TOKEN.equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        if (token != null) {
            UserVo userVo = (UserVo) redisTemplate.opsForValue().get(Constant.USER_TOKEN + token);
            if (userVo != null) {//刷新过期时间
                //过期时间 30分钟
                redisTemplate.expire(Constant.USER_TOKEN + token, Constant.USER_TOKEN_TIMEOUT, TimeUnit.SECONDS);
            }
            return userVo;
        } else {
            return null;
        }
    }

    Boolean checkPermission(String url, Object obj) {

        return true;
    }

    /**
     * log对象转json
     * @param requestLog
     * @return
     */
    private String getLog(Map<String,String> requestLog) {
        if (requestLog.size() == 0) {
            return null;
        }

        String log = JSON.toJSONStringWithDateFormat(requestLog,
                "yyyy-MM-dd HH:mm:ss.SSS", SerializerFeature.DisableCircularReferenceDetect);
        return log;
    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    private Map<String,String> getReqeustParameters(HttpServletRequest request) {
        Map<String, String> result = new LinkedHashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();

        if (parameterMap != null && !parameterMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String key = entry.getKey();
                String[] valueArray = entry.getValue();
                String value = getStringArrayStr(valueArray, ",");
                result.put(key, value);
            }
        }
        return result;
    }

    private static String getStringArrayStr(String[] strArray, String splitSign) {
        StringBuffer stringArraySb = new StringBuffer();

        if (strArray != null && strArray.length > 0) {
            int size = strArray.length;

            for (int i = 0; i < size; i++) {
                stringArraySb.append(strArray[i]);
                int end = size - 1;

                if (i != end) {
                    stringArraySb.append(splitSign);
                }
            }
        }

        return stringArraySb.toString();
    }

    /**
     * 获取json的请求体参数
     * @param request
     * @return
     * @throws IOException
     */
    public static String getPayload(HttpServletRequest request) throws IOException {
        //由于ServletInputStream 只能被读取一次，所以我们读取ServletInputStream之前先进行备份ServletInputStream
        MyHttpRequest myHttpRequest = new MyHttpRequest(request);
        BufferedReader br = new BufferedReader(new InputStreamReader(myHttpRequest.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        return sb.toString();
    }

}
