package com.zhihu.activities;

import java.util.HashMap;
import java.util.Map;

import com.packet.zhihu.R;
import com.zhihu.common.Common;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

public class AnswerActivity extends BaseActivity {
	public Handler mWebHandler;//处理web提交的句柄
	private Integer mQuesId;//问题id
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO 自动生成的方法存根
		/*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
        mQuesId = bundle.getInt("id");
        
        mPageName = "添加回答";
        mServerIP = getResources().getString(R.string.server_ip);

        mTitle.setTitleText(mPageName);//部属上才能调整
		mTitle.setTextClickable(false);
        
        View content_view = View.inflate(this, R.layout.activity_answer_layout, null);
        setContent(content_view);
        
        mWebHandler = new Handler(){
        	public void handleMessage(Message msg){
				String message=(String)msg.obj;//obj不一定是String类，可以是别的类，看用户具体的应用
				//根据message中的信息对主线程的UI进行改动
        	}

        };
        
        /*test field*/
        CheckBox test = (CheckBox)findViewById(R.id.checkBoxA);
        test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				submitAnswer();
			}
		});
	}
	
	/**
	 * 提交答案
	 */
	private void submitAnswer() {
        String url = "http://" + mServerIP + "/service/submit_answer_service.php";

        EditText answer = (EditText) findViewById(R.id.editTextAnswer);
        Map<String, String> attr = new HashMap<String, String>();
        
        attr.put("question_id", mQuesId.toString());
        attr.put("content", answer.getText().toString().trim());
        attr.put("user_id", "11");//需要获得用户id
        
        Common sender = new Common();
        sender.sentHttpClient(url, attr, mWebHandler);
	}
}
