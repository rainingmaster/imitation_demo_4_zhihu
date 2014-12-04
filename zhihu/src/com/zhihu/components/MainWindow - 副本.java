package com.zhihu.components;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainWindow extends WebView {
	
	private Activity mActivity;
	
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
        
        addJavascriptInterface(myJavaScriptInterface,"AndroidFunction");
        loadUrl("file:///android_asset/index.html");//本地测试
	}


	private Handler mHandler = new Handler();
    
    
	/*定义各种web和android之间交互接口*/
    final class JavaScriptInterface {
    	Context mContext;
    	JavaScriptInterface(Context c) {
	        mContext = c;
	    }
        /** 
         * 该方法被浏览器端调用 
         */  
        public void clickOnAndroid(final String order) {
            mHandler.post(new Runnable() {  
                public void run() {
                	//TextView mText = (TextView) findViewById(R.id.resultText);
                	//mText.setText(order); 
                }  
            });  
        }  
    }

}
