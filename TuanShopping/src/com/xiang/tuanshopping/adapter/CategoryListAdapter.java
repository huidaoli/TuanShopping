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

public class CategoryListAdapter extends BaseAdapter {

	
	private Context context;
	private ArrayList<HashMap<String,Object>> itemList;
	
	public CategoryListAdapter(Context context,ArrayList<HashMap<String,Object>> item){
		this.context = context;
		this.itemList = item;
	}
	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Datalist data = new Datalist();
		convertView = LayoutInflater.from(context).inflate(R.layout.category_item, null);
		data.mNameTextView =(TextView) convertView.findViewById(R.id.name);
		data.countTextView = (TextView) convertView.findViewById(R.id.count);
		data.mImage = (ImageView) convertView.findViewById(R.id.haschild);
		
		data.mNameTextView.setText(itemList.get(position).get("name").toString());
		data.countTextView.setText(itemList.get(position).get("count").toString());
		data.mImage.setVisibility(View.VISIBLE);
		return convertView;
	}
	private class Datalist{
		public TextView mNameTextView,countTextView;
		public ImageView mImage;
	}

}
