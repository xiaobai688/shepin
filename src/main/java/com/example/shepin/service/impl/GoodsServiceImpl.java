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
        GoodsVO goodsVO=new GoodsVO();
        goodsVO.setSkuNo(skuNo);
        Example<GoodsVO> example =Example.of(goodsVO);
        Optional<GoodsVO> one = goodsDao.findOne(example);
        if (one.isPresent()) {
            GoodsVO goodsVO1 =   one.get();
            return goodsVO1;
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
        goodsDao.saveAll(goodsVOList);
    }

    @Override
    public void update(GoodsVO goodsVO) {
        GoodsVO goodsVO1=new GoodsVO();
        goodsVO.setSkuNo(goodsVO.getSkuNo());
        Example<GoodsVO> example =Example.of(goodsVO1);
        Optional<GoodsVO> one = goodsDao.findOne(example);
        if (one.isPresent()) {
            GoodsVO goodsVO2 =   one.get();
            goodsVO.setId(goodsVO2.getId());
            goodsDao.save(goodsVO);
        }else{
            goodsDao.save(goodsVO);
        }
    }

    @Override
    public void batchUpdate(List<GoodsVO> goodsVOList) {
        for(GoodsVO goodsVO : goodsVOList){
            GoodsVO goodsVO1=new GoodsVO();
            goodsVO.setSkuNo(goodsVO.getSkuNo());
            Example<GoodsVO> example =Example.of(goodsVO1);
            Optional<GoodsVO> one = goodsDao.findOne(example);
            if (one.isPresent()) {
                GoodsVO goodsVO2 =   one.get();
                goodsVO.setId(goodsVO2.getId());
                goodsDao.save(goodsVO);
            }else{
                goodsDao.save(goodsVO);
            }
        }
    }
}
