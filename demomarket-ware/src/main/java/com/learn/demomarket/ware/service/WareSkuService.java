package com.learn.demomarket.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:34:47
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

