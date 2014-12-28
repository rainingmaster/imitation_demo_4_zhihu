package com.zhihu.components;

import android.content.Context;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollLayout extends ViewGroup{

    private static final String TAG = "ScrollLayout";      
    private VelocityTracker mVelocityTracker;  			// 主要用跟踪触摸屏事件（flinging事件和其他gestures手势事件）的速率    
    private static final int SNAP_VELOCITY = 600;        
    private Scroller  mScroller;						// 婊戝姩鎺у埗鍣�
    private int mCurScreen;    						    
	private int mDefaultScreen = 0;//
    private int mBorderWidth = 20;//边框宽度
    private int mPageWidth;
    private int mPageCount;//页数
    private float mLastMotionX;
    private float mLastTapX;
    private float mLastTapY;
    
    private OnViewChangeListener mOnViewChangeListener;	 
	public ScrollLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}	
	public ScrollLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	public ScrollLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub		
		init(context);
	}
	
	private void init(Context context)
	{
		mCurScreen = mDefaultScreen;
	 //   mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();    	        
	    mScroller = new Scroller(context);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub		
		 if (changed) {    
	            int childLeft = 0;    
	            final int childCount = getChildCount();
                int childWidth;
	            for (int i=0; i<childCount; i++) {    
	                final View childView = getChildAt(i);    
	                if (childView.getVisibility() != View.GONE) {
	                	childWidth = childView.getMeasuredWidth();
						childView.layout(childLeft, 0,     
	                            childLeft+childWidth, childView.getMeasuredHeight());    
	                    childLeft += childWidth;
	                }    
	            }
	            mPageWidth = childLeft;
	        }    
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int width = MeasureSpec.getSize(widthMeasureSpec);       
	    final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	    int BorderSpec = MeasureSpec.makeMeasureSpec(mBorderWidth, widthMode);
	    mPageCount = 0;
	    		
		final int count = getChildCount();
        for (int i = 0; i < count; i++) {
        	if (getChildAt(i).getClass() == View.class) {//是边框
        		getChildAt(i).measure(BorderSpec, heightMeasureSpec);
        	}else{//是页面
        		getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        		mPageCount++;
        	}
        }                
        scrollTo(mCurScreen * (width + mBorderWidth), 0);
	}

	 public void snapToDestination() {    
	        final int screenWidth = getWidth();
	        final int destScreen = (getScrollX()+ screenWidth/2)/screenWidth;    
	        snapToScreen(destScreen);
	 }  
	
	 public void snapToScreen(int whichScreen) {    	
	        // get the valid layout page
	        whichScreen = Math.max(0, Math.min(whichScreen, mPageCount - 1));
	        if (getScrollX() != (whichScreen*(getWidth()+mBorderWidth))) {    	                
	            final int delta = (whichScreen*(getWidth()+mBorderWidth))-getScrollX();    
	      	    mScroller.startScroll(getScrollX(), 0,
	                    delta, 0, Math.abs(delta)*2);
	            
	            mCurScreen = whichScreen;    
	            invalidate();       // Redraw the layout    	            
	            if (mOnViewChangeListener != null)
	            {
	            	mOnViewChangeListener.onViewChange(mCurScreen);
	            }
	        }    
	    }    

	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		if (mScroller.computeScrollOffset()) {    
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());  
            postInvalidate();    
        }   
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		// TODO 自动生成的方法存根
		final int action = event.getAction();
        final float x = event.getX();    
        final float y = event.getY();
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			onTouchEvent(event);
			mLastTapX = x;
			mLastTapY = y;
			return false;
		    
		case MotionEvent.ACTION_MOVE:
			onTouchEvent(event);
			if(Math.abs(mLastTapX - x) < 3 && Math.abs(mLastTapY - y) < 3) {//移动幅度小的则传到
				return false;
			}
			return true; 
		case MotionEvent.ACTION_UP:
			onTouchEvent(event);
			return false;
		}
		return false;    
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub           	            
	        final int action = event.getAction();    
	        final float x = event.getX();    
	        final float y = event.getY();
	            
	        switch (action) {    
	        case MotionEvent.ACTION_DOWN:
	        	if (mVelocityTracker == null) {
			            mVelocityTracker = VelocityTracker.obtain();
			            mVelocityTracker.addMovement(event);
			    }
	            if (!mScroller.isFinished()){    
	                mScroller.abortAnimation();    
	            }
	            mLastMotionX = x;
	            break;    
	                
	        case MotionEvent.ACTION_MOVE:  
	           int deltaX = (int)(mLastMotionX - x);     
        	   if (IsCanMove(deltaX))
        	   {
        		 if (mVelocityTracker != null)
  		         {
  		            	mVelocityTracker.addMovement(event); 
  		         }   
  	             mLastMotionX = x;     
  	             scrollBy(deltaX, 0);
        	   }
         
	           break;    	                
	        case MotionEvent.ACTION_UP:       	        	
	        	int velocityX = 0;
	            if (mVelocityTracker != null)
	            {
	            	mVelocityTracker.addMovement(event); 
	            	mVelocityTracker.computeCurrentVelocity(1000);  
	            	velocityX = (int) mVelocityTracker.getXVelocity();
	            }	               	                
	            if (velocityX > SNAP_VELOCITY && mCurScreen > 0) {
	                // Fling enough to move left       
	                //Log.e(TAG, "snap left");    
	                snapToScreen(mCurScreen - 1);       
	            } else if (velocityX < -SNAP_VELOCITY       
	                    && mCurScreen < mPageCount - 1) {       
	                // Fling enough to move right       
	                //Log.e(TAG, "snap right");    
	                snapToScreen(mCurScreen + 1);
	            } else {       
	                snapToDestination();       
	            }      
	            	            
	            if (mVelocityTracker != null) {       
	                mVelocityTracker.recycle();       
	                mVelocityTracker = null;       
	            }
	            break;      
	        }    	            
	        return true;
	}
	
	private boolean IsCanMove(int deltaX)
	{
		if (getScrollX() <= 0 && deltaX < 0 ){
			return false;
		}
		else if (getScrollX() >= (mPageWidth-getWidth()) && deltaX > 0){
			return false;
		}
		return true;
	}
	
	public void SetOnViewChangeListener(OnViewChangeListener listener)
	{
		mOnViewChangeListener = listener;
	}
}