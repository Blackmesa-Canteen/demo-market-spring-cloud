package com.learn.demomarket.order.dao;

import com.learn.demomarket.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:27:38
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
