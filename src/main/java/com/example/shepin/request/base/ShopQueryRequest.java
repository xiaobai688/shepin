package com.example.shepin.request.base;


import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.base.ShopQueryResponse;

/**
 * 店铺查询请求类
 * @author opec
 */
public class ShopQueryRequest extends CommonRequest<ShopQueryResponse> {
    @Override
    protected String method() {
        return "shop.query";
    }


}
