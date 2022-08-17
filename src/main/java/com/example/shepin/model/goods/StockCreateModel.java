package com.example.shepin.model.goods;

import com.example.shepin.model.CommonQueryModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存查询参数类
 * @author opec
 */
@Data
public class StockCreateModel extends CommonQueryModel {

    /** 必填参数 部门编号 */
    private String applyDepartCode;


    /**  公司编号 */
    private String applyCompanyCode;

    /**  入库仓库code */
    private String inWarehouseCode;
    /**  出库仓库code */
    private String outWarehouseCode;
    /**  入库类型 101=采购入库 102=调拨入库 103=盘盈入库 104=其他入库*/
    private String inType;
    /**  出库类型 202调拨出库 203 = 盘亏出库 204-其他出库 205采购退货 206生产领料 207组装拆卸出库 208翻新出库 209报废出库 210残次品出库 211倒冲出库 212 包材出库 215维修还厂 299可用库存修正 231成本调整出库*/
    private String outType;
    /**  关联单据编号 */
    private String relDataId;
    /**  申请人 */
    private String applyUserName;
    /**  申请部门 */
    private String applyDepartName;
    /**  申请时间 */
    private LocalDateTime applyDate;
    /**  制单人*/
    private String operator;
    /**  写死:OPEN */
    private String source;
    /** 商品库存集合 */
    private List<GoodsCreateModel>  stockInDetailViews;
    /** 商品库存集合 */
    private List<GoodsCreateModel>  stockOutDetailViews;

}
