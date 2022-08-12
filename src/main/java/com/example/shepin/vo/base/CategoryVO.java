package com.example.shepin.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryVO {

    /** 品类编号*/
    private String categoryNo;
    /** 品类名称*/
    private String categoryName;
    /** 父级类目品类编号*/
    private String parentCategoryNo;
    /** 子集集合*/
    private List<CategoryVO> children;

}