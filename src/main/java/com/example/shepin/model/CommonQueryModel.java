package com.example.shepin.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 查询参数common基类
 * @author opec
 */
@Data
public class CommonQueryModel {

    @JSONField(name = "pageSize")
    private Integer pageSize = 1;

    @JSONField(name = "pageNo")
    private Integer pageNo = 10;

}
