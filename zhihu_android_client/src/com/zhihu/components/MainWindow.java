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
		// TODO 自动生成的构造函数存根
    	this.mActivity = act;
    	this.mBeginningData = new HashMap<String, String>();
    	mBeginningData.put("ip", getResources().getString(R.string.server_ip));

        requestFocus();
        setWebViewClient(new MyWebViewClient());
        setWebChromeClient(new MyWebChromeClient());//让WebView支持弹出框
        WebSettings webSettings = getSettings();  
        webSettings.setSavePassword(false);  
        webSettings.setSaveFormData(false);  
        webSettings.setJavaScriptEnabled(true);  
        webSettings.setSupportZoom(false);
        webSettings.setAllowFileAccess(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDefaultTextEncodingName("UTF-8");//设置字符编码  
        
        final JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(mActivity);
        
        addJavascriptInterface(myJavaScriptInterface,"AndroidFun");
        
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//去掉白边
        setOnLongClickListener(new OnLongClickListener() {//屏蔽长按功能
			
			@Override
			public boolean onLongClick(View v) {
				// TODO 自动生成的方法存根
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
        public void getFromWebView(final String funName, final String data) {
        	if (funName != null) {
            mHandler.post(new Runnable() {  
	                public void run() {
						Method exec;
	                	try {
	                		JSONObject obj = new JSONObject(data);//转换为json
							exec = mActivity.getClass().getMethod(funName, JSONObject.class);
							if (exec != null) {
								exec.invoke(mActivity, obj);
	                		}
						} catch (Exception e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
	                }
	            });
        	}
        }
        
        /**
         * 网页完成初始化时调用
         */
    }

    /** 
     * 该方法在安卓调用，发送内容搞到浏览器 
     */ 
    public void sendToWebView(String funName, String data) {
    	if(funName != null && data != null)
    		loadUrl("javascript:" + funName + "(" + data + ")");//让浏览器调用getFromAndroid函数
    }
    
    /** 
     * 内部类,继承WebViewClient类 
     * 对js弹出框时间进行处理 
     *  
     */
    final public class MyWebViewClient extends WebViewClient {
	    // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
	    // 而不是新开Android的系统browser中响应该链接，必须覆盖 webview的WebViewClient对象。
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
	    		beginStr += "\""+ key + "\"=\"" + mBeginningData.get(key) + "\",";
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
     * 内部类,继承WebChromeClient类 
     * 对js弹出框时间进行处理 
     *  
     */  
    final class MyWebChromeClient extends WebChromeClient {
  
//        /** 
//         * 处理alert弹出框 
//         */  
//        @Override  
//        public boolean onJsAlert(WebView view,String url,
//                                 String message,JsResult result) {
//            Log.d(LOG_TAG,"onJsAlert:"+message);
//            mReusultText.setText("Alert:"+message);
//            //对alert的简单封装  
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
//         * 处理confirm弹出框 
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
//         * 处理prompt弹出框 
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
