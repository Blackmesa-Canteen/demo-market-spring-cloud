package com.learn.demomarket.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.product.entity.SpuInfoEntity;
import com.learn.demomarket.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SpuInfoEntity getSpuInfoBySkuId(Long skuId);

    void up(Long spuId);

    void savesupInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    PageUtils queryPageByCondtion(Map<String, Object> params);
}

