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
		// TODO 自动生成的方法存根
		
		/*设置本页标题名称*/
		mPageName=getResources().getString(R.string.index);
		
		/*设置中央webview主体*/
		mWebView = new MainWindow(this);
		mWebView.loadUrl("file:///android_asset/index.html");
		mWebView.setmGetWebHandle("onGetWebView");
		
		
		/*设置左边抽屉菜单*/
		mLeftDrawer = (LeftDrawer) View.inflate(this, R.layout.component_leftdrawer_layout, null);
		mLeftDrawer.setUserMess("rainmaser", R.drawable.tt);
		/*设置网页为背景元素*/
		mLeftDrawer.setMainPart(mWebView);

		setContent(mLeftDrawer);

		/*设置标题栏*/
		mTitle.setTitleText(mPageName);//部属上才能调整
		mTitle.setTextClickable(true);
        
		/*捆绑标题和菜单之间功能*/
		bingdingTitleandLeftMenu();
	}
	
	public void onGetWebView(JSONObject obj){
		try {
			switch(obj.getInt("action")) {
				case 0: {//点击相关栏
					jumpToQuestion(obj.getInt("id"), obj.getString("title"));
					break;
				}
				case 1: {//点击相关栏
					jumpToQuestion(obj.getInt("id"), obj.getString("title"));
					break;
				}
				case 2: {//点击相关栏
					jumpToQuestion(obj.getInt("id"), obj.getString("title"));
					break;
				}
				default:
					Toast.makeText(this, "参数出错！", Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void webHandle(JSONObject obj) {
	}

	private void jumpToQuestion(int id, String title) {
		/* 通过Bundle对象存储需要传递的数据 */
		Bundle bundle = new Bundle();
		/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
		bundle.putInt("id", id);
		bundle.putString("title", title);
		  
		Intent intent = new Intent();
		/*把bundle对象assign给Intent*/
		intent.putExtras(bundle);
		intent.setClass(this, QuestionActivity.class);
		startActivity(intent);
	}

}
