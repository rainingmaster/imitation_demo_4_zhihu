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
		// TODO �Զ����ɵĹ��캯�����
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
     * �÷������ڰ�׿���ã��������ݸ㵽����� 
     */ 
    public void setToWebView(String content) {
    	loadUrl("javascript:getFromAndroid('"+content+"')");//�����������getFromAndroid����
    }
    
}
