package com.util;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/12/13.
 */
public class PropertyUtils extends PropertyPlaceholderConfigurer {
    private static final byte[] KEY = { 9, -1, 0, 5, 39, 8, 6, 19 };
    private static Map<String, String> ctxPropertiesMap;
    private List<String> decryptProperties;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        ctxPropertiesMap=new HashMap<String, String>();
        for (Object o : props.keySet()) {
            String key = o.toString();
            String property = props.getProperty(key);
            if (CollectionUtils.isNotEmpty(decryptProperties)&&decryptProperties.contains(key)){
                property = SecurityUtil.decrpytDes(property, KEY);
                props.setProperty(key,property);
            }
            ctxPropertiesMap.put(key,property);
        }
    }
    public void setDecryptProperties(List<String> decryptProperties){
        this.decryptProperties=decryptProperties;
    }
}
