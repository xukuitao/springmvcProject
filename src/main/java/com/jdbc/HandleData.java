package com.jdbc;

/**
 * Created by Administrator on 2017/12/12.
 */
public class HandleData {
    //本地线程存储数据源名称
    private static ThreadLocal<String> holder=new ThreadLocal<String>();

    public static String getDataSource(){
        return holder.get();
    }
    public static void setDataSource(String dataSource){
        holder.set(dataSource);
    }
    public static void clear(){
        holder.remove();
    }
}
