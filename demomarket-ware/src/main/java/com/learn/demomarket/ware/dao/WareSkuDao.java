package com.learn.demomarket.ware.dao;

import com.learn.demomarket.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:34:47
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
