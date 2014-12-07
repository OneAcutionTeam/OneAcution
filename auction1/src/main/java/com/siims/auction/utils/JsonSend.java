package com.siims.auction.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

public class JsonSend {
	public static void send(HttpServletResponse response,String text) {  
        response.setContentType("application/json;charset=UTF-8");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        try {  
            if(text==null){  
                text="";  
            }  
            response.getWriter().write(text);  
            response.getWriter().flush();  
            response.getWriter().close();  
        } catch (IOException e) {  
              
            if(!"class org.apache.catalina.connector.ClientAbortException".equals(e.getClass().toString()))  
                e.printStackTrace();  
        }  
    }  

}
