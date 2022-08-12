package com.example.shepin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommonPageVO<T> implements Serializable {
    private List<T> list;

    private Integer total;

}
