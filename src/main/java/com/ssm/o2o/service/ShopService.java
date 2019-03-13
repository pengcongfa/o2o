package com.ssm.o2o.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ssm.o2o.dto.ShopExecution;
import com.ssm.o2o.entity.Shop;


public interface ShopService {
	ShopExecution addShop(Shop shop, File shopImg);
}
