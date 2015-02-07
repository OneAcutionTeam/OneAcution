package com.siims.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.domain.Essay;
import com.siims.auction.domain.Goods;
import com.siims.auction.service.EssayService;
import com.siims.auction.service.GoodsService;
import com.siims.auction.utils.ImageUtil;
import com.siims.auction.utils.JsonSend;

@Controller
@RequestMapping("/file")
public class FileUpload {
	
	@Autowired
	GoodsService service;
	@Autowired
	EssayService eService;
	
	@RequestMapping("/upload")
    public void uploadFile(
    		@RequestParam(value="goods_name",required=true) String name,
			@RequestParam(value="user_id",required=true)String userId,
			@RequestParam(value="origion_price",required=true) float origionPrice,
			@RequestParam(value="goods_price",required=true)float price,
			@RequestParam(value="good_desc",required=true)String desc,
			@RequestParam(value="goods_contract",required=true)String contactId,
			@RequestParam(value="goods_id",required=false)String gId,
            HttpServletRequest request,HttpServletResponse response

    ) throws ServletException, IOException {
		
		
		Goods g = new Goods();
		
	
		String images="";
		String videoName = "";
		String videoImage = "";
		String saveVideoPath ="C:/Users/PCNCAD-dosh/git/OneAcution/auction1/src/main/webapp/video/";
		
		String savePath ="C:/Users/PCNCAD-dosh/git/OneAcution/auction1/src/main/webapp/images/";//request.getContextPath();
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		
		 if(multipartResolver.isMultipart(request)){
			 MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        System.out.println(myFileName); 
	                       
	                        //重命名上传后的文件名  
	                        
	                       // String fileName =  file.getOriginalFilename();  
	                        if(myFileName.endsWith(".mp4")){//视频文件
	                        	String videoPath = saveVideoPath+ myFileName;
	                        	File localFile = new File(videoPath);  
	  	                        file.transferTo(localFile);
	  	                        videoName = myFileName;
	  	                        continue;
	                        }
	                        if(myFileName.startsWith("AUC_")){
	                        	videoImage = myFileName;
	                        }else{
	                        	images +=myFileName+";";
	                        }
	                        //定义上传路径  
	                        String path = savePath+ myFileName;
	                        System.out.println("saved in path :"+path);
	                        try {
								ImageUtil.formateImage(path, file.getInputStream());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                       // File localFile = new File(path);  
	                      //  file.transferTo(localFile);  
	                    }  
	                }  
	            } 
		 }else{
			 System.out.println(request.getContentType());
		 }
       
		g.setGoodsId(gId);
		g.setgBriefDesc(desc);
		g.setgDetailDesc(desc);
		g.setgName(name);
		g.setgOrigionPrice(origionPrice);
		g.setgPrice(price);
		g.setgConTractId(contactId);
		g.setgImages(images);
		g.setgPublished(0);
		g.setgUserId(userId);
		g.setgVideo(videoName);
		g.setgVideoCover(videoImage);
       System.out.println("add name "+g.getgName()+"add id"+g.getGoodsId()+" voide "+g.getgVideo());
        JSONObject j = new JSONObject();
        if(service.addGoods(g)){
        	j.put("status", 1);
        }else{
        	j.put("status", 0);
        }
        JsonSend.send(response, j.toJSONString());
      
    }
	
	@RequestMapping("/upload_essay")
    public void uploadFileEssay(
    		@RequestParam(value="e_name",required=true) String name,
			@RequestParam(value="e_user_id",required=true)String userId,
			@RequestParam(value="e_content",required=true)String content,
			@RequestParam(value="e_goods_id",required=true)String goodsId,
			@RequestParam(value="e_id",required=false)String eId,
            HttpServletRequest request,HttpServletResponse response

    ) throws ServletException, IOException {
		Essay essay = new Essay();
	
		String images="";
		String videoName = "";
		String videoImage = "";
		String saveVideoPath ="C:/Users/PCNCAD-dosh/git/OneAcution/auction1/src/main/webapp/video/";
		
		String savePath ="C:/Users/PCNCAD-dosh/git/OneAcution/auction1/src/main/webapp/images/";//request.getContextPath();
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		
		 if(multipartResolver.isMultipart(request)){
			 MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        System.out.println(myFileName); 
	                       
	                        //重命名上传后的文件名  
	                        
	                       // String fileName =  file.getOriginalFilename();  
	                        if(myFileName.endsWith(".mp4")){//视频文件
	                        	String videoPath = saveVideoPath+ myFileName;
	                        	File localFile = new File(videoPath);  
	  	                        file.transferTo(localFile);
	  	                        videoName = myFileName;
	  	                        continue;
	                        }
	                        if(myFileName.startsWith("AUC_")){
	                        	videoImage = myFileName;
	                        }else{
	                        	images +=myFileName+";";
	                        }
	                        //定义上传路径  
	                        String path = savePath+ myFileName;
	                        System.out.println("saved in path :"+path);
	                        try {
								ImageUtil.formateImage(path, file.getInputStream());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                       // File localFile = new File(path);  
	                      //  file.transferTo(localFile);  
	                    }  
	                }  
	            } 
		 }else{
			 System.out.println(request.getContentType());
		 }
		 essay.seteId(eId);
		essay.seteContent(content);
		essay.setEgId(eId);
		essay.seteName(name);
		essay.setePicture(images);
		essay.setEuId(userId);
		essay.setEgId(goodsId);
		essay.seteVideo(videoName);
		essay.seteVideoPic(videoImage);
		

        JSONObject j = new JSONObject();
        if(eService.addEssay(essay)){
        	j.put("status", 1);
        }else{
        	j.put("status", 0);
        }
        JsonSend.send(response, j.toJSONString());
      
    }

}
