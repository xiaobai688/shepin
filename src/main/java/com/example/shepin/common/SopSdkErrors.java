package com.example.shepin.common;


import com.example.shepin.response.ErrorResponse;

/**
 * @author opec
 */
public enum SopSdkErrors {
    /**
     * 网络错误
     */
    HTTP_ERROR("836875001", "网络错误"),
    /**
     * 验证返回sign错误
     */
    CHECK_RESPONSE_SIGN_ERROR("836875002", "验证服务端sign出错")
    ;

    SopSdkErrors(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.subCode = code;
        this.subMsg = msg;
    }

    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setMsg(msg);
        return errorResponse;
    }

    private String code;
    private String msg;
    private String subCode;
    private String subMsg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

}
