package com.example.shepin.vo.order;

import lombok.Data;

/**
 * 订单查询结果-订单商品明细
 */
@Data
public class OrderGoodsVO {
    /**平台SKU编码 示例值：850001564789655*/
    private String skuNo;
    /** 商品货号 示例值：1895464789623*/
    private String goodsModel;
    /** 商品名称 示例值：测试商品*/
    private String goodsName;
    /** 商品数量 示例值：1*/
    private String quantity;
    /** 规格描述 示例值：颜色:红色,尺码:xxl*/
    private String salePropertiesName;
    /** 商品结算价(保留两位小数) 示例值：6880.00*/
    private String costPrice;
    /** 运费(保留两位小数) 示例值：30.00*/
    private String freightFee;
    /** 税费(保留两位小数) 示例值：60.00*/
    private String taxesFee;
}
