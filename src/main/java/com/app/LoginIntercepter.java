package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/10/20.
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {
    @Autowired
    private CheckDemo checkDemo;
    private String ref;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        return checkDemo.check(request, response);
    }


    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }
}
