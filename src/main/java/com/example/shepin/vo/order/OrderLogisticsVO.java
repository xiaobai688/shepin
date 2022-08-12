package com.example.shepin.vo.order;

import lombok.Data;

/**
 * 订单查询返回结果 -订单物流明细
 */
@Data
public class OrderLogisticsVO {

    /**发货时间 示例值：2021-12-16 15:37:17*/
    private String deliveryTime;

    /**物流公司名称 示例值：顺丰速运*/
    private String logisticsName;

    /**物流单号 示例值：SF1042937549047*/
    private String logisticsNo;
}
