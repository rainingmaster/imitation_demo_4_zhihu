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
	
	private Integer mQuesId;//问题id
	private String mQuesTitle;//问题标题

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO 自动生成的方法存根
		
		/*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
        mQuesId = bundle.getInt("id");
        mQuesTitle = bundle.getString("title");
		
		/*设置本页标题名称*/
		mPageName="共80个回答";
		
		/*设置中央webview主体*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/question.html");
		mWebView.getBeginningData().put("id", mQuesId.toString());
		mWebView.getBeginningData().put("title", mQuesTitle);
		
		setContent(mWebView);

		/*设置标题栏*/
        mTitle.setTitleText(mPageName);//部属上才能调整
		mTitle.setTextClickable(false);
	}

	public void jumpToAnswer(JSONObject data) {
		Intent intent = new Intent();
		intent.setClass(this, AnswerActivity.class);
		startActivity(intent);
	}


}
