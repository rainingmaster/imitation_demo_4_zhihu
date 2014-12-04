package com.zhihu.components;
//���˼·���������Ҳఴť����һ����Դ���飬�����������ܣ�ͼ��ȡ�Ȼ����Զ��������/ɾ��/�޸ģ�Ȼ������������ɰ�ť������addview��ӵ���Ļ��

import java.util.ArrayList;
import java.util.HashMap;

import com.packet.zhihu.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

//manager title_bar.xml
public class Title {
	private Activity mActivity;
	private TextView mTitleText;
	private LinearLayout mTitleArea;
	private ArrayList<HashMap<String, Object>> mTitleButtonLst;
	
	public Title(Activity act) {
		this.mActivity = act;
	}
	
	public boolean initTitleBar() {
		boolean ret = true;
		
		mActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);  //title_barΪ�Լ��������Ĳ���
		mTitleText = (TextView) mActivity.findViewById(R.id.title_text);
		mTitleArea = (LinearLayout) mActivity.findViewById(R.id.title_area);
		
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
	
	public void setButtonText(String name) {
		mTitleText.setText(name);
	}
	
	public void setButtonFun(OnClickListener listener) {
		mTitleArea.setOnClickListener(listener);
	}
}
