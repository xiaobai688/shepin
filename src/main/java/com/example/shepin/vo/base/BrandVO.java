package com.example.shepin.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class BrandVO {
    /** 品牌编号*/
    private String brandNo;
    /** 品牌中文名称*/
    private String brandNameCn;
    /** 品牌英文名称*/
    private String brandNameEn;
    /** 0：删除，1：未删除*/
    private Integer isDel;

}