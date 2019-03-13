package com.ssm.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mysql.fabric.xmlrpc.base.Data;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String generateThumbnail(File thumbnail, String targetAddr) {
		String realFileName = getRandomFIleName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		System.out.println(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return relativeAddr;
	}

	// 随机文件名 时间年月日时分秒+随机的五位数
	public static String getRandomFIleName() {
		int rnumber = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rnumber;
	}

	private static String getFileExtension(File cFile) {
		// TODO Auto-generated method stub
		String oldname = cFile.getName();
		String extension = oldname.substring(oldname.lastIndexOf("."));
		return extension;
	}

	private static void makeDirPath(String targetAddr) {
		// TODO Auto-generated method stub
		String parentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(parentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	public static void main(String[] args) {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		try {
			Thumbnails.of(new File("C:\\Users\\pengcong\\Downloads\\2.PNG")).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.8f).toFile("C:\\Users\\pengcong\\Downloads\\2new.PNG");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
