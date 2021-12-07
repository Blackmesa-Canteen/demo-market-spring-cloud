package com.learn.demomarket.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);
}

