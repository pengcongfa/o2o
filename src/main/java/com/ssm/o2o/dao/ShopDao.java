package com.ssm.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.o2o.entity.Shop;

public interface ShopDao {

	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);

	/**
	 * 返回queryShopList总数
	 * 
	 * @param shopCondition
	 * @return
	 */

	int queryShopCount(@Param("shopCondition") Shop shopCondition);

	/**
	 * 通过employee id 查询店铺
	 * 
	 * @param employeeId
	 * @return List<shop>
	 */

	public Shop findShopById(Long shopId);

	public List<Shop> findShopList();

	public int insertShop(Shop shop);

	// 批量插入数据foreach
	public int insertShopsForeach(List<Shop> shoplist); // int是批量插入成功的数据条数

	public int updateShop(Shop shop);

	public int deleteShop(Long shopId);

	/**
	 * 分页查询店铺,可输入的条件有：店铺名（模糊），店铺状态，店铺Id,店铺类别,区域ID
	 * 
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */

}
