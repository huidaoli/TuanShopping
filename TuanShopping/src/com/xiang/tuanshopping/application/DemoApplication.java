package com.xiang.tuanshopping.application;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Application;
import android.content.Context;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;


public class DemoApplication extends Application {
	
	private static DemoApplication mInstance = null;
    public boolean m_bKeyRight = true;
    public BMapManager mBMapManager = null;
    
    
    public LocationClient mLocationClient = null;
	public GeofenceClient mGeofenceClient;
	private String mData;  
	public TextView mTv;
	public Vibrator mVibrator01;
	public static String TAG = "geek";

    public static final String strKey = "VcRgGVtBHrLS9mjySoHKeqUZ";
	
	@Override
    public void onCreate() {
	    super.onCreate();
		mInstance = this;
		initEngineManager(this);
		
		initLocSDK();
	}
	
	//初始化定位相关
	private void initLocSDK() {
		mLocationClient = new LocationClient( this );
		mLocationClient.setAK(strKey);
		mGeofenceClient = new GeofenceClient(this);
	}

	public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(strKey,new MyGeneralListener())) {
            Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                    "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
	}
	
	public static DemoApplication getInstance() {
		return mInstance;
	}
	
	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
    public static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
                    Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
        	//非零值表示key验证未通过
            if (iError != 0) {
                //授权Key错误：
                Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                        "请在 DemoApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "+iError, Toast.LENGTH_LONG).show();
                DemoApplication.getInstance().m_bKeyRight = false;
            }
            else{
            	DemoApplication.getInstance().m_bKeyRight = true;
            	Toast.makeText(DemoApplication.getInstance().getApplicationContext(), 
                        "key认证成功", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    
    private static Boolean isExit = false;  
    private static Boolean hasTask = false;  
    Timer tExit = new Timer();  
    TimerTask task = new TimerTask() {  
@Override  
        public void run() {  
            isExit = false;  
            hasTask = true;  
        }  
    };  
public boolean onKeyDown(int keyCode, KeyEvent event) {  
                // TODO Auto-generated method stub  
                if(keyCode == KeyEvent.KEYCODE_BACK){  
//                        System.out.println("user back down");  
                        if(isExit == false ) {  
                                isExit = true;  
                                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
                                if(!hasTask) {  
                                        tExit.schedule(task, 2000);  
                                }} else {  
                                                                              
      }  
//                                finish();  
                                System.exit(0);  
                        }  
                                          
                return false;  
        }  
}