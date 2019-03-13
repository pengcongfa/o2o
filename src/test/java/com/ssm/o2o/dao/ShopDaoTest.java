package com.ssm.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.o2o.BasicTest;
import com.ssm.o2o.entity.Area;
import com.ssm.o2o.entity.PersonInfo;
import com.ssm.o2o.entity.Shop;
import com.ssm.o2o.entity.ShopCategory;

public class ShopDaoTest extends BasicTest {
	@Autowired
	ShopDao shopDao;

	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(1L);
		shop.setArea(area);
		shop.setOwner(owner);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺insert");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		shop.setPhone("15810859758");
		;
		int effectedNum = shopDao.updateShop(shop);
		System.out.println(effectedNum);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testDeleteShop() {
		long shopId = 4L;
		int effectedNum = shopDao.deleteShop(shopId);
		System.out.println(effectedNum);
		assertEquals(1, effectedNum);
	}

	@Test
	@Ignore
	public void testFindShopById() {
		long shopId = 2L;
		Shop shop = shopDao.findShopById(shopId);
		System.out.println(shop.toString());
		// assertEquals(1, shop);
	}

	@Test
	public void testFindShopList() {
		List<Shop> shops = shopDao.findShopList();
		System.out.println(shops.toString());
		// assertEquals(1, shop);
	}

}
