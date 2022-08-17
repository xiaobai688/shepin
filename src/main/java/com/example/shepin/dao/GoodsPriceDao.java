package com.example.shepin.dao;

import com.example.shepin.vo.goods.GoodsVO;
import com.example.shepin.vo.goods.PriceVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GoodsPriceDao extends JpaRepository<PriceVO,Long>, Serializable {

    PriceVO findBySkuNoIs(String skuNo);
}
