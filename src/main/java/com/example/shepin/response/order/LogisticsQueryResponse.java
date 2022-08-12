package com.example.shepin.response.order;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.base.LogisticsCompanyVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class LogisticsQueryResponse extends CommonResponse {

    private List<LogisticsCompanyVO> content;
}
