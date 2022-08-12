package com.example.shepin.response.order;

import com.example.shepin.response.CommonResponse;
import com.example.shepin.vo.order.OrderBaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author opec
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderSaveResponse extends CommonResponse {

    /**订单新增返回内容*/
    private OrderBaseVO content;
}
