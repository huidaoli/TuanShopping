package com.xiang.tuanshopping.adapter;

import com.xiang.tuanshopping.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class FenLeiAdapter7 extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context;
	private String [] content ={"全部","服装鞋帽","家居日用","食品饮料","箱包","母婴用品","化妆品","数码家电","钟表首饰","户外用品","图书音像","本地购物","其他购物"};
	public FenLeiAdapter7(Context context) {
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return content.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return content[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.classify_item, parent, false);
		Button btn_fenlei = (Button) layout.findViewById(R.id.btn_fenlei);
		btn_fenlei.setText(content[position]);
		return layout;
	}

}
