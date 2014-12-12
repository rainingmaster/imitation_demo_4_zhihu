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

public class BaseTitleBar extends ViewGroup {

	private static final String TAG = "BASETITLE";
	private Context mContext;
	private ViewGroup mTextModle;
	private ViewGroup mButtonModle;
	private TextView mTitleText;
	private ImageView mClickTag;
	
	public BaseTitleBar(Context context) {
		super(context);
		// TODO �Զ����ɵĹ��캯�����
	}	
	public BaseTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}
	public BaseTitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub		
		init(context);
	}
	private void init(Context context)
	{
		this.mContext = context;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO �Զ����ɵķ������
		if (changed) {
			super.layout(l, t, r, b);
			initTitleTextModle();
			initTitleButtonModle();
			initTitleTextClickableTag();
		}
	}
	
	/**
     * ���ñ�������
     */
	public void setTitleText(String name) {
		mTitleText.setText(name);
	}

	/**
    * ������������ߵ�text
	*/
	private void initTitleTextModle() {
		mTextModle = (ViewGroup) getChildAt(0);//��һ��layoutΪText����
		mTextModle.removeAllViews();
		
		if (mTextModle.isClickable()) {
			
		}
		mTitleText = (TextView)View.inflate(mContext, R.layout.component_titlebar_text_layout, null);
		mTextModle.addView(mTitleText);
	}

	/**
    * �������������text�ɵ��ʱ��ʾ��ͼƬ
    * �����ⲻ�ɵ��ʱ��ʾͼƬ��ȥ��
	*/
	private void initTitleTextClickableTag() {
		//�����ɵ����־ͼƬ
		mClickTag = new ImageView(mContext);
	    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(20, 20);
	    mClickTag.setLayoutParams(layoutParams);//���ó���
	    mClickTag.setImageResource(R.drawable.lead);
	}

	/**
    * �����������ұߵ�button
	*/
	private void initTitleButtonModle() {
		mButtonModle = (ViewGroup) getChildAt(1);//�ڶ���layoutΪButton����
		mButtonModle.removeAllViews();
		
		ImageButton btn1 = (ImageButton) View.inflate(mContext, R.layout.component_titlebar_button_layout, null);
		btn1.setImageResource(R.drawable.plus);
		ImageButton btn2 = (ImageButton) View.inflate(mContext, R.layout.component_titlebar_button_layout, null);
		btn2.setImageResource(R.drawable.pen);
		
		mButtonModle.addView(btn1);
		mButtonModle.addView(btn2);
	}
	
	/**
     * ���ñ����Ƿ���Ӧ���
     * @param chose true������Ӧ���   false����Ӧ���
     */
	public void setTextClickable(boolean chose) {
		mTextModle.setClickable(chose);
		if (chose) {//�Ƿ���ʾ�ɵ����img
			mTextModle.addView(mClickTag, 0);
		}
		else {
			mTextModle.removeView(mClickTag);
			mTitleText.setPadding(20, 0, 0, 0);
		}
	}
	
	/**
     * ��ȡText����ǰ�Ƿ���Ӧ���
     * @return true������Ӧ���   false����Ӧ���
     */
	public boolean isTextClickable() {
		return mTextModle.isClickable();
	}

}
