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
		// TODO �Զ����ɵĹ��캯�����
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
    * ������������ߵ�text
	*/
	private void initTitleTextModle() {
		mTextModle = (ViewGroup) getChildAt(0);//��һ��layoutΪText����
		mTextModle.removeAllViews();
		
		changeTextClickable();
		mTitleText = (TextView)View.inflate(mContext, R.layout.component_titlebar_text_layout, null);
		changeTitleText();//����mTitleText����
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
    * �����������ұߵ�view
	*/
	private void initTitleViewModle() {
		mViewModle = (ViewGroup) getChildAt(1);//�ڶ���layoutΪview����
		mViewModle.removeAllViews();
		
		/*ImageButton btn1 = (ImageButton) View.inflate(mContext, R.layout.component_titlebar_view_layout, null);
		btn1.setImageResource(R.drawable.plus);
		ImageButton btn2 = (ImageButton) View.inflate(mContext, R.layout.component_titlebar_view_layout, null);
		btn2.setImageResource(R.drawable.pen);
		
		mViewModle.addView(btn1);
		mViewModle.addView(btn2);

		TextView test = (TextView)View.inflate(mContext, R.layout.component_titlebar_text_layout, null);
		//changeTitleText();//����mTitleText����
		test.setText("����");
		mViewModle.addView(test);*/
	}
	
	/**
     * ���ñ�������
     * @param ������������
     */
	public void setTitleText(String name) {
		mTitle = name;
		if(mTitleText != null){
			changeTitleText();
		}
	}
	
	/**
     * ʵ�ʸı��������
     */
	private void changeTitleText() {
		mTitleText.setText(mTitle);
	}
	
	/**
     * ���ñ����Ƿ���Ӧ���
     * @param chose true������Ӧ���   false����Ӧ���
     */
	public void setTextClickable(boolean chose) {
		mTextModle.setClickable(chose);
		if (mTextModle != null && mTitleText != null) {
			changeTextClickable();
		}
	}
	
	/**
     * ʵ�ʸı�����Ƿ���Ӧ���
     * @param chose true������Ӧ���   false����Ӧ���
     */
	private void changeTextClickable() {
		if (mTextModle.isClickable()) {//�Ƿ���ʾ�ɵ����tag
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
		if (mTextModle != null) {
			return mTextModle.isClickable();
		}
		return false;
	}

}
