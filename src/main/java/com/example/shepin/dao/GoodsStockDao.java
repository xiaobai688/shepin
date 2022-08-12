package com.example.shepin.dao;

import com.example.shepin.vo.goods.StockVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GoodsStockDao extends JpaRepository<StockVO,Long>, Serializable {

}
