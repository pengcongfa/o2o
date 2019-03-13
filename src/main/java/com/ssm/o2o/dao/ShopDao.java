package com.ssm.o2o.dao;

import java.util.List;

import com.ssm.o2o.entity.Shop;

public interface ShopDao {
	public Shop findShopById(Long shopId);
	public List<Shop> findShopList();
	public int insertShop(Shop shop);
	public int updateShop(Shop shop);
	public int deleteShop(Long shopId);
}

