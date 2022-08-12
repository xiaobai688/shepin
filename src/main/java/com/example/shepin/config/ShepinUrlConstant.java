package com.example.shepin.config;

/**
 * 钉钉开放接口网关常量
 * @author Administrator
 */
public class ShepinUrlConstant {

    /**
     * 获取access_token url
     */
    public static final String REQUEST_URL = "http://release.sop-gateway.opechk.com";
    /**
     * 通过免登授权码获取用户信息 url
     */
    public static final String GET_USER_INFO_URL = "https://oapi.dingtalk.com/topapi/v2/user/getuserinfo";
    /**
     * 根据用户id获取用户详情 url
     */
    public static final String USER_GET_URL = "https://oapi.dingtalk.com/topapi/v2/user/get";
    /**
     * 发送群助手消息 url
     */
    public static final String SCENCEGROUP_MESSAGE_SEND_V2 = "https://oapi.dingtalk.com/topapi/im/chat/scencegroup/message/send_v2";
    /**
     * 上传文件
     */
    public static final String UPLOAD = "https://oapi.dingtalk.com/media/upload";

    /**
     * 获取在职员工列表
     */
    public static final String QUERYONJOB = "https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/queryonjob";

    /**
     * 获取员工花名册字段信息
     */
    public static final String QUERYONJOBLIST = "https://oapi.dingtalk.com/topapi/smartwork/hrm/employee/v2/list";

    /**
     * 获取员工考勤信息
     */
    public static final String ATTENDANCELIST = "https://oapi.dingtalk.com/attendance/list";

    /**
     * 获取员工考勤详细信息
     */
    public static final String ATTENDANCELISTRECORD = "https://oapi.dingtalk.com/attendance/listRecord";

    /**
     * 获取对应审批详情
     */
    public static final String PROCESSINSTANCE_INFO = "https://oapi.dingtalk.com/topapi/processinstance/get";

    /**
     * 获取用户发出的日志列表
     */
    public static final String REPORTLIST = "https://oapi.dingtalk.com/topapi/report/list";
}
