package com.learn.demomarket.order.dao;

import com.learn.demomarket.order.entity.MqMessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:27:38
 */
@Mapper
public interface MqMessageDao extends BaseMapper<MqMessageEntity> {
	
}
