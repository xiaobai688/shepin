package com.example.shepin.request.goods;

import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.goods.PriceQueryResponse;

/**
 * 价格查询请求类
 * @author opec
 */
public class PriceQueryRequest extends CommonRequest<PriceQueryResponse> {
    @Override
    protected String method() {
        return "price.query";
    }


}
