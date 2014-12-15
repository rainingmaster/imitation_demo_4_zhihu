package com.zhihu.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * @author admin
 *
 */
@SuppressLint("SetJavaScriptEnabled")
public class MainWindow extends WebView {
	
	protected Activity mActivity;
	protected String mGetWebHandle;
	protected String mBeginningData;
	
    @SuppressWarnings("deprecation")
	public MainWindow(Activity act) {
		super(act);
		// TODO �Զ����ɵĹ��캯�����
    	this.mActivity = act;
    	this.mGetWebHandle = null;
    	this.mBeginningData = null;

        requestFocus();
        setWebViewClient(new MyWebViewClient());
        setWebChromeClient(new MyWebChromeClient());//��WebView֧�ֵ�����
        WebSettings webSettings = getSettings();  
        webSettings.setSavePassword(false);  
        webSettings.setSaveFormData(false);  
        webSettings.setJavaScriptEnabled(true);  
        webSettings.setSupportZoom(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDefaultTextEncodingName("UTF-8");//�����ַ�����  
        
        final JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(mActivity);
        
        addJavascriptInterface(myJavaScriptInterface,"AndroidFun");
        
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//ȥ���ױ�
        
	}
    
	public String getmBeginningData() {
		return mBeginningData;
	}

	public void setmBeginningData(String mBeginningData) {
		this.mBeginningData = mBeginningData;
	}

	public String getmGetWebHandle() {
		return mGetWebHandle;
	}

	public void setmGetWebHandle(String mGetWebHandle) {
		this.mGetWebHandle = mGetWebHandle;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO �Զ����ɵķ������
		super.onScrollChanged(l, t, oldl, oldt);
		float webcontent = getContentHeight()*getScale();//webview�ĸ߶�                
		float webnow = getHeight()+ getScrollY();//��ǰwebview�ĸ߶� 
		if (( webcontent - webnow) == 0){
			//�Ѿ����ڵ׶�    
			//lay_bottom_layout.setVisibility(View.VISIBLE);��ʾ�ؼ�
			Toast.makeText(mActivity, "on the top", Toast.LENGTH_SHORT).show();
		              
		} else {
			//lay_bottom_layout.setVisibility(View.GONE);//���ؿؼ�    
		}/*
		//�Ѿ����ڶ���                
		if (getScaleY() == 0) {
		 }*/
	}

	private Handler mHandler = new Handler();
    
	/**
	 * �������web��android֮�佻���ӿڵ���
	 */
    final class JavaScriptInterface {
    	Context mContext;
    	JavaScriptInterface(Context c) {
	        mContext = c;
	    }
        /** 
         * �÷�����������˵��ã����������ȡ���� 
         */  
        public void getFromWebView(final String json) {
        	if (json!=null) {
            mHandler.post(new Runnable() {  
	                public void run() {
						Method exec;
	                	try {
	                		if (mGetWebHandle!=null) {
								JSONObject obj = new JSONObject(json);//ת��Ϊjson
								exec = mActivity.getClass().getMethod(mGetWebHandle, JSONObject.class);
			                	exec.invoke(mActivity, obj);
	                		}
						} catch (Exception e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
	                }
	            });
        	}
        }  
    }

    /** 
     * �÷����ڰ�׿���ã��������ݸ㵽����� 
     */ 
    public void sendToWebView(String content) {
    	if(content!=null)
    		loadUrl("javascript:getFromAndroid('"+content+"')");//�����������getFromAndroid����
    }
    
    // �ڲ���
    final public class MyWebViewClient extends WebViewClient {
	    // ���ҳ�������ӣ����ϣ��������Ӽ����ڵ�ǰbrowser����Ӧ��
	    // �������¿�Android��ϵͳbrowser����Ӧ�����ӣ����븲�� webview��WebViewClient����
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }

	    @Override
	    public void onPageStarted(WebView view, String url, Bitmap favicon) {
	        //showProgress();
	    }

	    @Override
	    public void onPageFinished(WebView view, String url) {
	        //closeProgress();
	    	if(mBeginningData!=null)
	    		sendToWebView(mBeginningData);
	    }

	    @Override
	    public void onReceivedError(WebView view, int errorCode,
	            String description, String failingUrl) {
	        //closeProgress();
	    }
    }
  
    /** 
     * �̳�WebChromeClient�� 
     * ��js������ʱ����д��� 
     *  
     */  
    final class MyWebChromeClient extends WebChromeClient {
  
//        /** 
//         * ����alert������ 
//         */  
//        @Override  
//        public boolean onJsAlert(WebView view,String url,
//                                 String message,JsResult result) {
//            Log.d(LOG_TAG,"onJsAlert:"+message);
//            mReusultText.setText("Alert:"+message);
//            //��alert�ļ򵥷�װ  
//            new AlertDialog.Builder(Main_Activity.this).  
//                setTitle("Alert").setMessage(message).setPositiveButton("OK",  
//                new DialogInterface.OnClickListener() {  
//                    @Override  
//                    public void onClick(DialogInterface arg0, int arg1) {  
//                       //TODO  
//                   }  
//            }).create().show();  
//            result.confirm();  
//            return true;  
//        }  
//  
//        /** 
//         * ����confirm������ 
//         */  
//        @Override  
//        public boolean onJsConfirm(WebView view, String url, String message,  
//                JsResult result) {  
//            Log.d(LOG_TAG, "onJsConfirm:"+message);  
//            mReusultText.setText("Confirm:"+message);  
//            result.confirm();  
//            return super.onJsConfirm(view, url, message, result);  
//        }  
//  
//        /** 
//         * ����prompt������ 
//         */  
//        @Override  
//        public boolean onJsPrompt(WebView view, String url, String message,  
//                String defaultValue, JsPromptResult result) {
//            Log.d(LOG_TAG,"onJsPrompt:"+message);  
//            mReusultText.setText("Prompt input is :"+message);  
//            result.confirm();  
//            return super.onJsPrompt(view, url, message, message, result);  
//        }
    } 
    
}
