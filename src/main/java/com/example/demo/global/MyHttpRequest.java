package com.example.demo.global;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 由于ServletInputStream 只能被读取一次，
 * 所以我们读取ServletInputStream之前先进行备份ServletInputStream
 * @Author: lzy
 * @Date: Created in 23:28 2019/3/5
 */
public class MyHttpRequest extends HttpServletRequestWrapper {
    private static Logger log = LoggerFactory.getLogger(MyHttpRequest.class);
    private byte[] bytes;
    private String queryString;
    /**
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest} object.
     * @throws IOException
     */
    public MyHttpRequest(HttpServletRequest request) throws IOException {
        super(request);
        bytes = IOUtils.toByteArray(request.getInputStream());
        queryString = request.getQueryString();
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new DelegatingServletInputStream(byteArrayInputStream);
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
