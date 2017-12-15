package com.app;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/10/11.
 */
@Controller
public class AutoApp {
    private static final Logger logger= LoggerFactory.getLogger(AutoApp.class);


    @RequestMapping("/finish")
    @ResponseBody
    public String index(HttpServletRequest request) throws IOException {
        Enumeration headerNames = request.getHeaderNames();
        List<Object> list=new ArrayList<Object>();
        if (headerNames.hasMoreElements()){
            Object o = headerNames.nextElement();
            list.add(o);
        }
        return "list";
    }
    @RequestMapping("/test/read")
    @ResponseBody
    @ApiOperation("测试")
    public List test(HttpServletRequest request, HttpServletResponse response) throws IOException {
       /* Enumeration headerNames = request.getHeaderNames();
        List<Object> list=new ArrayList<Object>();
        if (headerNames.hasMoreElements()){
            Object o = headerNames.nextElement();
            list.add(o);
        }
        logger.info("=============ljljl");*/
        HttpSession session = request.getSession();
        logger.info("=============ljljl===========");
        session.setAttribute("current_user","9527");
        session.setAttribute("user_agent","vip");
        session.setAttribute("user_ip","123456");
        String userName = request.getParameter("userName");
        return new ArrayList();
    }
}
