package com.example.shepin.request.goods;


import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.goods.GoodsAllQueryResponse;

/**
 * 商品查询请求类
 */
public class GoodsAllQueryRequest extends CommonRequest<GoodsAllQueryResponse> {


    /**
     * 定义接口名称
     *
     * @return 接口名称
     */
    @Override
    protected String method() {
        return "goods.queryAll";
    }
}
