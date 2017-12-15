package com.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by Administrator on 2017/10/23.
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {

    public MyRequestWrapper(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }
}
