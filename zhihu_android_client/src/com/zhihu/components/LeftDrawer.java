package com.zhihu.components;

import java.util.ArrayList;
import java.util.HashMap;

import com.packet.zhihu.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LeftDrawer extends DrawerLayout{
	
	/**
	 * 菜单标题数组
	 */
    private String[] mMenuTitles;
    
    /**
	 * 调用本控件的上下文
	 */
	private Context mContext;
    
    /**
	 * 左侧菜单内容
	 */
	private View mLeftMenu;
    
    /**
	 * 左侧菜单layout
	 */
	private LinearLayout mLeftLayout;
    
    /**
	 * 左侧菜单列表
	 */
    private ListView mDrawerList;
    
    /**
	 * 左侧菜单用户信息
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
		
		mLeftMenu = View.inflate(mContext, R.layout.component_leftdrawer_leftmenu_layout, null);//需要添加的layout
        mDrawerList = (ListView) mLeftMenu.findViewById(R.id.menu_view);
        mUserText = (TextView) mLeftMenu.findViewById(R.id.user_bar);
        
        ArrayList<HashMap<String, Object>> items = getMenuItems();

        // Set the adapter for the list view
        SimpleAdapter adapter = new SimpleAdapter(mContext, items, R.layout.component_leftdrawer_item_layout, 
                new String[] {"menuimage","menutext"}, 
                new int[] {R.id.menuimage, R.id.menutext});
        mDrawerList.setAdapter(adapter);
        
        //mDrawerList.setSelection(0);//默认选中第一个
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO 自动生成的方法存根
		((LinearLayout) getChildAt(1)).removeAllViews();
		((LinearLayout) getChildAt(1)).addView(mLeftMenu);//添加菜单部分
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
}

	/**
	 * 设置无菜单时显示的主体。需要插入到第一个元素
	 * @param view 带插入视图
	 * @return
	 */
	public boolean setMainPart(View view) {
		boolean ret = true;
		
		addView(view, 0);//插入到第一个位置，即为背景
		
		DrawerLayout.LayoutParams layoutParams=new DrawerLayout.LayoutParams(
				DrawerLayout.LayoutParams.MATCH_PARENT, 
				DrawerLayout.LayoutParams.MATCH_PARENT);
		
		view.setLayoutParams(layoutParams);//设置长高
		
		return ret;
	}
	
	/**
	 * 设置用户信息，显示在菜单第一行
	 * @param usrName 用户名
	 * @param recourse 用户头像资源
	 * @return 空
	 */
	public boolean setUserMess(String usrName, int recourse) {
		boolean ret = true;
		Resources r = mContext.getResources();
		float length = r.getDimension(R.dimen.user_pic);
		float padding = r.getDimension(R.dimen.drawer_menu_img_margin);
		
		mUserText.setText(usrName);
		
		Drawable drawable = mContext.getResources().getDrawable(recourse);
		drawable.setBounds(0, 0, (int)length, (int)length);//必须设置图片大小，否则不显示
		mUserText.setCompoundDrawables(drawable, null, null, null);
		mUserText.setCompoundDrawablePadding((int)padding);
		return ret;
	}
    
    /**
     * 取得用于ListView的数据
     * @return
     */
    private ArrayList<HashMap<String, Object>> getMenuItems() {
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        mMenuTitles = mContext.getResources().getStringArray(R.array.menulist);
        for (int i = 0; i < mMenuTitles.length; i++) {
            //***********************************************
            //* 每一个map中的数据对应与ListView中的一个item *
            //* 在我自定义的布局文件中，一个item包括：      *
            //* 1、图片（menuimage）                   *
            //* 2、名称（menutext）                 *
            //* 所以map中也至少需要包括这两项数据           *
            //***********************************************
            HashMap<String, Object> map = new HashMap<String, Object>();
            //图片，key值可以随便取，映射关系会在实例化Adapter时定义，但我喜欢将key与布局文件中定义的id取同样的值
            //value值为图片的资源id
            map.put("menuimage", R.drawable.ic_launcher);
            //名称
            map.put("menutext", mMenuTitles[i]);
            
            items.add(map);
        }
        return items;
    }
    
    /**
     * 设置菜单打开和关闭时的功能
     * @param listener 监听实现对象
     */
    public void setDrawerFun(DrawerListener listener) {
    	setDrawerListener(listener);
    }
    
    /**
     * 显示左边抽屉菜单
     */
    public void openDrawer() {
    	openDrawer(Gravity.LEFT);
    }
    
    /**
     * 隐藏左边抽屉菜单
     */
    public void closeDrawer() {
    	closeDrawer(Gravity.LEFT);
    }
}
