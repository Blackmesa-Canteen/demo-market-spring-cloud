package com.learn.demomarket.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.ware.entity.PurchaseEntity;
import com.learn.demomarket.ware.vo.MergeVo;
import com.learn.demomarket.ware.vo.PurchaseDoneVo;

import java.util.List;
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

    void done(PurchaseDoneVo doneVo);

    void received(List<Long> ids);

    void mergePurchase(MergeVo mergeVo);

    PageUtils queryPageUnreceive(Map<String, Object> params);
}

