package com.example.shepin.vo.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class OrderBaseVO implements Serializable {

   /** 原始单号 示例值：102202110280010*/
   private String outNo;
   /** 平台订单编号 示例值：OP2021102800010*/
   private String orderNo;

}