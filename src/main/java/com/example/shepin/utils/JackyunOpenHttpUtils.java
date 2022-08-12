/*
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */

package com.example.shepin.utils;

import com.example.shepin.request.BaseRequestBizData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 请求吉客云开放平台Http辅助类。
 *
 * @author HGW
 * @since 2018-08-07  10:29:52
 */
public class JackyunOpenHttpUtils {
    //region 变量
    /**
     * 并发控制锁对象。
     */
    private final static Object __LOCK__ = new Object();
    /**
     * 在吉客云开放平台上申请的Appekey。
     */
    private final static String APPKEY = "58501194";
    /**
     * 在吉客云开放平台上申请的AppeSecret。
     */
    private final static String APPSECRET = "995c0202f9ac4542b0dcd588cf4985b7";
    /**
     * 吉客云开放平台网关。
     */
    private final static String GATEWAY = "https://open.jackyun.com/open/openapi/do";

    //endregion

    //region 请求吉客云开放平台

    /**
     * 请求吉客云开放平台。
     *
     * @param method  开放接口方法名
     * @param version 开放接口版本号(null表示默认)
     * @param data 业务请求数据
     * @return
     * @throws Exception
     */
    public static String post(String method, String version, String data) throws Exception {
        // 构造请求参数(name1=value1&name2=value2格式)。
        DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SortedMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.put("method", method);
        sortedMap.put("appkey", APPKEY);
        sortedMap.put("version", version);
        sortedMap.put("contenttype", "json");
        sortedMap.put("timestamp", DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now()));
        sortedMap.put("bizcontent", data);

        // 构建待签名的字符串。
        StringBuilder sbSignData = new StringBuilder(APPSECRET);
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sbSignData.append(entry.getKey()).append(entry.getValue());
        }
        sbSignData.append(APPSECRET);
        // 生成签名。
        sortedMap.put("sign", md5Encrypt(sbSignData.toString().toLowerCase(),"UTF-8"));

        //构造请求参数(name1=value1&name2=value2格式)
        StringBuilder sbPostData = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sbPostData.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        String postDataStr = sbPostData.substring(1);
        return postData(GATEWAY, postDataStr);
    }

    //endregion

    //region 其他方法

    /**
     * MD5哈希加密。
     *
     * @param text     原文本
     * @param encoding 编码格式
     * @return 加密后的文本
     */
    private static String md5Encrypt(String text, String encoding) throws Exception {
        if (encoding == null || encoding.isEmpty()) {
            encoding = "UTF-8";
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] resultByte = text.getBytes(encoding);
        byte[] md5Bytes = md5.digest(resultByte);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = (md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * Post方式请求
     *
     * @param url      路径
     * @param postData 发送的数据(name1=value1&name2=value2)格式
     * @return HTTP-Post返回结果
     */
    public static String postData(String url, String postData) throws IOException {
        return postData(url, postData, "UTF-8", 300000);
    }

    /**
     * Post方式请求
     *
     * @param url      路径
     * @param postData 发送的数据(name1=value1&name2=value2)格式
     * @param encoding 编码格式
     * @return HTTP-Post返回结果
     */
    public static String postData(String url, String postData, String encoding) throws IOException {
        return postData(url, postData, encoding, 300000);
    }

    /**
     * Post方式请求
     *
     * @param url      路径
     * @param postData 发送的数据(name1=value1&name2=value2)格式
     * @param encoding 编码格式
     * @param timeOut  超时时间
     * @return HTTP-Post返回结果
     */
    public static String postData(String url, String postData, String encoding, int timeOut) throws IOException {
        if (encoding == null || encoding.isEmpty()) {
            encoding = "UTF-8";
        }
        if (timeOut <= 0) {
            timeOut = 300000;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer resultBuffer = null;
        URLConnection urlConnection = null;

        // 发送Post请求
        try {
            URL postMethodUrl = new URL(url);
            // 打开连接
            urlConnection = postMethodUrl.openConnection();
            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");

            urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setConnectTimeout(timeOut);
            // 设置url连接可以用于输入输出
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            printWriter = new PrintWriter(urlConnection.getOutputStream());
            // 发送请求参数
            printWriter.print(postData);
            // flush输出流的缓冲
            printWriter.flush();
            // 定义BufferedReader输入流来读取URL的响应
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            resultBuffer = new StringBuffer();
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return resultBuffer.toString();
    }

    //endregion
}
