package com.learn.demomarket.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.order.entity.MqMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:27:38
 */
public interface MqMessageService extends IService<MqMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

