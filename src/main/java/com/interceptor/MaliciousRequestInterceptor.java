package com.interceptor;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/6.
 */
public class MaliciousRequestInterceptor extends BaseInterceptor{

    private Boolean allRequest = false; // 拦截所有请求,否则拦截相同请求
    private Long minRequestIntervalTime; // 允许的最小请求间隔
    private Integer maxMaliciousTimes; // 允许的最大恶意请求次数

    public Boolean getAllRequest() {
        return allRequest;
    }

    public void setAllRequest(Boolean allRequest) {
        this.allRequest = allRequest;
    }

    public Long getMinRequestIntervalTime() {
        return minRequestIntervalTime;
    }

    public void setMinRequestIntervalTime(Long minRequestIntervalTime) {
        this.minRequestIntervalTime = minRequestIntervalTime;
    }

    public Integer getMaxMaliciousTimes() {
        return maxMaliciousTimes;
    }

    public void setMaxMaliciousTimes(Integer maxMaliciousTimes) {
        this.maxMaliciousTimes = maxMaliciousTimes;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
             {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers",
                "x-requested-with,Access-Control-Allow-Origin,EX-SysAuthToken,EX-JSESSIONID");

        String servletPath = request.getServletPath();

        if (servletPath.startsWith("/unauthorized")||servletPath.endsWith("/forbidden")){
            return super.preHandle(request,response,handler);
        }

        HttpSession session = request.getSession();

        String prerequest = (String) session.getAttribute("PREREQUEST");
        Long prerequest_time = (Long) session.getAttribute("PREREQUEST_TIME");
        if (StringUtils.isNotBlank(prerequest)&&prerequest_time!=null){

            if ((servletPath.equals(prerequest)||allRequest)&&System.currentTimeMillis()-prerequest_time<minRequestIntervalTime){
                Integer malicious_request_times = (Integer) session.getAttribute("MALICIOUS_REQUEST_TIMES");
                if (malicious_request_times==null){
                    malicious_request_times=1;
                }else {
                    malicious_request_times++;
                }
                session.setAttribute("MALICIOUS_REQUEST_TIMES",malicious_request_times);
                if (malicious_request_times>maxMaliciousTimes){
                    response.setStatus(207);//频繁操作
                    logger.warn("interceptor a malicious request url is {}",servletPath);
                    try {
                        response.getWriter().write("====你他妈的恶意请求，已经被老子拦截了======");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            }else {
                session.setAttribute("MALICIOUS_REQUEST_TIMES",0);
            }

        }
                 session.setAttribute("PREREQUEST",servletPath);
                 session.setAttribute("PREREQUEST_TIME",System.currentTimeMillis());
                 return super.preHandle(request,response,handler);
    }
}
