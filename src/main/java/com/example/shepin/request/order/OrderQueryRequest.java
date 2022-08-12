package com.example.shepin.request.order;

import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.order.OrderQueryResponse;

/**
 * 订单查询请求类
 */
public class OrderQueryRequest extends CommonRequest<OrderQueryResponse> {
    @Override
    protected String method() {
        return "order.query";
    }

}
