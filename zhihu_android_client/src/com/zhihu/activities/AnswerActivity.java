package com.zhihu.activities;

import java.util.HashMap;
import java.util.Map;

import com.packet.zhihu.R;
import com.zhihu.common.Common;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

public class AnswerActivity extends BaseActivity {
	private Integer mQuesId;//����id
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO �Զ����ɵķ������
		/*��ȡIntent�е�Bundle����*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*��ȡBundle�е����ݣ�ע�����ͺ�key*/
        mQuesId = bundle.getInt("id");
        
        mPageName = "��ӻش�";
        mServerIP = getResources().getString(R.string.server_ip);

        mTitle.setTitleText(mPageName);//�����ϲ��ܵ���
		mTitle.setTextClickable(false);
        
        View content_view = View.inflate(this, R.layout.activity_answer_layout, null);
        setContent(content_view);
        
        /*test field*/
        CheckBox test = (CheckBox)findViewById(R.id.checkBoxA);
        test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				submitAnswer();
			}
		});
	}
	
	/**
	 * �ύ��
	 */
	private void submitAnswer() {
        String url = "http://" + mServerIP + "/service/submit_answer_service.php";

        EditText answer = (EditText) findViewById(R.id.editTextAnswer);
        Map<String, String> attr = new HashMap<String, String>();
        
        attr.put("question_id", mQuesId.toString());
        attr.put("content", answer.getText().toString().trim());
        attr.put("user_id", "10000");//��Ҫ����û�id
        
        Common.sentHttpClient(url, attr);
	}
}
