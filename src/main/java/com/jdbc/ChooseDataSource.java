package com.jdbc;

import org.apache.commons.collections.MapUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.*;

/**
 * Created by Administrator on 2017/12/11.
 */
public class ChooseDataSource extends AbstractRoutingDataSource {
    //存储方法类型
    public static Map<String,Object> METHOD_TYPE=new HashMap<String, Object>();

    static{
        System.setProperty("durid.log","log4j");
    }
    //获取当前数据源名称
    protected Object determineCurrentLookupKey() {
        return HandleData.getDataSource();
    }

    // 设置方法名前缀对应的数据源 xml配置
	/*<map key-type="java.lang.String">
	<entry key="read" value=",get,select,count,list,query," />
	<entry key="write" value=",add,insert,create,update,delete,remove," />
	</map>*/
    public void setMethodType(Map<String,String> map){
        if (MapUtils.isNotEmpty(map)){
            for (String key : map.keySet()){
                String[] values = map.get(key).split(",");
                List<String> list = Arrays.asList(values);
                METHOD_TYPE.put(key,list);
            }
        }

    }
}
