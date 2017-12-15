package com.service;

import com.bean.SysEvent;
import com.mapper.SysEventMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEntityService extends BaseService<SysEvent> {
    @Autowired
    protected SysEventMapper mapper;

    public SysEvent query(SysEvent event) throws ParserConfigurationException, IOException, SAXException {

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
        List<Long> longs = mapper.selectIdPage(map);

        return new SysEvent();
    }
}
