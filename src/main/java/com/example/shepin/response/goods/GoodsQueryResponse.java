package com.example.shepin.response.goods;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.goods.GoodsVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author opec
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsQueryResponse extends CommonResponse {
    private CommonPageVO<GoodsVO> content;
}
