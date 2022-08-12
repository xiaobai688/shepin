package com.example.shepin.model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商品查询查询参数
 * @author opec
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenApiOrderSaveModel {
    /**店铺编号*/
    private String channelNo;
    /**原始订单编号。原始订单编号不能为空，最多50长度,不能重复*/
    private String outNo;
    /**订单备注，最多200长度*/
    private String orderMessage;
    /**收货人姓名。收货人姓名不能为空，必须与身份证匹配 最多50长度*/
    private String receiverName;
    /**身份证号。身份证号不能为空，必须与姓名匹配 最多18长度*/
    private String idCard;
    /**电话/手机。电话/手机不能为空*/
    private String mobPhone;
    /**手机区号*/
    private String areaPhone;

    /**省/市名称。省/市名称不能为空。例如：山东省；北京市等*/
    private String provinceName;
    /**市/区名称。市/区名称不能为空，例如：济南市；朝阳区等*/
    private String cityName;
    /**县/街道名称*/
    private String areaName;
    /**详细地址。详细地址不能为空*/
    private String address;
    /**邮编*/
    private String zipCode;
    /**商品信息。sku信息不能为空,可以包含多个sku信息。skuNo是平台sku编码,quantity是购买sku数量。示例：[{"skuNo":"870000112739118","quantity":1},{"skuNo":"870000112711876","quantity":1}]*/
    private List<OrderSaveGoodsModel> goodsList;
}
