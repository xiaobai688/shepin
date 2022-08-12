package com.example.shepin.model.goods;

import com.example.shepin.model.CommonQueryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品查询查询参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsAllQueryModel extends CommonQueryModel {

    /**
     * 店铺唯一编号 如果关联多个店铺可传参查询单个店铺商品数据
     */
    private String channelNo;

    /**
     * 上一页最后一条数据的商品编号
     */
    private String scrollNo;


}
