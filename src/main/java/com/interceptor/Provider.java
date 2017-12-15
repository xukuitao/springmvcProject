package com.interceptor;

import com.util.InstanceUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/7.
 */
@Component
public class Provider implements ApplicationContextAware ,BaseProvider{

    private ApplicationContext applicationContext;

    public Parameter execute(Parameter parameter){
        String no = parameter.getNo();
        Object bean = applicationContext.getBean(parameter.getService());

        String method = parameter.getMethod();
        Object[] param = parameter.getParam();
        Object o = InstanceUtils.invokeMethod(bean, method, param);
        return new Parameter(o);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
