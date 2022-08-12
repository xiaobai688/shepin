package com.example.shepin.response.goods;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.goods.StockVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class StockQueryResponse extends CommonResponse {

    private CommonPageVO<StockVO> content;
}
