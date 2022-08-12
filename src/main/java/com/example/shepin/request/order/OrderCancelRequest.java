package com.example.shepin.request.order;

import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.order.OrderCancelResponse;

/**
 * 订单取消请求类
 */
public class OrderCancelRequest extends CommonRequest<OrderCancelResponse> {
    @Override
    protected String method() {
        return "order.cancel";
    }

}
