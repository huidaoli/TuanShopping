package com.xiang.tuanshopping.application;

import java.util.ArrayList;
import java.util.List;

import com.xiang.tuanshopping.activity.TuangouActivity;
import com.xiang.tuanshopping.adapter.ImageListAdapter;
import com.xiang.tuanshopping.bean.Merchs;
import com.xiang.tuanshopping.util.TuanJsonParser;
import com.xiang.tuanshopping.util.Utility;

import android.app.Application;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
/**
 * 商家，商品数据
 * @author Administrator
 *
 */
public class DataApplication extends Application {
	
	private List<Merchs> merchsList; //获取到的所有的商品信息
	

	public List<Merchs> getMerchsList() {
		return merchsList;
	}

	public void setMerchsList(List<Merchs> merchsList) {
		this.merchsList = merchsList;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//获取所有商品的信息
		merchsList = TuanJsonParser.parse(TuangouActivity.path);
		setMerchsList(merchsList);
	}

}
