package com.zhihu.activities;

import com.packet.zhihu.R;
import com.zhihu.components.BaseTitleBar;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("JavascriptInterface")
public class BaseActivity extends Activity {
    /**
     * 主体浏览器实体
     */
    protected MainWindow mWebView;
    
    /**
     * 左侧抽屉实体
     */
    protected LeftDrawer mLeftDrawer;
    
    /**
     * 标题实体
     */
    protected BaseTitleBar mTitle;
    
    /**
     * 左侧菜单状态，true为打开
     */
    protected Boolean mMenuState;

    /**
     * 服务器地址
     */
    protected String mServerIP;
	
	/**
    * 本app的名字
    */
    protected String mAppName;
	
	/**
    * 本页面的名字
    */
    protected String mPageName;

	/**
    * 页面标题布局
    */
    protected LinearLayout mTitleLayout;

	/**
    * 页面内容布局
    */
    protected LinearLayout mContentLayout;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mAppName=getResources().getString(R.string.app_name);//设置app名字
		
        mServerIP = getResources().getString(R.string.server_ip);//设置IP地址

        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_base_layout);
		mTitle = (BaseTitleBar)View.inflate(this, R.layout.component_titlebar_layout, null);
        setTitle(mTitle);
		//setContentView(R.layout.component_titlebar_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String getmPageName() {
		return mPageName;
	}

	public void setmPageName(String mPageName) {
		this.mPageName = mPageName;
	}
	
	/**
    * 对菜单与标题做一个捆绑，需要有开/关功能
    * @return 
    */
	protected void bingdingTitleandLeftMenu() {
		if (mLeftDrawer!=null) {
		closeSetLfMenu();
		
			mLeftDrawer.setDrawerFun(new DrawerListener(){
	
				@Override
				public void onDrawerClosed(View arg0) {
					closeSetLfMenu();
					// TODO 自动生成的方法存根
				}
	
				@Override
				public void onDrawerOpened(View arg0) {
					// TODO 自动生成的方法存根
					openSetLfMenu();
				}
	
				@Override
				public void onDrawerSlide(View arg0, float arg1) {
					// TODO 自动生成的方法存根
					
				}
	
				@Override
				public void onDrawerStateChanged(int arg0) {
					// TODO 自动生成的方法存根
					
				}
			});
		}
	}
	
	/**
    * 开启左侧抽屉菜单后的配置
    * @return 
    */
	private void openSetLfMenu() {
		if (mLeftDrawer!=null) {
			mTitle.setTitleText(mAppName);
			mTitle.setTitleTextFun(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			mLeftDrawer.closeDrawer();
	    		}
			});
			mMenuState=false;
		}
	}
	
	/**
    * 关闭左侧抽屉菜单后的配置
    * @return 
    */
	private void closeSetLfMenu() {
		if (mLeftDrawer!=null) {
			mTitle.setTitleText(mPageName);
			mTitle.setTitleTextFun(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			mLeftDrawer.openDrawer();
	    		}
			});
			mMenuState=true;
		}
	}

    /** 
     * 如果不做任何处理，浏览网页，点击系统“Back”键，整个Browser会调用finish()而结束自身 
     * 如果希望浏览的网 页回退而不是退出浏览器，需要在当前Activity中处理并消费掉该Back事件。
     *  
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (mWebView != null) {
	        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {  
	        	mWebView.goBack();  
	            return true;  
	        }
    	}
        return super.onKeyDown(keyCode, event); 
    }

    /** 
     * 设置activity的标题栏 
     * @param 标题栏对象
     */
	public boolean setTitle(View view) {
		
		mTitleLayout = (LinearLayout) findViewById(R.id.base_title);
		if (mTitleLayout.getChildCount() > 0) {
			mTitleLayout.removeAllViews();
		}
		mTitleLayout.addView(view);
		
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
				DrawerLayout.LayoutParams.MATCH_PARENT, 
				DrawerLayout.LayoutParams.MATCH_PARENT);
		
		view.setLayoutParams(layoutParams);//设置长高
		
		return true;
	}

    /** 
     * 设置activity的内容栏
     * @param 内容栏对象
     */
	public boolean setContent(View view) {
		
		mContentLayout = (LinearLayout) findViewById(R.id.base_content);
		if (mContentLayout.getChildCount() > 0) {
			mContentLayout.removeAllViews();
		}
		mContentLayout.addView(view);
		
		LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
				DrawerLayout.LayoutParams.MATCH_PARENT, 
				DrawerLayout.LayoutParams.MATCH_PARENT);
		
		view.setLayoutParams(layoutParams);//设置长高
		
		return true;
	}
		
}
