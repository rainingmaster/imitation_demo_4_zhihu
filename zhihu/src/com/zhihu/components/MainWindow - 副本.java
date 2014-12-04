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
        
        addJavascriptInterface(myJavaScriptInterface,"AndroidFunction");
        loadUrl("file:///android_asset/index.html");//���ز���
	}


	private Handler mHandler = new Handler();
    
    
	/*�������web��android֮�佻���ӿ�*/
    final class JavaScriptInterface {
    	Context mContext;
    	JavaScriptInterface(Context c) {
	        mContext = c;
	    }
        /** 
         * �÷�����������˵��� 
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
