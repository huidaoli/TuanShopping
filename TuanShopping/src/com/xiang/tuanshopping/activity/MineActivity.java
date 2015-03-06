package com.xiang.tuanshopping.activity;

import com.xiang.tuanshopping.R;
import com.xiang.tuanshopping.bean.UserInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * 通过判断用户信息是否为空来显示界面
 * @author Administrator
 *
 */
public class MineActivity extends Activity {
	private UserInfo userInfo=null;
	//我的美团券
	private LinearLayout ll_mine_ticker;
	//收藏夹
	private LinearLayout ll_mine_favorite;
	//每日推荐
	private LinearLayout ll_everyday_recommend;
	//代付款
	private LinearLayout ll_order_first;
	//已付款
	private LinearLayout ll_order_second;
	//抽奖单
	private LinearLayout ll_order_third;
	//其他
	private LinearLayout ll_other;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_mine);
		
		
	}
	
	/**
	 * 初始化控件
	 */
	public void initControl(){
		
		ll_mine_ticker = (LinearLayout) findViewById(R.id.ll_mine_meituan_ticker);
		ll_mine_favorite = (LinearLayout) findViewById(R.id.ll_mine_favorite);
		
		ll_everyday_recommend = (LinearLayout) findViewById(R.id.ll_everyday_recommend);
		ll_order_first = (LinearLayout) findViewById(R.id.ll_order_first);
		ll_order_second = (LinearLayout) findViewById(R.id.ll_order_second);
		ll_order_third = (LinearLayout) findViewById(R.id.ll_order_third);
		
		ll_other = (LinearLayout) findViewById(R.id.ll_other);
	}
	
	/**
	 * 为各控件设置监听
	 */
	public void setLisentener(){
		//1、我的美团券
		ll_mine_ticker.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				//判断是否登录，未登录则跳转到登录页面
				if(userInfo==null){
					Intent intent=new Intent(MineActivity.this,LoginActivity.class);
					startActivity(intent);
				}else{//若已登录，跳转到我的美团券页面
					
				}
			}
		});
		//2、收藏夹
		ll_mine_favorite.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// 跳转至收藏页面
				
			}
		});
		//3、每日推荐
		ll_everyday_recommend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 跳转至每日推荐页
				
			}
		});
		//4、待付款
		ll_order_first.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		//5、已付款
	}
	
	/**
	 * 右上角的点击事件
	 * 若未登陆则跳转到登陆界面
	 * 若已登陆，跳转到通知界面
	 */
	public void inform(View btn){
		
	}
	
	/**
	 * 登录
	 */
	public void onLogin(View btn){
		//跳转到登录页面
		Intent intent=new Intent(this,LoginActivity.class);
		startActivity(intent);
	}
}
