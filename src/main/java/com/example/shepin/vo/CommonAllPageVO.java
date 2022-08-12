package com.example.shepin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommonAllPageVO<T> implements Serializable {

    private List<T> list;

    private Integer total;

    private String scrollNo;

}
