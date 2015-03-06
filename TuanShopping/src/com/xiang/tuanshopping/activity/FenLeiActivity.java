package com.xiang.tuanshopping.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.xiang.tuanshopping.R;
import com.xiang.tuanshopping.adapter.FenLeiAdapter;
import com.xiang.tuanshopping.adapter.FenLeiAdapter2;
import com.xiang.tuanshopping.adapter.FenLeiAdapter3;
import com.xiang.tuanshopping.adapter.FenLeiAdapter4;
import com.xiang.tuanshopping.adapter.FenLeiAdapter5;
import com.xiang.tuanshopping.adapter.FenLeiAdapter6;
import com.xiang.tuanshopping.adapter.FenLeiAdapter7;

public class FenLeiActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classify);

		GridView gv_content = (GridView) findViewById(R.id.gv_content);
		GridView gv_content2 = (GridView) findViewById(R.id.gv_content2);
		GridView gv_content3 = (GridView) findViewById(R.id.gv_content3);
		GridView gv_content4 = (GridView) findViewById(R.id.gv_content4);
		GridView gv_content5 = (GridView) findViewById(R.id.gv_content5);
		GridView gv_content6 = (GridView) findViewById(R.id.gv_content6);
		GridView gv_content7 = (GridView) findViewById(R.id.gv_content7);
		
		FenLeiAdapter adapter = new FenLeiAdapter(this);
		FenLeiAdapter2 adapter2 = new FenLeiAdapter2(this);
		FenLeiAdapter3 adapter3 = new FenLeiAdapter3(this);
		FenLeiAdapter4 adapter4 = new FenLeiAdapter4(this);
		FenLeiAdapter5 adapter5 = new FenLeiAdapter5(this);
		FenLeiAdapter6 adapter6 = new FenLeiAdapter6(this);
		FenLeiAdapter7 adapter7 = new FenLeiAdapter7(this);
		
		gv_content.setVerticalSpacing(10);
		gv_content2.setVerticalSpacing(10);
		gv_content3.setVerticalSpacing(10);
		gv_content4.setVerticalSpacing(10);
		gv_content5.setVerticalSpacing(10);
		gv_content6.setVerticalSpacing(10);
		gv_content7.setVerticalSpacing(10);
		
		gv_content.setAdapter(adapter);
		gv_content2.setAdapter(adapter2);
		gv_content3.setAdapter(adapter3);
		gv_content4.setAdapter(adapter4);
		gv_content5.setAdapter(adapter5);
		gv_content6.setAdapter(adapter6);
		gv_content7.setAdapter(adapter7);
	}

}
