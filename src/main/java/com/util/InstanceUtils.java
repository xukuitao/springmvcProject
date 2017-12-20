package com.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/7.
 */
public class InstanceUtils {
    public static Map<String, MethodAccess> methodMap = new HashMap<String, MethodAccess>();
    public static final Object invokeMethod(Object bean, String methodName, Object... args) {
        Class<?> aClass = bean.getClass();
        String key=null;
        if (!ArrayUtils.isEmpty(args)){
            Class<?>[] classes = new Class[args.length];
            for (int i=0;i<args.length;i++){
                if (args[i]!=null){
                    classes[i]=args[i].getClass();
                }
            }
            key=aClass+"_"+methodName+"_"+ StringUtils.join(classes,",");
        }else {
            key=aClass+"_"+methodName;
        }

        MethodAccess methodAccess = methodMap.get(key);
        if (methodAccess==null){
            methodAccess = MethodAccess.get(aClass);
            methodMap.put(key,methodAccess);
        }
        return methodAccess.invoke(bean,methodName,args);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str="\\u003f";
        System.out.println(new String(str.getBytes(),"utf-8"));
    }
}
