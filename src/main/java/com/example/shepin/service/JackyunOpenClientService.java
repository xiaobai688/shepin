/*
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */

package com.example.shepin.service;

import com.example.shepin.request.BaseRequestBizData;
import com.example.shepin.response.JackyunResponse;
import com.example.shepin.utils.JackyunOpenHttpUtils;
import com.example.shepin.utils.JsonUtils;

/**
 * 请求吉客云开放平台服务。
 *
 * @author HGW
 * @since 2018-08-29  14:46:43
 */
public class JackyunOpenClientService {
    /**
     * 请求开放平台服务。
     *
     * @param method  开放接口方法名
     * @param version 开放接口版本号(null表示默认)
     * @param data 请求业务数据
     * @return OpenResponse返回对象
     */
    public JackyunResponse call(String method, String version, String data) {
        //接口返回值
        JackyunResponse response = null;
        //返回值字符串
        String strResponse = null;
        try {
            //请求吉客云开放接口。
            strResponse = JackyunOpenHttpUtils.post(method, version, data);
        } catch (Exception ex) {
            response = new JackyunResponse();
            response.onFail(ex.getMessage(), "CLIENT_EXCEPTION");
            return response;
        }
        return JsonUtils.deJson(strResponse, JackyunResponse.class);
    }
}
