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

public class QuizActivity extends Activity  implements OnViewChangeListener, OnClickListener{


	private ScrollLayout mScrollLayout;
	private EditText et;
	private ImageView[] mImageViews;	
	private int mViewCount;	
	private int mCurSel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        init();
        Log.v("@@@@@@", "this is in  SwitchViewDemoActivity onClick()");
    }
    
    private void init()
    {
    	mScrollLayout = (ScrollLayout) findViewById(R.id.ScrollLayout);
    	/*LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout);   	
    	mViewCount = mScrollLayout.getChildCount();
    	mImageViews = new ImageView[mViewCount];   	
    	for(int i = 0; i < mViewCount; i++)    	{
    		mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
    		mImageViews[i].setEnabled(true);
    		mImageViews[i].setOnClickListener(this);
    		mImageViews[i].setTag(i);
    	}
    	mCurSel = 0;
    	mImageViews[mCurSel].setEnabled(false);*/
    	mScrollLayout.SetOnViewChangeListener(this);
    	et = (EditText)findViewById(R.id.editText2);
    	et.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO 自动生成的方法存根
				//et.setInputType(InputType.TYPE_NULL); // 关闭软键盘 
				mScrollLayout.onTouchEvent(event);
				return false;
			}  
  
        }); 
    	Log.v("@@@@@@", "this is in  SwitchViewDemoActivity init()");
    }

    private void setCurPoint(int index)
    {
    	if (index < 0 || index > mViewCount - 1 || mCurSel == index) {
    		return ;
    	}    	
    	mImageViews[mCurSel].setEnabled(true);
    	mImageViews[index].setEnabled(false);    	
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
