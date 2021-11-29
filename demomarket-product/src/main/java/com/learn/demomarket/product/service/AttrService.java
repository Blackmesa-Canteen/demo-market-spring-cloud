package com.learn.demomarket.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

