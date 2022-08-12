package com.example.shepin.model.base;

import com.example.shepin.model.CommonQueryModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌查询参数类
 * @author opec
 */
@Data
public class BrandQueryModel extends CommonQueryModel {


    /** 批量品牌编号(最多不超过100个) */
    private List<String> brandNoList = new ArrayList<>();


}
