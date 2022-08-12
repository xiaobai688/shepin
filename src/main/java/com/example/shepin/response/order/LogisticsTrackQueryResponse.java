package com.example.shepin.response.order;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.order.LogisticsVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class LogisticsTrackQueryResponse extends CommonResponse {

    private LogisticsVO content;
}
