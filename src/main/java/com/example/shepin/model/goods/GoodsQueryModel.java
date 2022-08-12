package com.example.shepin.model.goods;

import com.example.shepin.model.CommonQueryModel;
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
public class GoodsQueryModel extends CommonQueryModel {
    /** 必填参数 按最后修改时间增量获取数据,start_time作为开始时间，格式：yyyy-MM-dd HH:mm:ss 建议倒序拉取数据不丢失 */
    private String startTime;

    /** 必填参数 按最后修改时间增量获取数据,end_time作为结束时间，格式：yyyy-MM-dd HH:mm:ss */
    private String endTime;

    /** 商品货号*/
    private String goodsModel;

    /** 商品编号*/
    private String goodsNo;

    /** 店铺唯一编号 如果关联多个店铺可传参查询单个店铺商品数据 */
    private String channelNo;


    /** 商家编码集合,按照商家编码集合指定返回响应参数 最多不超过100个,若传该字段则时间字段失效 例如：["870000180191006","870000180191009"]*/
    private List<String> skuNos;

    /** 是否删除 0：删除，1：未删除*/
    private Integer isDel;
}
