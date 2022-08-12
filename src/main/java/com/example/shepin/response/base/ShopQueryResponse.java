package com.example.shepin.response.base;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.base.ShopVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class ShopQueryResponse extends CommonResponse {

    private List<ShopVO> content;
}
