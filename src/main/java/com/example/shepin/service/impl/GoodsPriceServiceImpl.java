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
        PriceVO priceVO=new PriceVO();
        priceVO.setSkuNo(skuNo);
        Example<PriceVO> example =Example.of(priceVO);
        Optional<PriceVO> one = goodsPriceDao.findOne(example);
        if (one.isPresent()) {
            PriceVO priceVO1 =   one.get();
            return priceVO1;
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
        goodsPriceDao.saveAll(priceVOList);
    }

    @Override
    public void update(PriceVO priceVO) {
        PriceVO priceVO1=new PriceVO();
        priceVO.setSkuNo(priceVO.getSkuNo());
        Example<PriceVO> example =Example.of(priceVO1);
        Optional<PriceVO> one = goodsPriceDao.findOne(example);
        if (one.isPresent()) {
            PriceVO priceVO2 =   one.get();
            priceVO.setId(priceVO2.getId());
            goodsPriceDao.save(priceVO);
        }else{
            goodsPriceDao.save(priceVO);
        }
    }

    @Override
    public void batchUpdate(List<PriceVO> priceVOList) {
        for(PriceVO priceVO : priceVOList){
            PriceVO priceVO1=new PriceVO();
            priceVO.setSkuNo(priceVO.getSkuNo());
            Example<PriceVO> example =Example.of(priceVO1);
            Optional<PriceVO> one = goodsPriceDao.findOne(example);
            if (one.isPresent()) {
                PriceVO priceVO2 = one.get();
                priceVO.setId(priceVO2.getId());
                goodsPriceDao.save(priceVO);
            }else{
                goodsPriceDao.save(priceVO);
            }
        }
    }
}
