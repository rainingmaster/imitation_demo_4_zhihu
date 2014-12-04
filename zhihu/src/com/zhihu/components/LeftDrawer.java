package com.zhihu.components;

import java.util.ArrayList;
import java.util.HashMap;

import com.packet.zhihu.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LeftDrawer {
	
    private String[] mMenuTitles;
	private Activity mActivity;
    private ListView mDrawerList;
    private TextView mUserText;
    
    private DrawerLayout mDrawerLayout;
    
	public LeftDrawer(Activity act) {
		this.mActivity = act;
		View src_view = View.inflate(mActivity, R.layout.left_drawer, null);//��Ҫ��ӵ�layout
    	mDrawerLayout = (DrawerLayout) src_view.findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) src_view.findViewById(R.id.menu_view);
        mUserText = (TextView) src_view.findViewById(R.id.user_bar);
        
        initLeftDrawer();
	}
	
	/**
	* @param act ��Ҫ��ʾ��activity
	* @param usr_name �˵���ʾ���û���
	* @param resource �˵���ʾ���û�ͷ��
	*/
	public LeftDrawer(Activity act, String usr_name, int resource) {
		this(act);
		setUserMess(usr_name, resource);
	}
	
	public DrawerLayout initLeftDrawer() {          
        ArrayList<HashMap<String, Object>> items = getItems();

        // Set the adapter for the list view
        SimpleAdapter adapter = new SimpleAdapter(mActivity, items, R.layout.list_item, 
                new String[] {"menuimage","menutext"}, 
                new int[] {R.id.menuimage, R.id.menutext});
        mDrawerList.setAdapter(adapter);
        
        mDrawerList.setSelection(0);;//Ĭ��ѡ�е�һ��
        
        return mDrawerLayout;
        
	}

	/**
	 * �����޲˵�ʱ��ʾ�����塣��Ҫ���뵽��һ��Ԫ��
	 * @param view ��������ͼ
	 * @return
	 */
	public boolean setMainPart(View view) {
		boolean ret = true;
		
		mDrawerLayout.addView(view, 0);//���뵽��һ��λ�ã���Ϊ����
		
		DrawerLayout.LayoutParams layoutParams=new DrawerLayout.LayoutParams(
				DrawerLayout.LayoutParams.MATCH_PARENT, 
				DrawerLayout.LayoutParams.MATCH_PARENT);
		
		view.setLayoutParams(layoutParams);//���ó���
		
		return ret;
	}
	
	/**
	 * �����û���Ϣ����ʾ�ڲ˵���һ��
	 * @param usrName �û���
	 * @param recourse �û�ͷ����Դ
	 * @return ��
	 */
	public boolean setUserMess(String usrName, int recourse) {
		boolean ret = true;
		Resources r = mActivity.getResources();
		float length = r.getDimension(R.dimen.user_pic);
		float padding = r.getDimension(R.dimen.drawer_menu_img_margin);
		
		mUserText.setText(usrName);
		
		Drawable drawable = mActivity.getResources().getDrawable(recourse);
		drawable.setBounds(0, 0, (int)length, (int)length);//��������ͼƬ��С��������ʾ
		mUserText.setCompoundDrawables(drawable, null, null, null);
		mUserText.setCompoundDrawablePadding((int)padding);
		return ret;
	}
    
    /**
     * ȡ������ListView������
     * @return
     */
    private ArrayList<HashMap<String, Object>> getItems() {
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        mMenuTitles = mActivity.getResources().getStringArray(R.array.menulist);
        for (int i = 0; i < mMenuTitles.length; i++) {
            //***********************************************
            //* ÿһ��map�е����ݶ�Ӧ��ListView�е�һ��item *
            //* �����Զ���Ĳ����ļ��У�һ��item������      *
            //* 1��ͼƬ��menuimage��                   *
            //* 2�����ƣ�menutext��                 *
            //* ����map��Ҳ������Ҫ��������������           *
            //***********************************************
            HashMap<String, Object> map = new HashMap<String, Object>();
            //ͼƬ��keyֵ�������ȡ��ӳ���ϵ����ʵ����Adapterʱ���壬����ϲ����key�벼���ļ��ж����idȡͬ����ֵ
            //valueֵΪͼƬ����Դid
            map.put("menuimage", R.drawable.ic_launcher);
            //����
            map.put("menutext", mMenuTitles[i]);
            
            items.add(map);
        }
        return items;
    }
    
    /**
     * ���ò˵��򿪺͹ر�ʱ�Ĺ���
     * @param listener ����ʵ�ֶ���
     */
    public void setDrawerFun(DrawerListener listener) {
    	mDrawerLayout.setDrawerListener(listener);
    }
    
    /**
     * ��ʾ��߳���˵�
     */
    public void openDrawer() {
    	mDrawerLayout.openDrawer(Gravity.LEFT);
    }
    
    /**
     * ������߳���˵�
     */
    public void closeDrawer() {
    	mDrawerLayout.closeDrawer(Gravity.LEFT);
    }
    
    public DrawerLayout getDrawerlayout() {
    	return mDrawerLayout;
    }
}
