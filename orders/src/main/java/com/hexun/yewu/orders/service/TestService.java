package com.hexun.yewu.orders.service;

import com.hexun.yewu.orders.entity.Test;

public interface TestService {
	int deleteByPrimaryKey(String id);

	void insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

	Test findOneByCode(String userCode);

	void redisInsert(Test test);
}
