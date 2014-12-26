package com.zhihu.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;


public class Common {
	
	public void sentHttpClient(String url, Map<String, String> attr, Handler webHandler) {//添加一个线程访问？防止连接慢卡顿
		ThreadSendHttp threadTest=new ThreadSendHttp(url, attr, webHandler);  
        new Thread(threadTest).start();
	}
	
	public class ThreadSendHttp implements Runnable {
		private String mUrl;
		private Map<String, String> mAttr;
		private Handler mWebHandler;
		
		public ThreadSendHttp(String url, Map<String, String> attr, Handler webHandler) {
			mUrl = url;
			mAttr = attr;
			mWebHandler = webHandler;
		}
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
            Message msgMessage=new Message();
			/*建立HTTP Post联机*/
	        HttpPost httpRequest = new HttpPost(mUrl);
	        /*
	         * Post运作传送变量必须用NameValuePair[]数组储存
	        */
	        List <NameValuePair> params = new ArrayList <NameValuePair>();
	        for (String key : mAttr.keySet()) {
	        	params.add(new BasicNameValuePair(key, mAttr.get(key).toString().trim()));
	    	}
	        try 
	        { 
	          /*发出HTTP request*/
	          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); 
	          /*取得HTTP response*/
	          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
	          /*若状态码为200 ok*/
	          int code = httpResponse.getStatusLine().getStatusCode();
	          if(code == 200)
	          { 
	            /*取出响应字符串*/
	            String strResult = EntityUtils.toString(httpResponse.getEntity()); 
	            msgMessage.obj = strResult;  
	            mWebHandler.sendMessage(msgMessage);
	          } 
	          else 
	          {
	            msgMessage.obj = httpResponse.getStatusLine().toString();  
	            mWebHandler.sendMessage(msgMessage);
	          } 
	        }
	        catch (Exception e) 
	        {
	          e.printStackTrace();
              msgMessage.obj = e.getMessage().toString(); 
              mWebHandler.sendMessage(msgMessage);
	        }
		}
	
	}
}
