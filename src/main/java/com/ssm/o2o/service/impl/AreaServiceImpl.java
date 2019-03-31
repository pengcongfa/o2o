package com.ssm.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.o2o.cache.JedisUtil;
import com.ssm.o2o.dao.AreaDao;
import com.ssm.o2o.entity.Area;
import com.ssm.o2o.service.AreaService;

// 1.区域 2头条 3店铺类别
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Autowired
	private JedisUtil.Strings jedisStrings;

	private static String AREALISTKEY = "arealist";

	@Override
	public List<Area> getAreaList() {
		// TODO Auto-generated method stub

		String key = AREALISTKEY;
		List<Area> areaList = null;
		// 定义jackson数据转换操作类：area对象数组和字符串json对象的转化
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {// 1 redis查询不到
			areaList = areaDao.queryArea(); // 2向数据库查询，返回获取的结果
			String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(areaList); // 3.1将查询结果转化为String,
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jedisStrings.set(key, jsonString); // 3.2存入redis中
		} else { // 1 redis存在数据
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			try {
				areaList = mapper.readValue(jsonString, javaType); // 2 将查询结果转化为List
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return areaList;
	}

}
