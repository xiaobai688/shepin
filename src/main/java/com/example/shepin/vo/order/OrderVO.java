package com.example.shepin.vo.order;

import lombok.Data;

import java.util.List;

/**
 * 订单查询返回结果
 */
@Data
public class OrderVO extends OrderBaseVO {

    /** 订单结算总金额(保留两位小数) 示例值：6888.00*/
    private String orderTotalAmount;
    /**税费总金额(保留两位小数) 示例值：60.00*/
    private String taxesTotalAmount;
    /**运费总金额(保留两位小数) 示例值：30.00*/
    private String freightTotalAmount;
    /**订单商品结算金额(保留两位小数) 示例值：6880.00*/
    private String goodsTotalAmount;
    /** 平台订单状态 30：待发货， 55：已发货，65：签收完成，100：已退款，102：已取消 示例值：65*/
    private Integer orderState;
    /**订单状态描述,与orderState对应 示例值：签收完成*/
    private String orderStateDesc;
    /**订单商品数量 示例值：1*/
    private Integer quantity;
    /**订单备注 示例值：订单备注信息*/
    private String orderRemark;
    /**订单创建时间 示例值：2021-12-06 16:16:20*/
    private String createTime;

    /**订单商品明细*/
    private List<OrderGoodsVO> orderGoodsList;

    /**订单物流明细*/
    private List<OrderLogisticsVO> logisticsInfoDtoList;

    /** 总页数：100*/
    private Integer total;



}
