package com.example.shepin.request.order;

import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.order.OrderSaveResponse;

/**
 * 订单新增请求类
 */
public class OrderSaveRequest extends CommonRequest<OrderSaveResponse> {
    @Override
    protected String method() {
        return "order.save";
    }

}
