package com.siims.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.domain.Goods;
import com.siims.auction.service.GoodsService;
import com.siims.auction.utils.JsonSend;

@Controller
@RequestMapping("/file")
public class FileUpload {
	
	@Autowired
	GoodsService service;
	
	@RequestMapping("/upload")
    public void uploadFile(
    		@RequestParam(value="goods_name",required=true) String name,
			@RequestParam(value="user_id",required=true)String userId,
			@RequestParam(value="origion_price",required=true) float origionPrice,
			@RequestParam(value="goods_price",required=true)float price,
			@RequestParam(value="good_desc",required=true)String desc,
			@RequestParam(value="goods_contract",required=true)String contactId,

            HttpServletRequest request,HttpServletResponse response

    ) throws ServletException, IOException {
		
		
		Goods g = new Goods();
		
		Calendar c=Calendar.getInstance();
		//以当前毫秒数作为id保证唯一性
		long uniqueId = c.getTimeInMillis();
		String images="";

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
	                        images +=myFileName+";";
	                        //重命名上传后的文件名  
	                        String fileName =  file.getOriginalFilename();  
	                        //定义上传路径  
	                        String path = savePath+ fileName;
	                        
	                        
	                        System.out.println("saved in path :"+path);
	                        File localFile = new File(path);  
	                        file.transferTo(localFile);  
	                    }  
	                }  
	            } 
		 }else{
			 System.out.println(request.getContentType());
		 }
       
		g.setGoodsId(""+uniqueId);
		g.setgBriefDesc(desc);
		g.setgDetailDesc(desc);
		g.setgName(name);
		g.setgOrigionPrice(origionPrice);
		g.setgPrice(price);
		g.setgConTractId(contactId);
		g.setgImages(images);
		g.setgPublished(0);
		g.setgUserId(userId);
       System.out.println(g.getgImages());
        JSONObject j = new JSONObject();
        if(service.addGoods(g)){
        	j.put("status", 1);
        }else{
        	j.put("status", 0);
        }
        JsonSend.send(response, j.toJSONString());
      
    }

}
