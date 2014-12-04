package com.zhihu.components;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


@SuppressLint("SetJavaScriptEnabled")
public class MainWindow extends WebView {
	
	private Activity mActivity;
	
    @SuppressWarnings("deprecation")
	public MainWindow(Activity act) {
		super(act);
		// TODO 自动生成的构造函数存根
    	this.mActivity = act;
        setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {       
                view.loadUrl(url);
                return true;       
            }       
        });
        WebSettings webSettings = getSettings();  
        webSettings.setSavePassword(false);  
        webSettings.setSaveFormData(false);  
        webSettings.setJavaScriptEnabled(true);  
        webSettings.setSupportZoom(false);
        
        final JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(mActivity);
        
        addJavascriptInterface(myJavaScriptInterface,"AndroidFun");
        
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	}

	private Handler mHandler = new Handler();
    
	/**
	 * 定义各种web和android之间交互接口的类
	 */
    final class JavaScriptInterface {
    	Context mContext;
    	JavaScriptInterface(Context c) {
	        mContext = c;
	    }
        /** 
         * 该方法被浏览器端调用，从浏览器获取内容 
         */  
        public void getFromWebView(final String order) {
            mHandler.post(new Runnable() {  
                public void run() {
                	//TextView mText = (TextView) findViewById(R.id.resultText);
                	//mText.setText(order); 
                }  
            });  
        }  
    }

    /** 
     * 该方法被在安卓调用，发送内容搞到浏览器 
     */ 
    public void setToWebView(String content) {
    	loadUrl("javascript:getFromAndroid('"+content+"')");//让浏览器调用getFromAndroid函数
    }
    
}
