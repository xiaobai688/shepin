package com.example.shepin.service;

import com.example.shepin.vo.goods.GoodsVO;

import java.util.List;

public interface GoodsService {

    /**
     * 查询
     */
    List<GoodsVO> getAll();

    /**
     * 查询
     */
    GoodsVO getBySkuNo(String skuNo);

    /**
     * 插入
     */
    void insert(GoodsVO goodsVO);

    /**
     * 批量插入
     */
    void batchInsert(List<GoodsVO> goodsVOList);

    /**
     * 更新
     */
    void update(GoodsVO goodsVO);

    /**
     * 批量更新
     */
    void batchUpdate(List<GoodsVO> goodsVOList);
}
