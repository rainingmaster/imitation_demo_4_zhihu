package com.zhihu.components;
//���˼·���������Ҳఴť����һ����Դ���飬�����������ܣ�ͼ��ȡ�Ȼ����Զ��������/ɾ��/�޸ģ�Ȼ������������ɰ�ť������addview��ӵ���Ļ��

import java.util.ArrayList;
import java.util.HashMap;

import com.packet.zhihu.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//manager title_bar.xml
public class Title {
	private Activity mActivity;
	private TextView mTitleText;
	private LinearLayout mTitleArea;
	private ImageView mTitleTextImg;
	private ArrayList<HashMap<String, Object>> mTitleButtonLst;
	
	public Title(Activity act) {
		this.mActivity = act;
	}
	
	/**
	 * ����title���ϴ����activity��
	*/
	public boolean initTitleBar() {
		boolean ret = true;
		
		mActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.component_titlebar_layout);  //title_barΪ�Լ��������Ĳ���
		//mTitleText = (TextView) mActivity.findViewById(R.id.title_text);
		mTitleArea = (LinearLayout) mActivity.findViewById(R.id.title_area);
		
		//�����ɵ����־ͼƬ
		mTitleTextImg = new ImageView(mActivity);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
        		20, 
        		20);
        mTitleTextImg.setLayoutParams(layoutParams);//���ó���
        mTitleTextImg.setImageResource(R.drawable.lead);
		
		return ret;
	}
	
	/**
	 * ����title���ϴ����activity��
	*/
	public boolean initTitleBar(String titleName, boolean clickable) {
		boolean ret = true;
		
		initTitleBar();
		setButtonText(titleName);
		setButtonClickable(clickable);
		return ret;
	}
	
	/**
     * ������ť��Դ���飬�������ɰ�ť
     * δ���
     */
	private void initTitleBtnLst() {
		mTitleButtonLst = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> MapMore = new HashMap<String, Object>();
		
		MapMore.put("btnimage", R.drawable.plus);
	}
	
	/**
     * ���ݰ�ť��Դ�������ɰ�ť����ӵ�������
     * δ���
     */
	private void setTitleBtnLst() {
	}
	
	 /**
     * �������Ҳఴť��Ӱ�ť�����λ��Ϊ��һλ
     */
	public void addTitleBtn(View view) {
		LinearLayout mTitleBtnLayout = (LinearLayout) mActivity.findViewById(R.id.right_button);
		mTitleBtnLayout.addView(view, 0);
	}
	
	/**
     * ���ñ�������
     */
	public void setButtonText(String name) {
		mTitleText.setText(name);
	}
	
	/**
     * ���õ�����⹦��
     */
	public void setButtonFun(OnClickListener listener) {
		mTitleArea.setOnClickListener(listener);
	}
	
	/**
     * ���ñ����Ƿ���Ӧ���
     * @param chose true������Ӧ���   false����Ӧ���
     */
	public void setButtonClickable(boolean chose) {
		mTitleArea.setClickable(chose);
		if (chose) {//�Ƿ���ʾ�ɵ����img
			mTitleArea.addView(mTitleTextImg, 0);
		}
		else {
			mTitleArea.removeView(mTitleTextImg);
			mTitleArea.setPadding(20, 0, 0, 0);
		}
	}
	
	/**
     * ��ȡ��ǰ�Ƿ���Ӧ���
     * @return true������Ӧ���   false����Ӧ���
     */
	public boolean isButtonClickable() {
		return mTitleArea.isClickable();
	}
}
