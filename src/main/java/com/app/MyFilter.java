package com.app;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/23.
 */
public class MyFilter implements Filter {


    private String charset="UTF-8";
    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("myFilter init......");
        this.config=filterConfig;
        String charset=config.getServletContext().getInitParameter("charset");
        if (charset!=null&&charset.trim().length()>0){
            this.charset=charset;
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding(charset);
        servletResponse.setCharacterEncoding(charset);
        System.out.println("请求被"+config.getFilterName()+"过滤");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("响应被"+config.getFilterName()+"过滤");
    }

    public void destroy() {
        System.out.println("myFilter destroty.....");
        this.config=null;

    }
}
