package com.example.shepin.service.impl;

import com.example.shepin.dao.GoodsPriceDao;
import com.example.shepin.service.GoodsPriceService;
import com.example.shepin.vo.goods.PriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsPriceServiceImpl implements GoodsPriceService{

    @Autowired
    private GoodsPriceDao goodsPriceDao;


    @Override
    public List<PriceVO> getAll() {
        return goodsPriceDao.findAll();
    }

    @Override
    public PriceVO getBySkuNo(String skuNo) {
        PriceVO one = goodsPriceDao.findBySkuNoIs(skuNo);
        if (one!=null) {
            return one;
        } else {
            // handle not found, return null or throw
            System.out.println("no exit!");
            return null;
        }
    }

    @Override
    public void insert(PriceVO priceVO) {
        goodsPriceDao.save(priceVO);
    }

    @Override
    public void batchInsert(List<PriceVO> priceVOList) {
        for(PriceVO priceVO : priceVOList) {
            goodsPriceDao.saveAndFlush(priceVO);
        }
    }

    @Override
    public void update(PriceVO priceVO) {
        PriceVO one = goodsPriceDao.findBySkuNoIs(priceVO.getSkuNo());
        if (one!=null) {
            priceVO.setId(one.getId());
            goodsPriceDao.saveAndFlush(priceVO);
        }else{
            goodsPriceDao.save(priceVO);
        }
    }

    @Override
    public void batchUpdate(List<PriceVO> priceVOList) {
        for(PriceVO priceVO : priceVOList){
            PriceVO one = goodsPriceDao.findBySkuNoIs(priceVO.getSkuNo());
            if (one!=null) {
                priceVO.setId(one.getId());
                goodsPriceDao.saveAndFlush(priceVO);
            }else{
                goodsPriceDao.save(priceVO);
            }
        }
    }
}
