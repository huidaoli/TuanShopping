package com.xiang.tuanshopping.util;


import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 向服务端发送请求,得到请求的数据
 * @author Administrator
 *
 */
public class HttpUtils {
	public static String download(String uri) throws ClientProtocolException, IOException {
		String result = null;
		//HttpClient
		HttpClient client = new DefaultHttpClient();
		//HttpPost
		HttpPost post = new HttpPost(uri);
		//excute
		HttpResponse response = client.execute(post);
		
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			//获取entity
			result = EntityUtils.toString(response.getEntity());
		}
		
		return result;
	}
	
}
