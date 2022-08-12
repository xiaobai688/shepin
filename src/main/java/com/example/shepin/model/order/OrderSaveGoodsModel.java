package com.example.shepin.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 增加订单-请求参数-商品信息
 * @author opec
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveGoodsModel {

    /**购买sku数量。购买sku数量不能为空，必须为大于零*/
    private Integer quantity;

    /**平台SKU编码*/
    private String skuNo;
}
