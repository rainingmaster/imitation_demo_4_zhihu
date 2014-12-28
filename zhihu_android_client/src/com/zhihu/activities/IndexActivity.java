package com.zhihu.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.packet.zhihu.R;
import com.zhihu.components.BaseTitleBar;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;
import com.zhihu.activities.QuestionActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class IndexActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO �Զ����ɵķ������
		
		/*���ñ�ҳ��������*/
		mPageName=getResources().getString(R.string.index);
		
		/*��������webview����*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/index.html");
		
		
		/*������߳���˵�*/
		mLeftDrawer = (LeftDrawer) View.inflate(this, R.layout.component_leftdrawer_layout, null);
		mLeftDrawer.setUserMess("rainmaser", R.drawable.tt);
		/*������ҳΪ����Ԫ��*/
		mLeftDrawer.setMainPart(mWebView);

		setContent(mLeftDrawer);

		/*���ñ�����*/
		mTitle.setTitleText(mPageName);//�����ϲ��ܵ���
		mTitle.setTextClickable(true);
        
		/*�������Ͳ˵�֮�书��*/
		bingdingTitleandLeftMenu();
	}

	public void jumpToQuestion(JSONObject data) {
		/* ͨ��Bundle����洢��Ҫ���ݵ����� */
		Bundle bundle = new Bundle();
		
		try {
		/*�ַ����ַ������������ֽ����顢�������ȵȣ������Դ�*/
		bundle.putInt("id", data.getInt("id"));
		bundle.putString("title", data.getString("title"));
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		  
		Intent intent = new Intent();
		/*��bundle����assign��Intent*/
		intent.putExtras(bundle);
		intent.setClass(this, QuestionActivity.class);
		jumpActivity(intent);
	}

}
