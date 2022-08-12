package com.example.shepin.request.goods;


import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.goods.StockQueryResponse;

/**
 * 库存查询请求类
 * @author opec
 */
public class StockQueryRequest extends CommonRequest<StockQueryResponse> {

    @Override
    protected String method() {
        return "stock.query";
    }

}
