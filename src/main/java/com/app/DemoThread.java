package com.app;

/**
 * Created by Administrator on 2017/10/12.
 */
public class DemoThread {
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };
    public int getNextNum(){
        threadLocal.set(threadLocal.get()+1);
        return threadLocal.get();
    }
    int num=2;
    public int getNum(){
        return num++;
    }
    public static void main(String[] args) {
        final DemoThread demoThread=new DemoThread();
        for (int i=0;i<3;i++){
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("当前线程"+Thread.currentThread().getName()+",threadLocal="+demoThread.getNextNum()+",num="+demoThread.getNum());
                }
            }).start();
        }
        System.out.println(Integer.parseInt("12"));
    }
}
