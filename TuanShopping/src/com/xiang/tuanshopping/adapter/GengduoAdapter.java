package com.xiang.tuanshopping.adapter;

import com.xiang.tuanshopping.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GengduoAdapter extends BaseAdapter {
	
	private String[] set = {"消息提醒","分享设置","邀请好友使用美团","字号大小","清空缓存","意见反馈","支付帮忙","检查更新","关于美团"};
	
	private LayoutInflater inflater;
	
	public GengduoAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return set.length+2;
	}

	@Override
	public Object getItem(int position) {
		if (position ==0) {
			return null;
		}
		else if(position <= 6){
			return set[position -1];
		}
		else if(position == 7){
			return null;
		}
		else{
			return set[position -2];
		}
		
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String name=(String) getItem(position);
		if (position == 0) {
			View layout = inflater.inflate(R.layout.fourth_item_title, parent,false);
			TextView tvItem = (TextView) layout.findViewById(R.id.tv_item3);
			tvItem.setText("设置");
			return layout;
		}
		else if(position <= 1){
			View layout = inflater.inflate(R.layout.fourth_item, parent,false);
			TextView tvItem = (TextView) layout.findViewById(R.id.tv_item);
			tvItem.setText(set[1]);
			return layout;
		}
		else if(position == 2){
			View layout = inflater.inflate(R.layout.fourth_item_two,null);
			TextView tv =  (TextView) layout.findViewById(R.id.tv_item2);
			tv.setText("2G/3G下使用无图模式");
			ImageView iv =  (ImageView) layout.findViewById(R.id.iv_iv1);
			iv.setImageResource(R.drawable.bg_settings_drag_off);
			return layout;
		}
		else if(2<position && position <= 6 ){
			View layout = inflater.inflate(R.layout.fourth_item, parent,false);
			TextView tvItem = (TextView) layout.findViewById(R.id.tv_item);
			tvItem.setText(set[position-1]);
			return layout;
		}
		else if(position == 7){
			View layout = inflater.inflate(R.layout.fourth_item_title, parent,false);
			TextView tvItem = (TextView) layout.findViewById(R.id.tv_item3);
			tvItem.setText("其他");
			return layout;
		}
		else{
			View layout = inflater.inflate(R.layout.fourth_item, parent,false);
			TextView tvItem = (TextView) layout.findViewById(R.id.tv_item);
			tvItem.setText(set[position-2]);
			return layout;
		}
	}

}
