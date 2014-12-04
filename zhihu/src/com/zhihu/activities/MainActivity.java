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
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("JavascriptInterface")
public class MainActivity extends Activity {
    /**
     * ���������ʵ��
     */
    protected MainWindow mWebView;
    
    /**
     * ������ʵ��
     */
    protected LeftDrawer mleftDrawer;
    
    /**
     * ����ʵ��
     */
    protected Title mTitle;
    
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
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		/*������߳���˵�*/
		mleftDrawer = new LeftDrawer(this);
		/*��������webview����*/
		mWebView = new MainWindow(this);
		/*������ҳΪ����Ԫ��*/
		//mleftDrawer.setFirstEle(mWebView);
		
		mleftDrawer.setUserMess("rainmaser", R.drawable.tt);//���ӣ��������û����
		
        /*���ñ�������ť����*/
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(mWebView);

		/*���ñ���������Ҫ�������*/
		mTitle = new Title(this);
		mTitle.initTitleBar();

		mAppName=getResources().getString(R.string.app_name);
		mPageName=getResources().getString(R.string.index);
		
		mTitle.setButtonText(mPageName);
		
		//toggleLeftDrawerMenu();
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
	private void toggleLeftDrawerMenu() {
		closeSetLfMenu();
		
		mleftDrawer.setDrawerFun(new DrawerListener(){

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
	
	/**
    * ����������˵��������
    * @return 
    */
	private void openSetLfMenu() {
		mTitle.setButtonText(mAppName);
		mTitle.setButtonFun(new OnClickListener() {
    		@Override
    		public void onClick(View arg0) {
    			mleftDrawer.closeDrawer();
    		}
		});
		mMenuState=false;
	}
	

	/**
    * �ر�������˵��������
    * @return 
    */
	private void closeSetLfMenu() {
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
