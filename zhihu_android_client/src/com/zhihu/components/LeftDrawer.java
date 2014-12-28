package com.zhihu.components;

import java.util.ArrayList;
import java.util.HashMap;

import com.packet.zhihu.R;
import com.zhihu.activities.BaseActivity;
import com.zhihu.activities.QuestionActivity;
import com.zhihu.activities.QuizActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LeftDrawer extends DrawerLayout{
	
	/**
	 * �˵���������
	 */
    private String[] mMenuTitles;
    
    /**
	 * ���ñ��ؼ���������
	 */
	private Context mContext;
    
    /**
	 * ���˵�����
	 */
	private View mLeftMenu;
    
    /**
	 * ���˵�layout
	 */
	private LinearLayout mLeftLayout;
    
    /**
	 * ���˵��б�
	 */
    private ListView mDrawerList;
    
    /**
	 * ���˵��û���Ϣ
	 */
    private TextView mUserText;
    
    public LeftDrawer(Context context) {
        super(context, null);
        initLeftDrawer(context);
    }

    public LeftDrawer(Context context, AttributeSet attrs) {
    	super(context, attrs, 0);
        initLeftDrawer(context);
    }

    public LeftDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLeftDrawer(context);
    }
    
	public void initLeftDrawer(Context context) {
		mContext = context;
		
		mLeftMenu = View.inflate(mContext, R.layout.component_leftdrawer_leftmenu_layout, null);//��Ҫ��ӵ�layout
        mDrawerList = (ListView) mLeftMenu.findViewById(R.id.menu_view);
        mUserText = (TextView) mLeftMenu.findViewById(R.id.user_bar);
        
        ArrayList<HashMap<String, Object>> items = getMenuItems();

        // Set the adapter for the list view
        SimpleAdapter adapter = new SimpleAdapter(mContext, items, R.layout.component_leftdrawer_item_layout, 
                new String[] {"menuimage","menutext"}, 
                new int[] {R.id.menuimage, R.id.menutext});
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO �Զ����ɵķ������
				//����������һ���Զ���Ļص�������Ȼ��ÿؼ��������ط�����ʱҪʵ�ֻص������Ľӿ�
				//����������ʵ��һ������Ŀؼ�������д����
				BaseActivity activity = (BaseActivity) mContext;
				switch(position){
				case 1://��ҳ
					break;
				case 2://
					break;
				case 3:
					break;
				case 6: {//����
					Intent intent = new Intent();
					intent.setClass(mContext, QuizActivity.class);
					activity.jumpActivity(intent);
				}
					break;
				}
				closeDrawer();
			}
		});
        
        //mDrawerList.setSelection(0);//Ĭ��ѡ�е�һ��
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO �Զ����ɵķ������
		((LinearLayout) getChildAt(1)).removeAllViews();
		((LinearLayout) getChildAt(1)).addView(mLeftMenu);//��Ӳ˵�����
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
}

	/**
	 * �����޲˵�ʱ��ʾ�����塣��Ҫ���뵽��һ��Ԫ��
	 * @param view ��������ͼ
	 * @return
	 */
	public boolean setMainPart(View view) {
		boolean ret = true;
		
		addView(view, 0);//���뵽��һ��λ�ã���Ϊ����
		
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
		Resources r = mContext.getResources();
		float length = r.getDimension(R.dimen.user_pic);
		float padding = r.getDimension(R.dimen.drawer_menu_img_margin);
		
		mUserText.setText(usrName);
		
		Drawable drawable = mContext.getResources().getDrawable(recourse);
		drawable.setBounds(0, 0, (int)length, (int)length);//��������ͼƬ��С��������ʾ
		mUserText.setCompoundDrawables(drawable, null, null, null);
		mUserText.setCompoundDrawablePadding((int)padding);
		return ret;
	}
    
    /**
     * ȡ������ListView������
     * @return
     */
    private ArrayList<HashMap<String, Object>> getMenuItems() {
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        mMenuTitles = mContext.getResources().getStringArray(R.array.menulist);
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
    	setDrawerListener(listener);
    }
    
    /**
     * ��ʾ��߳���˵�
     */
    public void openDrawer() {
    	openDrawer(Gravity.LEFT);
    }
    
    /**
     * ������߳���˵�
     */
    public void closeDrawer() {
    	closeDrawer(Gravity.LEFT);
    }
}
