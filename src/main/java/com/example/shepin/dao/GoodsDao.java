package com.example.shepin.dao;

import com.example.shepin.vo.goods.GoodsVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GoodsDao extends JpaRepository<GoodsVO,Long>, Serializable {


}
