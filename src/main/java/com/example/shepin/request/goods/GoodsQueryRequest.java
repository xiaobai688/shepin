package com.example.shepin.request.goods;

import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.goods.GoodsQueryResponse;

/**
 * 商品查询请求类
 * @author opec
 */
public class GoodsQueryRequest extends CommonRequest<GoodsQueryResponse> {
    @Override
    protected String method() {
        return "goods.query";
    }


}
