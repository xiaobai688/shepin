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
    private Integer pageSize = 100;

    @JSONField(name = "pageNo")
    private Integer pageNo = 1;

}
