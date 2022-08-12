package com.example.shepin.model.order;

import com.example.shepin.model.CommonQueryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单查询查询参数
 * @author opec
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryModel extends CommonQueryModel {
    /**店铺编号*/
    private String channelNo;
    /**原始订单编号，若传该字段则时间字段失效*/
    private String outNo;
    /**平台订单编号，若传该字段则时间字段失效*/
    private String orderNo;
    /**平台订单状态 30：待发货， 55：已发货，65：签收完成，100：已退款，102：已取消*/
    private Integer orderState;
    /**开始时间(指下单时间)格式为：yyyy-MM-dd HH:mm:ss，示例：2021-11-06 16:16:20*/
    private String  startTime;
    /**结束时间(指下单时间)格式为：yyyy-MM-dd HH:mm:ss，示例：2021-12-06 16:16:20*/
    private String endTime;
}
