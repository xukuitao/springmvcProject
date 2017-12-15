package com.interceptor;

import com.alibaba.fastjson.JSON;
import com.app.LoginIntercepter;
import com.bean.SysEvent;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志拦截
 * Created by Administrator on 2017/12/7.
 */
public class LogInterceptor extends BaseInterceptor {

    @Autowired
    private BaseProvider baseProvider;

    private static final Logger logger= LoggerFactory.getLogger(LoginIntercepter.class);

    private final ThreadLocal<Long> startTimeThreadLocal=new NamedThreadLocal<Long>("ThreadLocal StartTime");

    private ExecutorService executorService =Executors.newCachedThreadPool();


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        startTimeThreadLocal.set(System.currentTimeMillis());
        return super.preHandle(request,response,handler);
    }
    public void afterCompletion(final HttpServletRequest request, HttpServletResponse response, Object handler,
                                final Exception ex){
        long startTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        if (handler instanceof HandlerMethod){
            Object current_user = session.getAttribute("current_user");
            String user_agent = (String) session.getAttribute("user_agent");
            String user_ip = (String) session.getAttribute("user_ip");

            if (servletPath.contains("read")||servletPath.contains("get")||servletPath.contains("unauthorized")
                    ||servletPath.contains("forbidden")){
                final SysEvent sysEvent = new SysEvent();
                sysEvent.setMethod(request.getMethod());
                sysEvent.setRequestUri(servletPath);
                sysEvent.setClientHost(user_ip);
                sysEvent.setUserAgent(user_agent);
                if (servletPath.contains("upload")){
                    sysEvent.setParameters("");
                }else {
                    //将request 中的参数放入map
                    Map<String,Object> map= new TreeMap<String,Object>();
                    Enumeration names = request.getParameterNames();
                    if (names!=null){
                        while (names.hasMoreElements()){
                            String name = (String) names.nextElement();
                            String[] parameterValues = request.getParameterValues(name);
                            if (!ArrayUtils.isEmpty(parameterValues)){
                                map.put(name,parameterValues);
                            }
                        }
                    }
                    String param = JSON.toJSONString(map);
                    sysEvent.setParameters(param.length()>1024?param.substring(0,1024):param);
                }
                sysEvent.setStatus(response.getStatus());
                if (current_user!=null){
                    sysEvent.setCreateBy(Long.parseLong(current_user.toString()));
                    sysEvent.setUpdateBy(Long.parseLong(current_user.toString()));
                }
                final String msg = (String) session.getAttribute("msg");

                HandlerMethod handlerMethod = (HandlerMethod) handler;
                ApiOperation annotation = handlerMethod.getMethod().getAnnotation(ApiOperation.class);
                sysEvent.setTitle(annotation.value());

                if (StringUtils.isNotBlank(msg)){
                    sysEvent.setRemark(msg);
                }else {
                    if (ex==null){
                        sysEvent.setRemark("");
                    }else {
                        StringWriter stringWriter = new StringWriter();
                        ex.printStackTrace(new PrintWriter(stringWriter));
                        sysEvent.setRemark(stringWriter.toString());
                    }
                    Parameter parameter = new Parameter("sysEntityService", "query", sysEvent);

                    baseProvider.execute(parameter);
                }
                /*executorService.submit(new Runnable() {
                    public void run() {
                        if (StringUtils.isNotBlank(msg)){
                            sysEvent.setRemark(msg);
                        }else {
                            if (ex==null){
                                sysEvent.setRemark("");
                            }else {
                                StringWriter stringWriter = new StringWriter();
                                ex.printStackTrace(new PrintWriter(stringWriter));
                                sysEvent.setRemark(stringWriter.toString());
                            }
                            Parameter parameter = new Parameter("sysEventService", "update", sysEvent);

                            baseProvider.execute(parameter);
                        }

                    }
                });*/


                try {
                    super.afterCompletion(request,response,handler,ex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
