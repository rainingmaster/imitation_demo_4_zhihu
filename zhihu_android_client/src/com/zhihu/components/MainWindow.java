package com.zhihu.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.packet.zhihu.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnLongClickListener;
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
	protected Map<String,String> mBeginningData;
	
    @SuppressWarnings("deprecation")
	public MainWindow(Activity act) {
		super(act);
		// TODO �Զ����ɵĹ��캯�����
    	this.mActivity = act;
    	this.mBeginningData = new HashMap<String, String>();
    	mBeginningData.put("ip", getResources().getString(R.string.server_ip));

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
        setOnLongClickListener(new OnLongClickListener() {//���γ�������
			
			@Override
			public boolean onLongClick(View v) {
				// TODO �Զ����ɵķ������
				return true;
			}
		});
        
	}
    
	public Map<String,String> getBeginningData() {
		return mBeginningData;
	}

	public void setBeginningData(Map<String,String> mBeginningData) {
		this.mBeginningData = mBeginningData;
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
        public void getFromWebView(final String funName, final String data) {
        	if (funName != null) {
            mHandler.post(new Runnable() {  
	                public void run() {
						Method exec;
	                	try {
	                		if(data != null && data.length() != 0) {
		                		JSONObject obj = new JSONObject(data);//ת��Ϊjson
								exec = mActivity.getClass().getMethod(funName, JSONObject.class);
								if (exec != null) {
									exec.invoke(mActivity, obj);
		                		}
	                		}
	                		else {
								exec = mActivity.getClass().getMethod(funName);
								if (exec != null) {
									exec.invoke(mActivity);
		                		}
	                		}
						} catch (Exception e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
	                }
	            });
        	}
        }
        
        /**
         * ��ҳ��ɳ�ʼ��ʱ����
         */
    }

    /** 
     * �÷����ڰ�׿���ã��������ݸ㵽����� 
     */ 
    public void sendToWebView(String funName, String data) {
    	if(funName != null) {
    		loadUrl("javascript:" + funName + "(" + data + ")");//�����������getFromAndroid����    	
    	}
    }
    
    /** 
     * �ڲ���,�̳�WebViewClient�� 
     * ��js������ʱ����д��� 
     *  
     */
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
	    	String beginStr = "";
	    	for (String key : mBeginningData.keySet()) {
	    		beginStr += "\""+ key + "\":\"" + mBeginningData.get(key) + "\",";
	    	}
	    	sendToWebView("initPage", "'{" + beginStr.substring(0, beginStr.length() - 1) + "}'"); 	
	    }

	    @Override
	    public void onReceivedError(WebView view, int errorCode,
	            String description, String failingUrl) {
	        //closeProgress();
	    }
    }
  
    /** 
     * �ڲ���,�̳�WebChromeClient�� 
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
