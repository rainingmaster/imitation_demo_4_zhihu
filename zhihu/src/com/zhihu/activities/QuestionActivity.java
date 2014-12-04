package com.zhihu.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.packet.zhihu.R;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;
import com.zhihu.components.Title;

public class QuestionActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO 自动生成的方法存根
		
		/*设置本页标题名称*/
		mPageName="共80个回答";
		
		/*设置中央webview主体*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/index.html");
		
        /*设置标题栏按钮功能*/
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(mWebView);

		/*设置标题栏，需要放在最后*/
		mTitle = new Title(this);
		mTitle.initTitleBar(mPageName, false);
	}

	private void jumpToQuestion() {
		Intent intent = new Intent();	
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}


}
