package com.zhihu.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.packet.zhihu.R;
import com.zhihu.components.LeftDrawer;
import com.zhihu.components.MainWindow;
import com.zhihu.components.Title;
import com.zhihu.activities.QuestionActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

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
		mWebView.setmGetWebHandle("onGetWebView");
		
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
	
	public void onGetWebView(JSONObject obj){
		try {
			switch(obj.getInt("action")) {
				case 0: {//��������
					jumpToQuestion(obj.getInt("id"), obj.getString("title"));
					break;
				}
				case 1: {//��������
					jumpToQuestion(obj.getInt("id"), obj.getString("title"));
					break;
				}
				case 2: {//��������
					jumpToQuestion(obj.getInt("id"), obj.getString("title"));
					break;
				}
				default:
					Toast.makeText(this, "��������", Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	public void webHandle(JSONObject obj) {
	}

	private void jumpToQuestion(int id, String title) {
		/* ͨ��Bundle����洢��Ҫ���ݵ����� */
		Bundle bundle = new Bundle();
		/*�ַ����ַ������������ֽ����顢�������ȵȣ������Դ�*/
		bundle.putInt("id", id);
		bundle.putString("title", title);
		  
		Intent intent = new Intent();
		/*��bundle����assign��Intent*/
		intent.putExtras(bundle);
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}

}
