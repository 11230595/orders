package com.hexun.yewu.orders.mapper;

import com.hexun.yewu.orders.entity.Test;

public interface TestMapper {
    int deleteByPrimaryKey(String id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

	Test findOneByCode(String userCode);
}