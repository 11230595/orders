package com.hexun.yewu.orders.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hexun.framework.core.redis.RedisClusterUtils;
import com.hexun.yewu.orders.entity.Test;
import com.hexun.yewu.orders.mapper.TestMapper;
import com.hexun.yewu.orders.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Resource
	private TestMapper testMapper;
	
	public int deleteByPrimaryKey(String id) {
		return testMapper.deleteByPrimaryKey(id);
	}
	
	public void insert(Test record) {
		RedisClusterUtils.set(record.getUserCode(), JSON.toJSONString(record));
		//testMapper.insert(record);
	}
	
	public int insertSelective(Test record) {
		return testMapper.insertSelective(record);
	}

	public Test selectByPrimaryKey(String id) {
		return testMapper.selectByPrimaryKey(id);
	}
	
	public int updateByPrimaryKeySelective(Test record) {
		return testMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Test record) {
		return testMapper.updateByPrimaryKey(record);
	}
	
	public Test findOneByCode(String userCode) {
		String result = RedisClusterUtils.get(userCode);
		/*System.out.println("查询数据库");
		testMapper.findOneByCode(userCode);*/
		return JSON.toJavaObject((JSON)JSON.parse(result), Test.class);
	}

	@Override
	public void redisInsert(Test test) {
		RedisClusterUtils.set(test.getUserCode(), JSON.toJSONString(test));
		testMapper.insert(test);
	}
}
