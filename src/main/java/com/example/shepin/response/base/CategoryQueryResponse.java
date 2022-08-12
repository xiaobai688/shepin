package com.example.shepin.response.base;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.base.CategoryVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryQueryResponse extends CommonResponse {

    private CategoryVO content;
}
