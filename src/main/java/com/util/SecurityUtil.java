package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PushbackInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/12/13.
 */
public class SecurityUtil {
    public static String decrpytDes(String data,byte[] key){
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //回退流
        /*（PushbackInputStream、PushbackReader），可以把读取进来的某些数据（不需要的数据）重新回退到输入流的缓冲区之中。*/
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(data.getBytes());
            String s = digest.toString();
            return s;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
