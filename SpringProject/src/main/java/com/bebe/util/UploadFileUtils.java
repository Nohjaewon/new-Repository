package com.bebe.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.bebe.www.UploadController;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception{
		//랜덤하게 파일이름 생성
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString()+"_"+originalName;
		
		//새로운 경로, 폴더 생성, 그 경로를 담고있음
		String savedPath = calcPath(uploadPath);
		//새로운 경로의 파일만 생성 경로,이름
		File target = new File(uploadPath+savedPath, savedName);
		//받은 데이터를 복사(생성)
		FileCopyUtils.copy(fileData, target);
		
		//확장자명
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		//확장자명 체크 값이 있으면 썸네일 생성, 없으면 생성 안함
		if(MediaUtils.getMediaType(formatName)!=null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}
		else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;
	}
	//이미지파일이 아닌경우 확장자명이름
	private static String makeIcon(String uploadPath, String path, String fileName)throws Exception{
		String iconName = uploadPath+path+File.separator+fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	//폴더 생성을 위한 경로 생성
	private static String calcPath(String uploadPath) {
		GregorianCalendar cal = new GregorianCalendar();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath ,yearPath, monthPath, datePath);
		
		return datePath;
	}
	//폴더생성 함수
	private static void makeDir(String uploadPath, String...paths) {
		//풀 경로의 폴더가 존재하는지 처음확인 없으면 생성시작
		if(new File(uploadPath+paths[paths.length-1]).exists()) {
			return;
		}
		//단계별 경로내의 폴더 생성
		for(String path : paths) {
			File dirPath = new File(uploadPath+path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	private static String makeThumbnail(String uploadPath, String path, String fileName)throws Exception{
		//가상메모리의 이미지 생성
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailName = uploadPath+path+File.separator+"s_"+fileName;
		
		File newFile = new File(thumbnailName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
