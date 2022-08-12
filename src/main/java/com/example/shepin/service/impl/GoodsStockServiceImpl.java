package com.example.shepin.service.impl;

import com.example.shepin.dao.GoodsStockDao;
import com.example.shepin.service.GoodsStockService;
import com.example.shepin.vo.goods.StockVO;
import com.example.shepin.vo.goods.StockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsStockServiceImpl implements GoodsStockService {

    @Autowired
    private GoodsStockDao goodsStockDao;


    @Override
    public List<StockVO> getAll() {
        return goodsStockDao.findAll();
    }

    @Override
    public StockVO getBySkuNo(String skuNo) {
        StockVO stockVO=new StockVO();
        stockVO.setSkuNo(skuNo);
        Example<StockVO> example =Example.of(stockVO);
        Optional<StockVO> one = goodsStockDao.findOne(example);
        if (one.isPresent()) {
            StockVO stockVO1 =   one.get();
            return stockVO1;
        } else {
            // handle not found, return null or throw
            System.out.println("no exit!");
            return null;
        }
    }

    @Override
    public void insert(StockVO stockVO) {
        goodsStockDao.save(stockVO);
    }

    @Override
    public void batchInsert(List<StockVO> stockVOList) {
        goodsStockDao.saveAll(stockVOList);
    }

    @Override
    public void update(StockVO stockVO) {
        StockVO stockVO1=new StockVO();
        stockVO.setSkuNo(stockVO.getSkuNo());
        Example<StockVO> example =Example.of(stockVO1);
        Optional<StockVO> one = goodsStockDao.findOne(example);
        if (one.isPresent()) {
            StockVO stockVO2 =   one.get();
            stockVO.setId(stockVO2.getId());
            goodsStockDao.save(stockVO);
        }else{
            goodsStockDao.save(stockVO);
        }
    }

    @Override
    public void batchUpdate(List<StockVO> stockVOList) {
        for(StockVO stockVO : stockVOList){
            StockVO stockVO1=new StockVO();
            stockVO.setSkuNo(stockVO.getSkuNo());
            Example<StockVO> example =Example.of(stockVO1);
            Optional<StockVO> one = goodsStockDao.findOne(example);
            if (one.isPresent()) {
                StockVO stockVO2 = one.get();
                stockVO.setId(stockVO2.getId());
                goodsStockDao.save(stockVO);
            }else{
                goodsStockDao.save(stockVO);
            }
        }
    }
}
