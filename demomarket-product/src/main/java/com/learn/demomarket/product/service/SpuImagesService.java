package com.learn.demomarket.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 13:08:45
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

