package com.example.shepin.response.goods;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.CommonAllPageVO;
import com.example.shepin.vo.goods.GoodsVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsAllQueryResponse extends CommonResponse {
    private CommonAllPageVO<GoodsVO> content;
}
