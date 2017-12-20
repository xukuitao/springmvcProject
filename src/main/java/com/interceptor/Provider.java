package com.interceptor;

import com.util.InstanceUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/12/7.
 */
@Component
public class Provider implements ApplicationContextAware ,BaseProvider{

    private ApplicationContext applicationContext;

    public Parameter execute(Parameter parameter){
//        String no = parameter.getNo();
        Object bean = applicationContext.getBean(parameter.getService());

        String method = parameter.getMethod();
        Object[] param = parameter.getParam();
        Object o = InstanceUtils.invokeMethod(bean, method, param);
        return new Parameter(o);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str="cardNo=1010000092161731&expoints=10&timeStamp=1513656367494&userId=100000002&token=b24aaabb1ea8a155c4572570cd260313";
        String sign1="145D36018549FA41F35972F29287E0AC";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(str.getBytes("utf-8"));
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            String s = Integer.toHexString((b & 0x000000ff) | 0xffffff00).substring(6);
            builder.append(s);
        }
        String sign = builder.toString().toUpperCase();
        System.out.println(sign1.equals(sign));
    }
}
