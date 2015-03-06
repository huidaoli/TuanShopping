package com.xiang.tuanshopping.util;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.xiang.tuanshopping.activity.TuangouActivity;
import com.xiang.tuanshopping.bean.Merchs;

public class SelectData {
	
	/**
	 * 查询猜你喜欢的数据
	 * 
	 */
	public List<Merchs> getAll(){
		List<Merchs> list = new ArrayList<Merchs>();
		list = TuanJsonParser.parse(TuangouActivity.path);
		Log.i("tjx", "所有数据："+list.size());
		return list;
	}

}
