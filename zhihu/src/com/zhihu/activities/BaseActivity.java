package com.zhihu.activities;

import com.packet.zhihu.R;
import com.zhihu.components.Title;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("JavascriptInterface")
public class BaseActivity extends Activity {
    /**
     * 主体浏览器实体
     */
    protected WebView mWebView;
    
    /**
     * 左侧抽屉实体
     */
    protected LeftDrawer mleftDrawer;
    
    /**
     * 标题实体
     */
    protected Title mTitle;
    
    /**
     * 左侧菜单状态，true为打开
     */
    protected Boolean mMenuState;
	
	/**
    * 本app的名字
    */
    protected String mAppName;
	
	/**
    * 本页面的名字
    */
    protected String mPageName;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mleftDrawer=null;
		mAppName=getResources().getString(R.string.app_name);
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
		if (mleftDrawer!=null) {
		closeSetLfMenu();
		
			mleftDrawer.setDrawerFun(new DrawerListener(){
	
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
		if (mleftDrawer!=null) {
			mTitle.setButtonText(mAppName);
			mTitle.setButtonFun(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			mleftDrawer.closeDrawer();
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
		if (mleftDrawer!=null) {
			mTitle.setButtonText(mPageName);
			mTitle.setButtonFun(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			mleftDrawer.openDrawer();
	    		}
			});
			mMenuState=true;
		}
	}
		
}
