package com.zhihu.activities;

import com.packet.zhihu.R;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;
import com.zhihu.components.Title;
import com.zhihu.activities.QuestionActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class IndexActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO �Զ����ɵķ������
		
		/*���ñ�ҳ��������*/
		mPageName=getResources().getString(R.string.index);	
		
		/*������߳���˵�*/
		mleftDrawer = new LeftDrawer(this, "rainmaser", R.drawable.tt);
		
		/*��������webview����*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/index.html");
		
		/*������ҳΪ����Ԫ��*/
		mleftDrawer.setMainPart(mWebView);
		
        /*���ñ�������ť����*/
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(mleftDrawer.getDrawerlayout());

		/*���ñ���������Ҫ�������*/
		mTitle = new Title(this);
		mTitle.initTitleBar(mPageName, true);
		
		/*�������Ͳ˵�֮�书��*/
		bingdingTitleandLeftMenu();
	}

	private void jumpToQuestion() {
		Intent intent = new Intent();	
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}

}
