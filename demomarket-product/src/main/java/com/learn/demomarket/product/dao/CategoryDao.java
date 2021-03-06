package com.learn.demomarket.product.dao;

import com.learn.demomarket.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
