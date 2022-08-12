package com.example.shepin.service;

import com.example.shepin.vo.goods.GoodsVO;
import com.example.shepin.vo.goods.PriceVO;

import java.util.List;

public interface GoodsPriceService {

    /**
     * 查询
     */
    List<PriceVO> getAll();

    /**
     * 查询
     */
    PriceVO getBySkuNo(String skuNo);

    /**
     * 插入
     */
    void insert(PriceVO priceVO);

    /**
     * 批量插入
     */
    void batchInsert(List<PriceVO> priceVOList);

    /**
     * 更新
     */
    void update(PriceVO priceVO);

    /**
     * 批量更新
     */
    void batchUpdate(List<PriceVO> priceVOList);
}
