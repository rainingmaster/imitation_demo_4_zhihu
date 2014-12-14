package com.zhihu.components;

import com.packet.zhihu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseTitleBar extends LinearLayout {

	private static final String TAG = "BASETITLE";
	private Context mContext;
	private ViewGroup mTextModle;
	private ViewGroup mViewModle;
	private TextView mTitleText;
	private String mTitle;
	private ImageView mClickTag;
	
	public BaseTitleBar(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}	
	public BaseTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context)
	{
		this.mContext = context;
		this.mTitle = context.getString(R.string.app_name);
		initTitleTextClickableTag();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		initTitleTextModle();
		initTitleViewModle();
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
    * 建立标题栏左边的text
	*/
	private void initTitleTextModle() {
		mTextModle = (ViewGroup) getChildAt(0);//第一个layout为Text部分
		mTextModle.removeAllViews();
		
		changeTextClickable();
		mTitleText = (TextView)View.inflate(mContext, R.layout.component_titlebar_text_layout, null);
		changeTitleText();//设置mTitleText内容
		mTextModle.addView(mTitleText);
	}

	/**
    * 建立标题栏左边text可点击时标示的图片
    * 当标题不可点击时标示图片将去除
	*/
	private void initTitleTextClickableTag() {
		//创建可点击标志图片
		mClickTag = new ImageView(mContext);
	    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(20, 20);
	    mClickTag.setLayoutParams(layoutParams);//设置长高
	    mClickTag.setImageResource(R.drawable.lead);
	}

	/**
    * 建立标题栏右边的view
	*/
	private void initTitleViewModle() {
		mViewModle = (ViewGroup) getChildAt(1);//第二个layout为view部分
		mViewModle.removeAllViews();
		
		/*ImageButton btn1 = (ImageButton) View.inflate(mContext, R.layout.component_titlebar_view_layout, null);
		btn1.setImageResource(R.drawable.plus);
		ImageButton btn2 = (ImageButton) View.inflate(mContext, R.layout.component_titlebar_view_layout, null);
		btn2.setImageResource(R.drawable.pen);
		
		mViewModle.addView(btn1);
		mViewModle.addView(btn2);

		TextView test = (TextView)View.inflate(mContext, R.layout.component_titlebar_text_layout, null);
		//changeTitleText();//设置mTitleText内容
		test.setText("发布");
		mViewModle.addView(test);*/
	}
	
	/**
     * 设置标题内容
     * @param 标题文字内容
     */
	public void setTitleText(String name) {
		mTitle = name;
		if(mTitleText != null){
			changeTitleText();
		}
	}
	
	/**
     * 实际改变标题内容
     */
	private void changeTitleText() {
		mTitleText.setText(mTitle);
	}
	
	/**
     * 设置标题是否响应点击
     * @param chose true可以响应点击   false不响应点击
     */
	public void setTextClickable(boolean chose) {
		mTextModle.setClickable(chose);
		if (mTextModle != null && mTitleText != null) {
			changeTextClickable();
		}
	}
	
	/**
     * 实际改变标题是否响应点击
     * @param chose true可以响应点击   false不响应点击
     */
	private void changeTextClickable() {
		if (mTextModle.isClickable()) {//是否显示可点击的tag
			mTextModle.addView(mClickTag, 0);
		}
		else {
			mTextModle.removeView(mClickTag);
			mTitleText.setPadding(20, 0, 0, 0);
		}
		
	}
	
	/**
     * 获取Text区域当前是否响应点击
     * @return true可以响应点击   false不响应点击
     */
	public boolean isTextClickable() {
		if (mTextModle != null) {
			return mTextModle.isClickable();
		}
		return false;
	}

}
