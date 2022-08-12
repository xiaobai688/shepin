package com.example.shepin.vo.order;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class LogisticsVO implements Serializable {

   /** 物流单号*/
   private String logisticsNo;
   /** 物流公司名称*/
   private String logisticsName;
   /** 物流公司编码*/
   private String logisticsCode;
   /** 轨迹列表*/
   private List<LogisticsTrackVO> trackList;
}