package com.zhihu.activities;

import com.packet.zhihu.R;

import android.os.Bundle;
import android.view.View;

public class AnswerActivity extends BaseActivity {
	private Integer mQuesId;//问题id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO 自动生成的方法存根
		/*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
        //mQuesId = bundle.getInt("id");
        
        mPageName = "添加回答";
        mServerIP = getResources().getString(R.string.server_ip);

        mTitle.setTitleText(mPageName);//部属上才能调整
		mTitle.setTextClickable(false);
        
        View content_view = View.inflate(this, R.layout.activity_answer_layout, null);
        setContent(content_view);
	}
}
