package com.learn.demomarket.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:34:47
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

