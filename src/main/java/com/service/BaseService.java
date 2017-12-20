package com.service;

import com.bean.BaseModel;
import com.mapper.SysEventMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;


public abstract class BaseService<T extends BaseModel> implements ApplicationContextAware {

    @Autowired
    protected SysEventMapper mapper;

    protected ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

   /* public Page<T> query(Map<String, Object> map) {
        Page<Long> page=getPage(map);
        List<Long> list = mapper.selectIdPage(page, map);
        page.setRecords(list);
        return getPage(page);
    }

    private Page<T> getPage(Page<Long> page) {
        if (page!=null){
            Page<T> tPage = new Page<T>(page.getCurrent(), page.getSize());
            tPage.setTotal(page.getTotal());
            List<T> records=new ArrayList<T>();
            Map<Integer,Object> thread=new ConcurrentHashMap<Integer, Object>();
            for (int i=0;i<page.getRecords().size();i++){
                records.set(i,queryById(page.getRecords().get(i)));
                thread.put(i,0);
            }

            while (thread.size()<records.size()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tPage.setRecords(records);
            return tPage;
        }
        return new Page<T>();
    }

    private T queryById(Long id) {
        //获取缓存键值
        String cacheKey = getCacheKey();
        String key = new StringBuilder(Constant.CACHE_NAMESPACE).append(cacheKey).append(":").append(id).toString();
        String lockKey = new StringBuilder(Constant.CACHE_NAMESPACE).append(cacheKey).append(":LOCK:").append(id).toString();
        return (T) mapper.selectById(id);
    }

    private String getCacheKey() {
        Class<?> aClass = getClass();
        String cacheName = Constant.cacheMap.get(aClass);
        if (StringUtils.isBlank(cacheName)){
            CacheConfig annotation = aClass.getAnnotation(CacheConfig.class);
            if (annotation!=null&& ArrayUtils.isNotEmpty(annotation.cacheNames())){
                cacheName = annotation.cacheNames()[0];
            }else{
                cacheName=getClass().getName();
            }
            Constant.cacheMap.put(aClass,cacheName);
        }
        return cacheName;
    }


    private Page<Long> getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "id_";
        if (isNotEmpty(params.get("pageNum"))) {
            current = Integer.valueOf(params.get("pageNum").toString());
        }
        if (isNotEmpty(params.get("pageIndex"))) {
            current = Integer.valueOf(params.get("pageIndex").toString());
        }
        if (isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }
        if (isNotEmpty(params.get("orderBy"))) {
            orderBy = (String) params.get("orderBy");
            params.remove("orderBy");
        }
        if (size == -1) {
            Page<Long> page = new Page<Long>();
            page.setOrderByField(orderBy);
            page.setAsc(false);
            return page;
        }
        Page<Long> page = new Page<Long>(current, size, orderBy);
        page.setAsc(false);
        return page;
    }
*/
    private static boolean isNotEmpty(Object pObj) {
        if (pObj == null || "".equals(pObj))
            return false;
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection<?>) {
            if (((Collection<?>) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map<?, ?>) {
            if (((Map<?, ?>) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

}
