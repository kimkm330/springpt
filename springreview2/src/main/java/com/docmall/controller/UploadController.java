package com.docmall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.docmall.util.MediaUtils;
import com.docmall.util.UploadFileUtils;

@RequestMapping("/upload/*")
@Controller
public class UploadController {

  private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

  @Resource(name = "uploadPath")
  private String uploadPath;

  @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
  public void uploadForm() {
  }

  @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
  public String uploadForm(MultipartFile file, Model model) throws Exception {

    logger.info("originalName: " + file.getOriginalFilename());
    logger.info("size: " + file.getSize());
    logger.info("contentType: " + file.getContentType());

    String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());

    model.addAttribute("savedName", savedName);

    return "uploadResult";
  }

  @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
  public void uploadAjax() {
  }

  private String uploadFile(String originalName, byte[] fileData) throws Exception {

    UUID uid = UUID.randomUUID();

    String savedName = uid.toString() + "_" + originalName;

    File target = new File(uploadPath, savedName);

    FileCopyUtils.copy(fileData, target);

    return savedName;

  }
  
  /*
   @ResponseBody :자바 객체를 HTTP요청의 body 내용으로 매핑하는 기능. 
      즉 클라이언로 보내는 데이타에 추가한다는 의미로 해석. 해당 메서드에서 리턴되는 값이 View(JSP)가 아니고  자바객체를 사용시.
   ResponseEntity : body 에 객체데이타를 추가하기 위한 기능을 제공
   @RequestBody : HTTP요청의 body 내용을 자바 객체로 매핑하는 역할
   HttpStatus.CREATED : 리소스가 정상적으로 생성되었다는 상태코드의 의미. 
   */
  @ResponseBody  
  @RequestMapping(value ="/uploadAjax", method=RequestMethod.POST, 
                  produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
    
    logger.info("originalName: " + file.getOriginalFilename());
    
    // return "/2019/04/30/s_99116f04-56d6-48bc-99d0-27c7d29a348a_31414f5c-6662-4dfb-8c3d-6b8c76ca5d4b_캡처.JPG";
    return 
      new ResponseEntity<>(
          UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes()), 
          HttpStatus.CREATED);
  }
  
  //스프링 프로젝트 영역 외의 자원을 참조하기 위한 기능
  @ResponseBody
  @RequestMapping("/displayFile")
  public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{
    
    // 자바 입출력(IO) 스트림
	// input : 데이터 읽기(Read), output : 데이터 쓰기(Write)
	 
	// 파일을 읽기위한 스트림 객체  
	InputStream in = null; 
    ResponseEntity<byte[]> entity = null;
    
    logger.info("FILE NAME: " + fileName);
    
    try{
      // 파일의 확장자
      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
      
      //파일의 확장자가 이미지 파일인지 일반파일인지 체크하는 작업
      MediaType mType = MediaUtils.getMediaType(formatName);
      
      // 클라이언트로 보내고자 하는 파일의 정보를 헤더에 추가하는 작업의 객체
      HttpHeaders headers = new HttpHeaders();
      
      in = new FileInputStream(uploadPath+fileName);
      
      
      //if문이 true면 이미지 파일, false 면 일반파일을 의미한다.
      if(mType != null){
        headers.setContentType(mType); // 헤더에 이미지파일의 종류를 추가하는 작업
      }else{
        // 헤더에 일반파일의 정보를 추가하는 작업
        fileName = fileName.substring(fileName.indexOf("_")+1);       
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment; filename=\""+ 
          new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
      }
      	// 파일을 읽어서 데이터 body 에 추가하는 작업
        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
          headers, 
          HttpStatus.CREATED);
    }catch(Exception e){
      e.printStackTrace();
      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    }finally{
      in.close();
    }
      return entity;    
  }
    
  @ResponseBody
  @RequestMapping(value="/deleteFile", method=RequestMethod.POST)
  public ResponseEntity<String> deleteFile(String fileName){
    
    logger.info("delete file: "+ fileName);
    
    String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
    
    MediaType mType = MediaUtils.getMediaType(formatName);
    
    if(mType != null){      
      
      String front = fileName.substring(0,12);
      String end = fileName.substring(14);
      new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
    }
    
    new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
    
    
    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }  
  
  @ResponseBody
  @RequestMapping(value="/deleteAllFiles", method=RequestMethod.POST)
  public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files){
    
    logger.info("delete all files: "+ files);
    
    if(files == null || files.length == 0) {
      return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
    
    for (String fileName : files) {
      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
      
      MediaType mType = MediaUtils.getMediaType(formatName);
      
      if(mType != null){      
        
        String front = fileName.substring(0,12);
        String end = fileName.substring(14);
        new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
      }
      
      new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
      
    }
    return new ResponseEntity<String>("deleted", HttpStatus.OK);
  }  

}
//  @ResponseBody
//  @RequestMapping(value = "/uploadAjax", 
//                 method = RequestMethod.POST, 
//                 produces = "text/plain;charset=UTF-8")
//  public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
//
//    logger.info("originalName: " + file.getOriginalFilename());
//    logger.info("size: " + file.getSize());
//    logger.info("contentType: " + file.getContentType());
//
//    return 
//        new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
//  }

// @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
// public void uploadForm(MultipartFile file, Model model) throws Exception {
//
// logger.info("originalName: " + file.getOriginalFilename());
// logger.info("size: " + file.getSize());
// logger.info("contentType: " + file.getContentType());
//
// String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
//
// model.addAttribute("savedName", savedName);
//
// }
//
// private String uploadFile(String originalName, byte[] fileData)throws
// Exception{
//
// UUID uid = UUID.randomUUID();
//
// String savedName = uid.toString() + "_"+ originalName;
//
// File target = new File(uploadPath,savedName);
//
// FileCopyUtils.copy(fileData, target);
//
// return savedName;
//
// }