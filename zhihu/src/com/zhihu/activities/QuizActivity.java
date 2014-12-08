package com.zhihu.activities;

import com.packet.zhihu.R;
import com.zhihu.components.ScrollLayout;
import com.zhihu.components.OnViewChangeListener;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizActivity extends Activity  implements OnViewChangeListener, OnClickListener, OnTouchListener{


	private ScrollLayout mScrollLayout;
	private TextView[] mTextViews;
	private int mPageCount;	
	private int mCurSel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        init();
    }
    
    private void init()
    {
    	mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayout);
    	LinearLayout quizBarLayout = (LinearLayout) findViewById(R.id.quiz_bar);
    	mPageCount = mScrollLayout.getChildCount();
    	int barCount = quizBarLayout.getChildCount();
    	mTextViews = new TextView[mPageCount];
    	//���õ��bar��Ч��
    	for(int i = 0, j = 0; i < barCount; i++)    	{
    		View check_v=quizBarLayout.getChildAt(i);
            if(check_v instanceof TextView ){
            	mTextViews[j] = (TextView)check_v;
        		mTextViews[j].setEnabled(true);
        		mTextViews[j].setOnClickListener(this);
        		mTextViews[j].setTag(j++);
             }
    	}
    	//���õ��EditTextʱ��Ч������touch��������ScorllLayout
    	for(int i = 0; i < mPageCount; i++)    	{
    		View check_v=mScrollLayout.getChildAt(i);
            check_v.setOnTouchListener(this);
    	}
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
	public void OnViewChange(int view) {
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �Զ����ɵķ������
		//((EditText) v).setInputType(InputType.TYPE_NULL); // �ر������ 
		EditText edit = (EditText) v;
		int inType = edit.getInputType();
		edit.setInputType(InputType.TYPE_NULL);
		edit.onTouchEvent(event);
		edit.setInputType(inType);
		
		mScrollLayout.onTouchEvent(event);
		return false;
	}  
}
