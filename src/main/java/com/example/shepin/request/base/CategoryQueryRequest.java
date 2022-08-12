package com.example.shepin.request.base;


import com.example.shepin.request.CommonRequest;
import com.example.shepin.response.base.CategoryQueryResponse;

/**
 * 品类查询请求类
 * @author opec
 */
public class CategoryQueryRequest extends CommonRequest<CategoryQueryResponse> {
    @Override
    protected String method() {
        return "category.query";
    }
}
