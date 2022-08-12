package com.example.shepin.response.order;

import com.example.shepin.response.CommonResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author opec
 * 订单取消相应类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderCancelResponse extends CommonResponse {
    /**订单取消返回内容*/
    private String content;
}
