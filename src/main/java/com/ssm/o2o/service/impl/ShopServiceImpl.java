package com.ssm.o2o.service.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ssm.o2o.dao.ShopDao;
import com.ssm.o2o.dto.ShopExecution;
import com.ssm.o2o.entity.Shop;
import com.ssm.o2o.enums.ShopStateEnum;
import com.ssm.o2o.exceptions.ShopExecutionException;
import com.ssm.o2o.service.ShopService;
import com.ssm.o2o.util.ImageUtil;
import com.ssm.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	public ShopExecution addShop(Shop shop, File shopImg) {
		// TODO Auto-generated method stub
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
		}
		try {
			// 给店铺信息赋值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectiveNum = shopDao.insertShop(shop);
			
			
			if (effectiveNum <= 0) {
				throw new ShopExecutionException("店铺创建失败");
			} else {
				if (shopImg != null) {
					try {
						// 存储图片，赋予最新的图片文件路径
						addShopImg(shop, shopImg);
					} catch (Exception e) {
						// TODO: handle exception
						throw new ShopExecutionException("addShopImg error" + e.getMessage());
					}
					// 更新shop的图片地址
					effectiveNum = shopDao.updateShop(shop);
					if (effectiveNum <= 0) {
						throw new ShopExecutionException("图片地址更新失败");
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ShopExecutionException("addShop error" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, File shopImg) {
		// TODO Auto-generated method stub
		// 获取相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
		shop.setShopImg(shopImgAddr);
	}

}
