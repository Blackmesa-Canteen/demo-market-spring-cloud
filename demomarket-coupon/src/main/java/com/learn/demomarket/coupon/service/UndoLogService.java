package com.learn.demomarket.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.coupon.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 15:42:53
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

