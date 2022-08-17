package com.example.shepin.service.impl;

import com.example.shepin.dao.GoodsDao;
import com.example.shepin.service.GoodsService;
import com.example.shepin.vo.goods.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;


    @Override
    public List<GoodsVO> getAll() {
        return goodsDao.findAll();
    }

    @Override
    public GoodsVO getBySkuNo(String skuNo) {
        GoodsVO bySkuNoIs = goodsDao.findBySkuNoIs(skuNo);
        if (bySkuNoIs!=null) {
            return bySkuNoIs;
        } else {
            // handle not found, return null or throw
            System.out.println("no exit!");
            return null;
        }
    }

    @Override
    public void insert(GoodsVO goodsVO) {
        goodsDao.save(goodsVO);
    }

    @Override
    public void batchInsert(List<GoodsVO> goodsVOList) {
        goodsDao.saveAllAndFlush(goodsVOList);
    }

    @Override
    public void update(GoodsVO goodsVO) {
        GoodsVO one = goodsDao.findBySkuNoIs(goodsVO.getSkuNo());
        if (one!=null) {
            goodsVO.setId(one.getId());
            goodsDao.saveAndFlush(goodsVO);
        }else{
            goodsDao.save(goodsVO);
        }
    }

    @Override
    public void batchUpdate(List<GoodsVO> goodsVOList) {
        for(GoodsVO goodsVO : goodsVOList){
            GoodsVO one = goodsDao.findBySkuNoIs(goodsVO.getSkuNo());
            if (one!=null) {
                goodsVO.setId(one.getId());
                goodsDao.saveAndFlush(goodsVO);
            }else{
                goodsDao.save(goodsVO);
            }
        }
    }
}
