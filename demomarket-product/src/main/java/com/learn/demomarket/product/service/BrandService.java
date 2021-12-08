package com.learn.demomarket.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);
}

