package com.example.shepin.service;

import com.example.shepin.vo.goods.PriceVO;
import com.example.shepin.vo.goods.StockVO;

import java.util.List;

public interface GoodsStockService {

    /**
     * 查询
     */
    List<StockVO> getAll();

    /**
     * 查询
     */
    StockVO getBySkuNo(String skuNo);

    /**
     * 插入
     */
    void insert(StockVO stockVO);

    /**
     * 批量插入
     */
    void batchInsert(List<StockVO> stockVOList);

    /**
     * 更新
     */
    void update(StockVO stockVO);

    /**
     * 批量更新
     */
    void batchUpdate(List<StockVO> stockVOList);
}
