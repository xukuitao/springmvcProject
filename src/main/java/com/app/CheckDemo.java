package com.app;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/10/20.
 */
@Component
public class CheckDemo {
    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        return id.equals("1");
    }
}
