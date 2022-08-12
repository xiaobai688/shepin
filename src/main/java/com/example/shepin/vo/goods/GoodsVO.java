package com.example.shepin.vo.goods;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 商品查询结果返回实体类
 */
@Entity
@Table(name = "t_goods")
@Data
public class GoodsVO implements Serializable {
    /** 条形码*/
    private String barcode;
    /** 品牌中文名称*/
    private String brandNameCn;
    /** 品牌英文名称*/
    private String brandNameEn;
    /** 品牌编号*/
    private String brandNo;
    /** 商品类目名称*/
    private String categoryName;
    /** 商品类目ID*/
    private String categoryNo;
    /** 商品类目名称*/
    private String categoryPathName;
    /** 店铺编号*/
    private String channelNo;
    /** 成本价*/
    private String costPrice;
    /** 商品创建时间*/
    private String createTime;
    /**运费 */
    private String generalTradeFreight;
    /** 税费*/
    private String generalTradeTax;
    /** 商品图（多张）*/
    @Transient
    private List<String> goodsDetailImage;
    /** 商品主图 （一张）*/
    private String goodsMainImage;
    /**商品货号*/
    private String goodsModel;
    /** 商品名称*/
    private String goodsName;
    /** 商品spu 编号*/
    private String goodsNo;
    /** 商品详情描述(包含图片和文字)*/
    private String goodsPcDesc;
    /** spu 所对应的属性的中文名字串 p1:v1;p2:v2 (注：属性名称中的冒号":"被转换为："#cln#"; 分号";"被转换为："#scln#")*/
    private String goodsPropertiesName;
    /**香港交货运费*/
    private String hkPickUpFreight;
    /** 0：删除，1：未删除*/
    private Integer isDel;
    /**市场价*/
    private String marketPrice;
    /**销售属性的中文名字串 p1:v1;p2:v2 (注：属性名称中的冒号":"被转换为："#cln#"; 分号";"被转换为："#scln#")*/
    private String salePropertiesName;
    /**海外市场价*/
    private String seaMarketPrice;
    /**海外市场价单位*/
    private String seaMarketPriceUnit;
    /** 规格编号-商家编码 (平台系统SKU唯一码)*/
    @JoinColumn(name="skuNo")
    private String skuNo;
    /** sku所对应的属性的中文名字串 p1:v1;p2:v2 (注：属性名称中的冒号":"被转换为："#cln#"; 分号";"被转换为："#scln#")*/
    private String skuPropertiesName;
    /** 货源地*/
    private String sourceArea;
    /** 商品修改时间*/
    private String updateTime;
    /** 商品图数量*/
    private Integer mainImageNum;
    /** 详情描述图数量*/
    private Integer descImageNum;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * CascadeType包含的类别  级联：给当前设置的实体操作另一个实体的权限
     *      CascadeType.ALL 级联所有操作
     *      CascadeType.PERSIST 级联持久化（保存）操作
     *      CascadeType.MERGE   级联更新（合并）操作
     *      CascadeType.REMOVE  级联删除操作
     *      CascadeType.REFRESH 级联刷新操作
     *      CascadeType.DETACH 级联分离操作,如果你要删除一个实体，但是它有外键无法删除，这个级联权限会撤销所有相关的外键关联。
     */
    @OneToOne(targetEntity = PriceVO.class,
            cascade = {},
            fetch = FetchType.LAZY)
    private PriceVO priceVO;

}
