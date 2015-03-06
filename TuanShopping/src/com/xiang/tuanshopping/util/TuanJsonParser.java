package com.xiang.tuanshopping.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.xiang.tuanshopping.application.DataApplication;
import com.xiang.tuanshopping.bean.Merchs;

public class TuanJsonParser {
	
	/**
	 * 获取服务器Json数据，转为MeiTuanBean对象集合
	 * @param uri
	 * @return
	 */
	public static List<Merchs> parse(String uri) {
		List<Merchs> list = new ArrayList<Merchs>();
		try {
			String result = HttpUtils.download(uri);
			Log.d("geek", result);
			// 将json数组，转换成集合
			// 1.将字符串转成JSONArray
			JSONArray array = new JSONArray(result);
			Log.d("geek", "数据条数："+array.length());
			for (int i = 0; i < array.length(); i++) {
				// 2.拿到JSONArray中的单个JSONObject
				JSONObject jo = (JSONObject) array.get(i);
				Log.d("geek", "单个json对象："+jo.toString());
				// 3.拿到单个JSONObject中的属性
				Merchs merchs = new Merchs(jo.getInt("merchsid"),
						jo.getString("loc"),jo.getString("image"),
						jo.getString("range"),jo.getInt("category"),
						jo.getString("shorttitle"),jo.getString("describe"),
						jo.getString("price"),jo.getString("value"),
						jo.getString("bought"),jo.getString("grade"),
						jo.getString("gradecount"),jo.getString("date"),
						jo.getString("city"));
				Log.d("geek","单个商品信息:"+merchs.toString());
				list.add(merchs);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
