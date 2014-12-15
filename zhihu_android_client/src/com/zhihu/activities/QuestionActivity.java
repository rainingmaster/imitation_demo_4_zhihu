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
 * {@value}��Ҫ������Ŀҳ��id������title
 *
 */
public class QuestionActivity extends BaseActivity {
	
	private int mQuestionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO �Զ����ɵķ������
		
		/*��ȡIntent�е�Bundle����*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*��ȡBundle�е����ݣ�ע�����ͺ�key*/
        mQuestionId = bundle.getInt("id");
        String title = bundle.getString("title");
        String bg_data = "{\"id\":\""+mQuestionId+"\", \"title\":\""+title+"\"}";
		
		/*���ñ�ҳ��������*/
		mPageName="��80���ش�";
		
		/*��������webview����*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/question.html");
		mWebView.setmBeginningData(bg_data);
		
		setContent(mWebView);

		/*���ñ�����*/
        mTitle.setTitleText(mPageName);//�����ϲ��ܵ���
		mTitle.setTextClickable(false);
	}

	private void jumpToAnswer() {
		Intent intent = new Intent();	
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}


}
