package com.learn.demomarket.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.product.entity.SkuInfoEntity;
import com.learn.demomarket.product.vo.SkuItemVo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * sku信息
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException;
}

