package com.example.shepin.model.goods;

import com.example.shepin.model.CommonQueryModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存查询参数类
 * @author opec
 */
@Data
public class StockQueryModel extends CommonQueryModel {

    /** 必填参数 店铺编号 */
    private String channelNo;

    /** 商家编码集合,按照商家编码集合指定返回响应参数 最多不超过100个,若传该字段则不校验时间 */
    private List<String> skuNos = new ArrayList<>();

    /**  按最后修改时间增量获取数据,start_time作为开始时间，格式：yyyy-MM-dd HH:mm:ss 结束时间 减开始时间不能大于1个小时 建议倒序拉取数据不丢失 */
    private String startTime;

    /**  按最后修改时间增量获取数据,end_time作为结束时间，格式：yyyy-MM-dd HH:mm:ss */
    private String endTime;

}
