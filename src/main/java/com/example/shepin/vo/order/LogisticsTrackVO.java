package com.example.shepin.vo.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class LogisticsTrackVO implements Serializable {

   /** 物流接收时间 2021-12-16 15:37:17*/
   private String acceptTime;
   /** 物流轨迹详细*/
   private String content;

}