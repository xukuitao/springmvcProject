package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
public interface Constant {
    Map<Class<?>,String> cacheMap=new HashMap<Class<?>, String>();
    String CACHE_NAMESPACE = "HBKMS:";
}
