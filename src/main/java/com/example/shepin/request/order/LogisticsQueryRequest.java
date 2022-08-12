package com.example.shepin.request.order;


import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.order.LogisticsQueryResponse;

public class LogisticsQueryRequest extends CommonRequest<LogisticsQueryResponse> {
    @Override
    protected String method() {
        return "logistics.query";
    }


}
