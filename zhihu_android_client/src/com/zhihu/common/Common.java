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
	
	public void sentHttpClient(String url, Map<String, String> attr, Handler webHandler) {//���һ���̷߳��ʣ���ֹ����������
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
			// TODO �Զ����ɵķ������
            Message msgMessage=new Message();
			/*����HTTP Post����*/
	        HttpPost httpRequest = new HttpPost(mUrl);
	        /*
	         * Post�������ͱ���������NameValuePair[]���鴢��
	        */
	        List <NameValuePair> params = new ArrayList <NameValuePair>();
	        for (String key : mAttr.keySet()) {
	        	params.add(new BasicNameValuePair(key, mAttr.get(key).toString().trim()));
	    	}
	        try 
	        { 
	          /*����HTTP request*/
	          httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); 
	          /*ȡ��HTTP response*/
	          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
	          /*��״̬��Ϊ200 ok*/
	          int code = httpResponse.getStatusLine().getStatusCode();
	          if(code == 200)
	          { 
	            /*ȡ����Ӧ�ַ���*/
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
