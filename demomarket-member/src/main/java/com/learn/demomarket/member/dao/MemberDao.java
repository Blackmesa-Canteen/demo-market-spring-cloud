package com.learn.demomarket.member.dao;

import com.learn.demomarket.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author 996worker
 * @email 
 * @date 2021-11-29 16:15:53
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
