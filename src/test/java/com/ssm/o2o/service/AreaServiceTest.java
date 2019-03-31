package com.ssm.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.o2o.BasicTest;
import com.ssm.o2o.entity.Area;

public class AreaServiceTest extends BasicTest {
	@Autowired
	private AreaService areaService;

	@Autowired
	private CacheService cacheService;

	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		System.out.println(areaList.get(0).getAreaName());
		assertEquals("东苑", areaList.get(0).getAreaName());
		cacheService.removeFromCacheService("arealist");
		areaList = areaService.getAreaList();
	}
}
