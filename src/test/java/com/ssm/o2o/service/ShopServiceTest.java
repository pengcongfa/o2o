package com.ssm.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.o2o.BasicTest;
import com.ssm.o2o.dto.ShopExecution;
import com.ssm.o2o.entity.Area;
import com.ssm.o2o.entity.PersonInfo;
import com.ssm.o2o.entity.Shop;
import com.ssm.o2o.entity.ShopCategory;
import com.ssm.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BasicTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void testShopServiceAddShop() {
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
		shop.setShopName("有图片的店铺测试");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("C:\\\\Users\\\\pengcong\\\\Downloads\\\\2.PNG");
		ShopExecution shopExecution = shopService.addShop(shop, shopImg);
		System.out.println("上传结果"+shopExecution .getState());
		assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
	}

}
