package com.interceptor;

import com.app.AutoApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/12/6.
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {
    static final Logger logger= LoggerFactory.getLogger(AutoApp.class);
    private BaseInterceptor[] interceptors;

    public void setNextInterceptor(BaseInterceptor... nextInterceptors){
        this.interceptors=nextInterceptors;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (interceptors==null){
            return true;
        }
        //拦截链
        for (BaseInterceptor interceptor : interceptors){
            if (!interceptor.preHandle(request,response,handler)){
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView){
        if (interceptors!=null){
            for (BaseInterceptor interceptor : interceptors){
                interceptor.postHandle(request,response,handler,modelAndView);
            }
        }
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (interceptors != null) {
            for (int i = 0; i < interceptors.length; i++) {
                interceptors[i].afterCompletion(request, response, handler, ex);
            }
        }
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (interceptors != null) {
            for (int i = 0; i < interceptors.length; i++) {
                interceptors[i].afterConcurrentHandlingStarted(request, response, handler);
            }
        }
    }

}
