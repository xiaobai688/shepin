package com.example.shepin.vo.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class LogisticsCompanyVO implements Serializable {

   /** 物流公司名称*/
   private String logisticsName;
   /** 物流公司编码*/
   private String logisticsCode;

}