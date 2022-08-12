package com.example.shepin.response.base;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.base.BrandVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class BrandQueryResponse extends CommonResponse {

    private CommonPageVO<BrandVO> content;
}
