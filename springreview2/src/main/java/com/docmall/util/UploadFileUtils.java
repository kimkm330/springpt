package com.docmall.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

  private static final Logger logger = 
      LoggerFactory.getLogger(UploadFileUtils.class);

//  public static String uploadFile(String uploadPath, 
//      String originalName, 
//      byte[] fileData)throws Exception{
//    
//    return null;
//  }
//  

  //파일을 업로드하는 작업메서드(업로드경로, 원본파일명, 파일 데이타)
  public static String uploadFile(String uploadPath, 
                              String originalName, 
                              byte[] fileData)throws Exception{
    
    UUID uid = UUID.randomUUID(); //16진수의 유일성 값을 생성
    
    String savedName = uid.toString() +"_"+originalName; //위의 16진수 값과 + "_" + 원본파일명
    
    String savedPath = calcPath(uploadPath); // "\2019\04\30"
    
    
    //  ("D:\\board_ex\\upload\2019\04\30", "파일명")
    File target = new File(uploadPath +savedPath,savedName);
    
    // 해당폴더에 업로드 완료됨.
    FileCopyUtils.copy(fileData, target);
    
    
    // abc.jpg 확장자 확인
    String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
    
    String uploadedFileName = null;
    
    // MediaType.IMAGE_JPEG  업로드한 파일이 이미지 인지 일반파일인지 체크하는 구문.
    if(MediaUtils.getMediaType(formatName) != null){
      uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
    }else{
      uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
    }
    
    return uploadedFileName;
    
  }
  
  private static  String makeIcon(String uploadPath, 
      String path, 
      String fileName)throws Exception{

    String iconName = 
        uploadPath + path + File.separator+ fileName;
    
    return iconName.substring(
        uploadPath.length()).replace(File.separatorChar, '/');
  }
  
  //썸네일 이미지 작업("D:\\board_ex\\upload", "\2019\04\30", "이미지 파일명")
  private static  String makeThumbnail(
              String uploadPath, 
              String path, 
              String fileName)throws Exception{
            
    //원본 파일을 작업환경에 해당하는 버퍼로 읽어오기.
	BufferedImage sourceImg = 
        ImageIO.read(new File(uploadPath + path, fileName));
    
	// 원본파일을 대상으로 사본파일을 만들기 위한 포맷작업
    BufferedImage destImg = Scalr.resize(sourceImg,Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_HEIGHT,100);
    
    // 썸네일 이미지 파일명 작업
    String thumbnailName = 
        uploadPath + path + File.separator +"s_"+ fileName;
    
    File newFile = new File(thumbnailName);
    //파일의 확장자
    String formatName = 
        fileName.substring(fileName.lastIndexOf(".")+1);
    
    //썸네일 이미지 작업완료
    ImageIO.write(destImg, formatName.toUpperCase(), newFile);
    
    // 썸네일 이미지 파일명(경로포함).
    return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
  } 
  
  // 업로드되는 날짜별 폴더생성
  private static String calcPath(String uploadPath){
    
    //날짜 기능제공하는 클래스
	Calendar cal = Calendar.getInstance();
    
    String yearPath = File.separator+cal.get(Calendar.YEAR); // "\" + "2019"
    
    
    // "\" +  "2019" + "\" + "04" -> "\2019\04"
    String monthPath = yearPath + 
        File.separator + 
        new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

    
    //  "\2019\04" + "\" + "30" -> "\2019\04\30"
    String datePath = monthPath + 
        File.separator + 
        new DecimalFormat("00").format(cal.get(Calendar.DATE));
    
    //날짜를 이용한 폴더 생성("D:\\board_ex\\upload", "\2019","\2019\04","\2019\04\30")
    makeDir(uploadPath, yearPath,monthPath,datePath);
    
    logger.info(datePath);
    
    return datePath;
  }
  
  // ("D:\\board_ex\\upload", "\2019","\2019\04","\2019\04\30")
  private static void makeDir(String uploadPath, String... paths){
    
    //File 클래스 : 파일,폴더와 관련된 기능을 제공. 해당 날짜폴더가 존재하는 지 체크
	if(new File(paths[paths.length-1]).exists()){  //paths[2] -> new File("\2019\04\30").exists()
      return;
    }
    //날짜별 폴더생성작업
    for (String path : paths) {
      
      
    	
    	//"D:\\board_ex\\upload\2019", "D:\\board_ex\\upload\2019\04", "D:\\board_ex\\upload\2019\04\30" 
    	File dirPath = new File(uploadPath + path);
      
      if(! dirPath.exists() ){
        dirPath.mkdir();
      } 
    }
  }
  
  
}
