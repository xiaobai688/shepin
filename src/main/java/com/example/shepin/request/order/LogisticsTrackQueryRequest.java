package com.example.shepin.request.order;


import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.order.LogisticsTrackQueryResponse;

public class LogisticsTrackQueryRequest extends CommonRequest<LogisticsTrackQueryResponse> {
    @Override
    protected String method() {
        return "logisticstrack.query";
    }


}
