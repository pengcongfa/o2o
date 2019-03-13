package com.ssm.o2o.util;

import org.apache.tomcat.jni.OS;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/o2oProject/image";
		} else {
			basePath = "/home/peng/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	public static String getShopImagePath(Long shopId) {
		String imagePath = "/uplaod/item/shop/" + shopId + "/";
		imagePath.replace("/", seperator);
		return imagePath;
	}
}
