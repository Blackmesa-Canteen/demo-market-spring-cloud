package com.learn.demomarket.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.ware.entity.WareInfoEntity;
import com.learn.demomarket.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:34:47
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    FareVo getFare(Long addrId);
}

