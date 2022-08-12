package com.example.shepin.response.order;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.CommonPageVO;
import com.example.shepin.vo.order.OrderVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author opec
 * 订单查询响应类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderQueryResponse extends CommonResponse {
    /**订单查询返回内容*/
    private CommonPageVO<OrderVO> content;
}
