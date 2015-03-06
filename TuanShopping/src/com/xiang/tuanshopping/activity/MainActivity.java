package com.xiang.tuanshopping.activity;

import com.xiang.tuanshopping.R;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.TabActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity {

	private TabHost tabhost;
	private TabHost.TabSpec first;
	private TabHost.TabSpec second;
	private TabHost.TabSpec third;
	private TabHost.TabSpec fourth;

	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
		// 创建快捷方式
		// 如果已经创建，无需重复创建

		// creatShortCut();

	}

	// private void creatShortCut() {
	// if(isExit()){
	// return;
	// }
	// Intent intent = new Intent();
	// intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
	// //设置数据，快捷方式的名称，图标
	// intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "美团");
	// Parcelable icon = Intent.ShortcutIconResource.fromContext(this,
	// R.drawable.ic_launcher);
	// intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
	//
	// //点击图标启动哪个组件
	// Intent targetIntent = new Intent();
	// targetIntent.setClass(this, MainActivity.class);
	// intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, targetIntent);
	//
	// sendBroadcast(intent);
	//
	// Toast.makeText(this, "快捷方式已创建成功", Toast.LENGTH_LONG).show();
	// }
	//
	// private boolean isExit() {
	// // TODO Auto-generated method stub
	// ContentResolver resolver = getContentResolver();
	// Uri uri =
	// Uri.parse("content://com.android.launcher2.settings/favorites");
	// int sdk = Build.VERSION.SDK_INT;
	// if(sdk < 8){
	// uri = Uri.parse("content://com.android.launcher.settings/favorites");
	// }
	//
	// Cursor c = resolver.query(uri, null, " title = ? ", new String[]{"美团"},
	// null);
	// if(c.moveToNext()){
	// return true;
	// }
	// return false;
	// }

	private void init() {
		tabhost = this.getTabHost();

		first = tabhost.newTabSpec("first");
		second = tabhost.newTabSpec("second");
		third = tabhost.newTabSpec("third");
		fourth = tabhost.newTabSpec("fourth");
		// 指定选项卡上文字，图标
		/*
		 * first.setIndicator(createContent("团购",
		 * getResources().getDrawable(R.drawable.ic_menu_deal_on)));
		 * second.setIndicator(createContent("商家",
		 * getResources().getDrawable(R.drawable.ic_menu_poi_off)));
		 * third.setIndicator(createContent("我的",
		 * getResources().getDrawable(R.drawable.ic_menu_user_off)));
		 * fourth.setIndicator(createContent("更多",
		 * getResources().getDrawable(R.drawable.ic_menu_more_off)));
		 */

		first.setIndicator(createContent("团购", R.drawable.first_tab));
		second.setIndicator(createContent("商家", R.drawable.two_tab));
		third.setIndicator(createContent("我的", R.drawable.third_tab));
		fourth.setIndicator(createContent("更多", R.drawable.fours_tab));

		// 绑定显示的页面
		// first.setContent(R.id.ll_first);
		first.setContent(new Intent(this, TuangouActivity.class));
		second.setContent(new Intent(this, ShangjiaActivity.class));
		third.setContent(new Intent(this, MineActivity.class));
		fourth.setContent(new Intent(this, GengduoActivity.class));
		// 将选项卡加进TabHost
		tabhost.addTab(first);
		tabhost.addTab(second);
		tabhost.addTab(third);
		tabhost.addTab(fourth);
		tabhost.setCurrentTab(0);
		// 设置tabHost切换时动态更改图标
		tabhost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				tabChanged(tabId);
			}

		});
	}

	// 捕获tab变化事件
	private void tabChanged(String tabId) {
		// 当前选中项
		if (tabId.equals("first")) {
			tabhost.setCurrentTabByTag("团购");
		} else if (tabId.equals("second")) {
			tabhost.setCurrentTabByTag("商家");
		} else if (tabId.equals("third")) {
			tabhost.setCurrentTabByTag("我的");
		} else if (tabId.equals("fourth")) {
			tabhost.setCurrentTabByTag("更多");
		}

	}

	/*
	 * // 设置选项卡上的布局内容 private View createContent(String text, Drawable drawable)
	 * { View view = LayoutInflater.from(this).inflate(R.layout.tabwidget, null,
	 * false); ImageView img_name = (ImageView)
	 * view.findViewById(R.id.img_name); TextView tv_name = (TextView)
	 * view.findViewById(R.id.tv_name); img_name.setImageDrawable(drawable);
	 * tv_name.setText(text); return view; }
	 */

	// 返回单个选项
	private View createContent(String text, int resid) {
		View view = LayoutInflater.from(this).inflate(R.layout.tabwidget, null,
				false);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		ImageView iv_icon = (ImageView) view.findViewById(R.id.img_name);
		tv_name.setText(text);
		iv_icon.setBackgroundResource(resid);
		return view;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}

	}
}
