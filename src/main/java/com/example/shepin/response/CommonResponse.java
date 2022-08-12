package com.example.shepin.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回对象，后续返回对象都要继承这个类
 * {
 * "alipay_trade_close_response": {
 * "code": "20000",
 * "msg": "Service Currently Unavailable",
 * "sub_code": "isp.unknown-error",
 * "sub_msg": "系统繁忙"
 * },
 * "sign": "ERITJKEIJKJHKKKKKKKHJEREEEEEEEEEEE"
 * }
 *
 * @author opec
 */
@Setter
@Getter
public abstract class CommonResponse {

    public static String CODE_SUCCESS = "0";

    private String code;

    private String msg;

    @JSONField(serialize = false)
    public boolean isSuccess() {
        return CODE_SUCCESS.equals(this.code);
    }

    public boolean isError() {
        return !this.isSuccess();
    }

    @JSONField(name = "sub_code")
    private String subCode;

    @JSONField(name = "sub_msg")
    private String subMsg;

    @JSONField(serialize = false)
    private String body;


}
