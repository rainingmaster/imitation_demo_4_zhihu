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
		// TODO �Զ����ɵķ������
		
		/*���ñ�ҳ��������*/
		mPageName="��80���ش�";
		
		/*��������webview����*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/index.html");
		
        /*���ñ�������ť����*/
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(mWebView);

		/*���ñ���������Ҫ�������*/
		mTitle = new Title(this);
		mTitle.initTitleBar(mPageName, false);
	}

	private void jumpToQuestion() {
		Intent intent = new Intent();	
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}


}
