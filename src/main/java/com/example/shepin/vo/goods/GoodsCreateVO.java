package com.example.shepin.vo.goods;

import com.example.shepin.model.goods.GoodsCreateModel;
import com.example.shepin.request.BaseRequestBizData;
import lombok.Data;

import java.util.List;

@Data
public class GoodsCreateVO extends BaseRequestBizData {

    List<GoodsCreateModel > goodsCreateModelList;
}
