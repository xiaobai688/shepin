package com.example.shepin.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品类查询参数类
 * @author opec
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryQueryModel {

    /** 品类编号 */
    private String categoryNo;


}
