package com.ssm.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.o2o.BasicTest;
import com.ssm.o2o.entity.Area;

public class AreaDaoTest extends BasicTest {
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> areaList=areaDao.queryArea();
		assertEquals(3, areaList.size());
	}
}
