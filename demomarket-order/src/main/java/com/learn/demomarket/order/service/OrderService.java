package com.learn.demomarket.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:27:38
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

