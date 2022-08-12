package com.example.shepin.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class ShopVO implements Serializable {

   /** 店铺编号*/
   private String channelNo;
   /** 店铺名称*/
   private String channelName;

}