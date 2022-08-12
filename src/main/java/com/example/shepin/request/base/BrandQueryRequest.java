package com.example.shepin.request.base;

import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.base.BrandQueryResponse;

/**
 * 品牌查询请求类
 * @author opec
 */
public class BrandQueryRequest extends CommonRequest<BrandQueryResponse> {
    @Override
    protected String method() {
        return "brand.query";
    }


}
