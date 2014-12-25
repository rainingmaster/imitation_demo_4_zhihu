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


public class Common {
	static public String sentHttpClient(String url, Map<String, String> attr) {
        /*����HTTP Post����*/
        HttpPost httpRequest = new HttpPost(url); 
        /*
         * Post�������ͱ���������NameValuePair[]���鴢��
        */
        List <NameValuePair> params = new ArrayList <NameValuePair>();
        for (String key : attr.keySet()) {
        	params.add(new BasicNameValuePair(key, attr.get(key).toString().trim()));
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
            return strResult;
          } 
          else 
          { 
            return httpResponse.getStatusLine().toString();
          } 
        }
        catch (Exception e) 
        {
          e.printStackTrace();
          return e.getMessage().toString();
        }  
	}
}
