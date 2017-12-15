package com.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.bean.SysEvent;
import net.sf.json.JSONObject;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEntityService extends BaseService<SysEvent> {

    public Page<SysEvent> query(SysEvent event) throws ParserConfigurationException, IOException, SAXException {

        Map<String,Object> map=new HashMap<>();
        JSONObject jsonObject = JSONObject.fromObject(event);
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()){
            Object next = keys.next();
            Object value = jsonObject.get(next);
            try{
                JSONObject value1 = JSONObject.fromObject(value);
                Iterator keys1 = value1.keys();
                while (keys1.hasNext()){
                    Object next1 = keys1.next();
                    Object value2 = value1.get(next1);
                    map.put(next1.toString(),value2);
                }
            }catch (Exception e){
                map.put(next.toString(),value);
            }
        }

        Page<SysEvent> page=super.query(map);
        for (SysEvent sysEvent : page.getRecords()){
            if(sysEvent!=null){
                Long createBy = sysEvent.getCreateBy();
                if (createBy!=null){
                    sysEvent.setUserName("233");
                }

            }
        }
        return page;
    }
}
