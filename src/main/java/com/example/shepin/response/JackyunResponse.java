package com.example.shepin.response;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.example.shepin.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class JackyunResponse {
    private String code;
    private String subCode;
    private String msg;
    private Object result;

    public JackyunResponse(Object result, String subCode, String msg, String contextId) {
        Map<String, Object> map = new HashMap();
        map.put("data", result);
        map.put("contextId", contextId);
        this.result = map;
        this.subCode = subCode;
        this.msg = msg;
    }

    public JackyunResponse(Object result, String subCode, String msg) {
        this(result, subCode, msg, (String)null);
    }

    public JackyunResponse(String subCode, String msg) {
        this((Object)null, subCode, msg, (String)null);
    }

    public JackyunResponse() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }

    public void onFail(String msg, String subCode) {
        this.code = "0";
        this.msg = msg;
        this.subCode = subCode;
    }

    public void onSuccess() {
        this.code = "200";
    }

    public boolean isSuccess() {
        return "200".equalsIgnoreCase(this.code);
    }
}
