package com.zhihu.activities;

import com.packet.zhihu.R;
import com.zhihu.components.ScrollLayout;
import com.zhihu.components.OnViewChangeListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizActivity extends Activity  implements OnViewChangeListener, OnClickListener{


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

        for (int i = 0; i < mScrollLayout.getChildCount(); i++) {
        	if (mScrollLayout.getChildAt(i).getClass() != View.class) {//是边框
        		mPageCount++;
        	}
        }
    	int barCount = quizBarLayout.getChildCount();
    	mTextViews = new TextView[mPageCount];
    	//设置点击bar的效果
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
    	
    	//设置点击EditText时的效果，将touch动作传给ScorllLayout
    	
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
}
