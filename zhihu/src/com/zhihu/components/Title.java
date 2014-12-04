package com.zhihu.components;
//设计思路：将标题右侧按钮做成一个资源数组，包括触发功能，图标等。然后可以对数组添加/删除/修改，然后根据数组生成按钮，再用addview添加到屏幕上

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
	 * 将本title贴上传入的activity中
	*/
	public boolean initTitleBar() {
		boolean ret = true;
		
		mActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);  //title_bar为自己标题栏的布局
		mTitleText = (TextView) mActivity.findViewById(R.id.title_text);
		mTitleArea = (LinearLayout) mActivity.findViewById(R.id.title_area);
		
		//创建可点击标志图片
		mTitleTextImg = new ImageView(mActivity);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
        		20, 
        		20);
        mTitleTextImg.setLayoutParams(layoutParams);//设置长高
        mTitleTextImg.setImageResource(R.drawable.lead);
		
		return ret;
	}
	
	/**
	 * 将本title贴上传入的activity中
	*/
	public boolean initTitleBar(String titleName, boolean clickable) {
		boolean ret = true;
		
		initTitleBar();
		setButtonText(titleName);
		setButtonClickable(clickable);
		return ret;
	}
	
	/**
     * 建立按钮资源数组，用于生成按钮
     * 未完成
     */
	private void initTitleBtnLst() {
		mTitleButtonLst = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> MapMore = new HashMap<String, Object>();
		
		MapMore.put("btnimage", R.drawable.plus);
	}
	
	/**
     * 根据按钮资源数组生成按钮并添加到标题栏
     * 未完成
     */
	private void setTitleBtnLst() {
	}
	
	 /**
     * 往标题右侧按钮添加按钮，添加位置为第一位
     */
	public void addTitleBtn(View view) {
		LinearLayout mTitleBtnLayout = (LinearLayout) mActivity.findViewById(R.id.right_button);
		mTitleBtnLayout.addView(view, 0);
	}
	
	/**
     * 设置标题内容
     */
	public void setButtonText(String name) {
		mTitleText.setText(name);
	}
	
	/**
     * 设置点击标题功能
     */
	public void setButtonFun(OnClickListener listener) {
		mTitleArea.setOnClickListener(listener);
	}
	
	/**
     * 设置标题是否响应点击
     * @param chose true可以响应点击   false不响应点击
     */
	public void setButtonClickable(boolean chose) {
		mTitleArea.setClickable(chose);
		if (chose) {//是否显示可点击的img
			mTitleArea.addView(mTitleTextImg, 0);
		}
		else {
			mTitleArea.removeView(mTitleTextImg);
			mTitleArea.setPadding(20, 0, 0, 0);
		}
	}
	
	/**
     * 获取当前是否响应点击
     * @return true可以响应点击   false不响应点击
     */
	public boolean isButtonClickable() {
		return mTitleArea.isClickable();
	}
}
