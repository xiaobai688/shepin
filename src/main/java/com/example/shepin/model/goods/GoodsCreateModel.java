package com.example.shepin.model.goods;

import com.example.shepin.model.CommonQueryModel;
import com.example.shepin.request.BaseRequestBizData;
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
public class GoodsCreateModel extends BaseRequestBizData {
    /** 商品名称 */
    private String goodsName;

    /** 商品编号 */
    private String goodsNo;

    /** 商品分类*/
    private String cateCode;

    /** 商品分类名称*/
    private String cateName;

    /** 品牌名称 */
    private String brandName;


    /** 货品备注*/
    private String goodsMemo;

    /** 计量单位 */
    private String unitName;

    /** 外部编码 */
    private String outSkuCode;

    /** 规格名称*/
    private String skuName;

    /** 条形码*/
    private String skuBarcode;

    /** 货主编码*/
    private String ownerCode;

    /** 主图*/
    private String mainGoodsUrl;
    /** 主图*/
    private String leftGoodsUrl;
    /** 主图*/
    private String rightGoodsUrl;
    /** 主图*/
    private String topGoodsUrl;
    /** 主图*/
    private String belowGoodsUrl;
    /** 主图*/
    private String sellInfo;
}
