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
	
	private Integer mQuesId;//����id
	private String mQuesTitle;//�������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO �Զ����ɵķ������
		
		/*��ȡIntent�е�Bundle����*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*��ȡBundle�е����ݣ�ע�����ͺ�key*/
        mQuesId = bundle.getInt("id");
        mQuesTitle = bundle.getString("title");
		
		/*���ñ�ҳ��������*/
		mPageName="��80���ش�";
		
		/*��������webview����*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/question.html");
		mWebView.getBeginningData().put("id", mQuesId.toString());
		mWebView.getBeginningData().put("title", mQuesTitle);
		
		setContent(mWebView);

		/*���ñ�����*/
        mTitle.setTitleText(mPageName);//�����ϲ��ܵ���
		mTitle.setTextClickable(false);
	}

	public void jumpToAnswer() {
		Bundle bundle = new Bundle();
		
		bundle.putInt("id", mQuesId);
		
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(this, AnswerActivity.class);
		startActivity(intent);
	}


}
