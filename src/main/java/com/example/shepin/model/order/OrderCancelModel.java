package com.example.shepin.model.order;

import lombok.Data;

/**
 * 订单取消请求参数
 * @author opec
 */
@Data
public class OrderCancelModel {

    /**店铺编号 示例值：3046*/
    private String channelNo;

    /**取消原因，最多200字符长度 示例值：用户不想要*/
    private String cancelCause;
    /**原始订单编号。原始订单编号不能为空，最多50字符长度,不能重复 示例值：102202110280010*/
    private String outNo;
}
