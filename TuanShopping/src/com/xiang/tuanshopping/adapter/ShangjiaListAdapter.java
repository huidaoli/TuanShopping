package com.xiang.tuanshopping.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.xiang.tuanshopping.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShangjiaListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<HashMap<String,Object>> data;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DataList dl = new DataList();
		convertView = LayoutInflater.from(context).inflate(R.layout.shangjia_item, null);
		dl.iv_name = (ImageView) convertView.findViewById(R.id.iv_name);
		dl.iv_shangjia_image = (ImageView) convertView.findViewById(R.id.iv_shangjia_image);
		dl.tv_shangjia_name = (TextView) convertView.findViewById(R.id.tv_shangjia_name);
		dl.tv_youhuijia = (TextView) convertView.findViewById(R.id.tv_youhuijia);
		dl.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
		dl.tv_qi = (TextView) convertView.findViewById(R.id.tv_qi);
		dl.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
		dl.tv_category = (TextView) convertView.findViewById(R.id.tv_category);
		dl.tv_distance = (TextView) convertView.findViewById(R.id.tv_distance);
		
		return convertView;
	}
	
	private class DataList{
		public TextView tv_shangjia_name,//商家名称
						tv_youhuijia,//优惠价
						tv_price,//价格
						tv_qi,//起
						tv_category,//类别
						tv_address,//商家地址
						tv_distance;//距离
		
		public ImageView iv_shangjia_image,//团
						 iv_name;//商家图片
		
	}

}
