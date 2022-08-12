package com.example.shepin.vo.goods;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 价格查询返回实体
 */
@Entity
@Table(name="t_goods_price")
@Data
public class PriceVO implements Serializable {
    /** 店铺编号*/
    private String channelNo;
    /** 规格编号-商家编码*/
    private String skuNo;

    /** 成本价*/
    private String costPrice;
    /** 市场价*/
    private String marketPrice;
    /** 海外市场价*/
    private String seaMarketPrice;
    /** 海外市场价单位*/
    private String seaMarketPriceUnit;
    /**税费(保留两位小数)*/
    private String taxesFee;
    /**运费(保留两位小数)*/
    private String freightFee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = PriceVO.class,
            cascade = {},
            fetch = FetchType.LAZY)
    private GoodsVO goodsVO;


}
