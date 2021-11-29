package com.learn.demomarket.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.common.utils.PageUtils;
import com.learn.demomarket.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:15:53
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

