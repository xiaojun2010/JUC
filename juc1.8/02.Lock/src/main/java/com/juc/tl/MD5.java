package com.juc.tl;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    static String str = "123test";
    public static void main(String[] args) throws NoSuchAlgorithmException{

        // 第一步，获取MessageDigest对象，参数为MD5字符串，表示这是一个MD5算法
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        // 第二步，输入源数据，参数类型为byte[]

        md5.update(str.getBytes(StandardCharsets.UTF_8));

        // 第三步，计算MD5值
        // String resultArray = md5.digest().toString();
        /*
         * digest() 方法返回值是一个字节数组类型的 16 位长度的哈希值，通常，我们会
         * 转化为十六进制的 32 位长度的字符串来使用，可以利用 BigInteger 类来做这个转化：
         */
        BigInteger bigInt = new BigInteger(1, md5.digest());
        String resultStr = bigInt.toString(64);

        System.out.print(str + " 的MD5为：");
        System.out.println(resultStr);
        System.out.println(resultStr.length());
    }

}