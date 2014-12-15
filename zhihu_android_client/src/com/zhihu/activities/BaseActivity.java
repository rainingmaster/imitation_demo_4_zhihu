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
     * ���������ʵ��
     */
    protected MainWindow mWebView;
    
    /**
     * ������ʵ��
     */
    protected LeftDrawer mLeftDrawer;
    
    /**
     * ����ʵ��
     */
    protected BaseTitleBar mTitle;
    
    /**
     * ���˵�״̬��trueΪ��
     */
    protected Boolean mMenuState;
	
	/**
    * ��app������
    */
    protected String mAppName;
	
	/**
    * ��ҳ�������
    */
    protected String mPageName;

	/**
    * ҳ����Ⲽ��
    */
    protected LinearLayout mTitleLayout;

	/**
    * ҳ�����ݲ���
    */
    protected LinearLayout mContentLayout;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mAppName=getResources().getString(R.string.app_name);

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
    * �Բ˵��������һ��������Ҫ�п�/�ع���
    * @return 
    */
	protected void bingdingTitleandLeftMenu() {
		if (mLeftDrawer!=null) {
		closeSetLfMenu();
		
			mLeftDrawer.setDrawerFun(new DrawerListener(){
	
				@Override
				public void onDrawerClosed(View arg0) {
					closeSetLfMenu();
					// TODO �Զ����ɵķ������
				}
	
				@Override
				public void onDrawerOpened(View arg0) {
					// TODO �Զ����ɵķ������
					openSetLfMenu();
				}
	
				@Override
				public void onDrawerSlide(View arg0, float arg1) {
					// TODO �Զ����ɵķ������
					
				}
	
				@Override
				public void onDrawerStateChanged(int arg0) {
					// TODO �Զ����ɵķ������
					
				}
			});
		}
	}
	
	/**
    * ����������˵��������
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
    * �ر�������˵��������
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
     * ��������κδ��������ҳ�����ϵͳ��Back����������Browser�����finish()���������� 
     * ���ϣ��������� ҳ���˶������˳����������Ҫ�ڵ�ǰActivity�д������ѵ���Back�¼���
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
     * ����activity�ı����� 
     * @param ����������
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
		
		view.setLayoutParams(layoutParams);//���ó���
		
		return true;
	}

    /** 
     * ����activity��������
     * @param ����������
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
		
		view.setLayoutParams(layoutParams);//���ó���
		
		return true;
	}
		
}
