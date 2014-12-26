package com.zhihu.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.packet.zhihu.R;
import com.zhihu.common.Common;
import com.zhihu.components.ScrollLayout;
import com.zhihu.components.OnViewChangeListener;
import com.zhihu.components.BaseTitleBar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizActivity extends BaseActivity  implements OnViewChangeListener, OnClickListener{

	public Handler mWebHandler;//����web�ύ�ľ��
	private ScrollLayout mScrollLayout;
	private TextView[] mTextViews;
	private int mPageCount;
	private int mCurSel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mPageName = "�������";

        mTitle.setTitleText(mPageName);//�����ϲ��ܵ���
		mTitle.setTextClickable(false);
        
        View content_view = View.inflate(this, R.layout.activity_quiz_layout, null);
        setContent(content_view);
        
        init();
        
        /*test field*/
        CheckBox test = (CheckBox)findViewById(R.id.checkBoxQ);
        test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				submitQuestion();
			}
		});
    }
    
    private void init()
    {
    	mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayout);
    	LinearLayout quizBarLayout = (LinearLayout) findViewById(R.id.quiz_bar);

        for (int i = 0; i < mScrollLayout.getChildCount(); i++) {
        	if (mScrollLayout.getChildAt(i).getClass() != View.class) {//�Ǳ߿�
        		mPageCount++;
        	}
        }
    	int barCount = quizBarLayout.getChildCount();
    	mTextViews = new TextView[mPageCount];
    	//���õ��bar��Ч��
    	View check_v;
    	for(int i = 0, j = 0; i < barCount; i++) {
    		check_v=quizBarLayout.getChildAt(i);
            if(check_v instanceof TextView ){
            	mTextViews[j] = (TextView)check_v;
        		mTextViews[j].setEnabled(true);
        		mTextViews[j].setOnClickListener(this);
        		mTextViews[j].setTag(j++);
             }
    	}
    	
    	//���õ��EditTextʱ��Ч������touch��������ScorllLayout
    	
    	mCurSel = 0;
    	mTextViews[mCurSel].setEnabled(false);
    	mScrollLayout.SetOnViewChangeListener(this);
    }

    private void setCurPoint(int index)
    {
    	if (index < 0 || index > mPageCount - 1 || mCurSel == index) {
    		return ;
    	}    	
    	mTextViews[mCurSel].setEnabled(true);
    	mTextViews[index].setEnabled(false);
    	mScrollLayout.getChildAt(index).setFocusable(true);
    	mCurSel = index;
    }

    @Override
	public void onViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int pos = (Integer)(v.getTag());
		setCurPoint(pos);
		mScrollLayout.snapToScreen(pos);
	}

	/**
	 * �ύ����
	 */
	private void submitQuestion() {
        String url = "http://" + mServerIP + "/service/submit_quiz_service.php";

        EditText title = (EditText) findViewById(R.id.editTextTitle);
        EditText content = (EditText) findViewById(R.id.editTextDesc);
        Map<String, String> attr = new HashMap<String, String>();
        
        attr.put("title", title.getText().toString().trim());
        attr.put("content", content.getText().toString().trim());
        attr.put("user_id", "10000");
        
        Common sender = new Common();
        sender.sentHttpClient(url, attr, mWebHandler);
	}
}
