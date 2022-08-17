package com.example.shepin.vo.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name="t_goods_stock")
@EqualsAndHashCode(callSuper = false)
public class StockVO implements Serializable {

   /** 可卖数*/
   private Integer quantity;

   /** 商家编码*/
   private String skuNo;

   /**
    * 数量差异
    */
   @Transient
   private Integer num;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   /**
    * CascadeType包含的类别  级联：给当前设置的实体操作另一个实体的权限
    *      CascadeType.ALL 级联所有操作
    *      CascadeType.PERSIST 级联持久化（保存）操作
    *      CascadeType.MERGE   级联更新（合并）操作
    *      CascadeType.REMOVE  级联删除操作
    *      CascadeType.REFRESH 级联刷新操作
    *      CascadeType.DETACH 级联分离操作,如果你要删除一个实体，但是它有外键无法删除，这个级联权限会撤销所有相关的外键关联。
    */
   @OneToOne(targetEntity = PriceVO.class,
           cascade = {},
           fetch = FetchType.LAZY)
   private GoodsVO goodsVO;

}