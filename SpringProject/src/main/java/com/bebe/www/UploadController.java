package com.bebe.www;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.bebe.util.MediaUtils;
import com.bebe.util.UploadFileUtils;

@Controller
@RequestMapping("/upload/*")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	private String uploadPath = "C:\\spring\\file\\upload";
	
	@RequestMapping(value="/uploadAjax", method=RequestMethod.GET)
	public void uploadAjax() {
	}
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST,
			produces ="text/plain;charset=UTF-8")
	public ResponseEntity<String>uploadAjax(MultipartFile file) throws Exception{
		//return new ResponseEntity<String>(file.getOriginalFilename(), HttpStatus.OK);
		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}
	@RequestMapping(value="/uploadForm", method=RequestMethod.GET)
	public void uploadForm() {	
	}
	@RequestMapping(value="/uploadForm", method=RequestMethod.POST)
	public void uploadForm(MultipartFile file, Model model)throws Exception{
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		model.addAttribute("savedName", savedName);
	}
	
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		InputStream in = null; 	
		ResponseEntity<byte[]> entity = null;
		try {
			//확장자명
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			if(mType!=null) {
				headers.setContentType(mType);
			}
			else {
				//파일이름+확장자명
				formatName = fileName.substring(fileName.lastIndexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//한글 인코딩
				headers.add("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			//대상 파일에서 데이터 읽는 부분
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName){
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if(mType!=null) {
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(uploadPath+(front+end).replace('/',File.separatorChar)).delete();
		}
		new File(uploadPath+fileName.replace('/',File.separatorChar)).delete();
		return new ResponseEntity<String>("delete", HttpStatus.OK);
	}
	
	private String uploadFile(String name, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString()+"_"+name;
		File target  = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
