package com.zhihu.activities;

import com.packet.zhihu.R;

import android.os.Bundle;
import android.view.View;

public class AnswerActivity extends BaseActivity {
	private Integer mQuesId;//����id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO �Զ����ɵķ������
		/*��ȡIntent�е�Bundle����*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*��ȡBundle�е����ݣ�ע�����ͺ�key*/
        //mQuesId = bundle.getInt("id");
        
        mPageName = "��ӻش�";
        mServerIP = getResources().getString(R.string.server_ip);

        mTitle.setTitleText(mPageName);//�����ϲ��ܵ���
		mTitle.setTextClickable(false);
        
        View content_view = View.inflate(this, R.layout.activity_answer_layout, null);
        setContent(content_view);
	}
}
