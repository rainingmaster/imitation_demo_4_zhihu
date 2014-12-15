package com.zhihu.activities;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.packet.zhihu.R;
import com.zhihu.components.BaseTitleBar;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;


/**
 * @author rainmaster
 * {@value}需要传入题目页面id和完整title
 *
 */
public class QuestionActivity extends BaseActivity {
	
	private int mQuestionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO 自动生成的方法存根
		
		/*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
        mQuestionId = bundle.getInt("id");
        String title = bundle.getString("title");
        String bg_data = "{\"id\":\""+mQuestionId+"\", \"title\":\""+title+"\"}";
		
		/*设置本页标题名称*/
		mPageName="共80个回答";
		
		/*设置中央webview主体*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/question.html");
		mWebView.setmBeginningData(bg_data);
		
		setContent(mWebView);

		/*设置标题栏*/
        mTitle.setTitleText(mPageName);//部属上才能调整
		mTitle.setTextClickable(false);
	}

	private void jumpToAnswer() {
		Intent intent = new Intent();	
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}


}
