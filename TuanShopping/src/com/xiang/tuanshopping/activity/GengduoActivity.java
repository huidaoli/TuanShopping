package com.xiang.tuanshopping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.xiang.tuanshopping.R;
import com.xiang.tuanshopping.adapter.GengduoAdapter;

public class GengduoActivity extends Activity implements OnItemClickListener{
	private ListView lv;
	private ImageView iv;
	private GengduoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourth);
		lv = (ListView) findViewById(R.id.lv_tasks);
		adapter = new GengduoAdapter(this);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position == 2) {
			Log.d("geek", "µ±Ç°item"+position);
			
			iv=(ImageView) view.findViewById(R.id.iv_iv1);
			iv.setImageResource(R.drawable.bg_settings_drag_on);
			//iv.setImageDrawable(R.drawable.bg_settings_drag_on);
			Log.d("geek", "1111");
			adapter.notifyDataSetChanged();
		}
		
	}
	

}
