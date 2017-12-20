package com.jdbc;

import com.interceptor.Parameter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/12.
 */
@Aspect
//@Component
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
    private static final Logger logger= LoggerFactory.getLogger(DataSourceAspect.class);
    @Pointcut("execution(* com.interceptor.Provider.execute(..))")
//    @Pointcut("execution(* com.interceptor.Provider.execute())")
    public void aspect(){}

    @Before("aspect()")
    public void before(JoinPoint joinPoint){
        logger.info("=============qiemian===========");
        Parameter paramter= (Parameter) joinPoint.getArgs()[0];
        String method = paramter.getMethod();//select , update ,insert ......
        Map<String, Object> methodType = ChooseDataSource.METHOD_TYPE;
        // methodType {"read":{"select","get","count"},"write":{"update","insert","delete"}}
        try{
            for (String key : methodType.keySet()){//read、write
                List<String> list = (List<String>) methodType.get(key);
                for (String type : list){
                    if (method.startsWith(type)){
                        HandleData.setDataSource(key);//读库或者是写库
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            HandleData.setDataSource("write");
        }
    }

    @After("aspect()")
    public void after(){
        HandleData.clear();
    }
}
