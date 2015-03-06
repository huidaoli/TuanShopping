package com.xiang.tuanshopping.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiang.tuanshopping.R;
import com.xiang.tuanshopping.adapter.ImageListAdapter;
import com.xiang.tuanshopping.adapter.TypeAdapter;
import com.xiang.tuanshopping.application.DataApplication;
import com.xiang.tuanshopping.bean.Merchs;
import com.xiang.tuanshopping.bean.Page;
import com.xiang.tuanshopping.util.PullToRefreshView;
import com.xiang.tuanshopping.util.PullToRefreshView.OnFooterRefreshListener;
import com.xiang.tuanshopping.util.PullToRefreshView.OnHeaderRefreshListener;
import com.xiang.tuanshopping.util.TuanJsonParser;
import com.xiang.tuanshopping.util.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

public class TuangouActivity extends Activity {
	private long exitTime = 0;

	private GridView gv_type;
	private ArrayList<Integer> types;
	private TypeAdapter typeAdapter;
	private static final int MESSAGE_INIT_DATA_SUCCESS = 1;

	// 美团开放API接口
	private final String URI = "";
	private ImageView iv_map;
	private ImageView iv_search;

	private DataApplication dataApp = null;
	// 商品信息
	private List<Merchs> merchsList = null;
	// 商品信息适配器
	private ImageListAdapter merchAdapter = null;
	// 请求数据地址
	public static final String path = "http://192.168.2.99:8080/TuanShoppingServer/mypack/merchsAction_getAllMerchsList";
	private TextView tv_merchs_info;
	private ListView lv_like_shop;

	private LayoutInflater inflater;
	private int headerHeight; // 头高度
	private int lastHeaderPadding; // 最后一次调用Move Header的Padding
	private boolean isBack; // 从Release 转到 pull
	private int headerState = DONE; // 头部状态
	static final private int RELEASE_To_REFRESH = 0; // 释放刷新:一直下拉屏幕时显示
	static final private int PULL_To_REFRESH = 1; // 正在刷新：放开屏幕后显示
	static final private int REFRESHING = 2; // 正在刷新
	static final private int DONE = 3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		initView();// 初始化各控件
		initCategory();// 初始化分类
		setListener();//给控件设置监听
		// 开启线程查询数据
		new Thread(new InitDataTask()).start();
	}

	private Handler handler = new Handler() {
		public void dispatchMessage(android.os.Message msg) {
			switch (msg.what) {
			// 初始化数据成功
			case MESSAGE_INIT_DATA_SUCCESS:
				// 绑定数据到适配器
				merchAdapter = new ImageListAdapter(TuangouActivity.this,
						merchsList);
				lv_like_shop.setAdapter(merchAdapter);
				// 加载完成，隐藏加载动画
				tv_merchs_info.setVisibility(View.GONE);
				Utility.setListViewHeightBasedOnChildren(lv_like_shop);
				break;
			}
		};
	};
	private ScrollView sc;
	private LinearLayout globleLayout;
	private LinearLayout header;
	private Animation anim;
	private ImageView iv_anim_first;
	private ImageView iv_header_fresh_anim;
	private TextView tv_text;
	private AnimationDrawable ad;

	/**
	 * 初始化各控件
	 */
	public void initView() {
		// 给头部导航的ImageView设置点击事件监听
		iv_map = (ImageView) findViewById(R.id.iv_map);
		iv_search = (ImageView)findViewById(R.id.iv_search);
		// 初始化中间商品信息控件
		tv_merchs_info = (TextView) findViewById(R.id.tv_load_info);
		lv_like_shop = (ListView) findViewById(R.id.lv_like_shop);

		// ScrollView
		sc = (ScrollView) findViewById(R.id.sv_first_sc);
		// 整体布局
		globleLayout = (LinearLayout) findViewById(R.id.globleLayout);
		// 布局加载器
		inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// 头部布局
		header = (LinearLayout) inflater.inflate(R.layout.first_header, null);
		tv_text = (TextView) header.findViewById(R.id.tv_first_refresh_text);
		iv_header_fresh_anim = (ImageView) header
				.findViewById(R.id.iv_header_anim);
		iv_header_fresh_anim.setBackgroundResource(R.drawable.frame);
		ad = (AnimationDrawable) iv_header_fresh_anim.getBackground();
		// 头部动画
		anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
		// 动画应用到的控件
		iv_anim_first = (ImageView) header.findViewById(R.id.iv_first_refresh);
		// 计算头部高度
		measureView(header);
		headerHeight = header.getMeasuredHeight();
		lastHeaderPadding = (-1 * headerHeight);
		header.setPadding(10, lastHeaderPadding, 0, 20);
		header.invalidate();
		// 添加头部布局
		globleLayout.addView(header, 0);
		anim.setFillAfter(true);// 动画结束后保持动画
		// 为ScrollView绑定监听
		sc.setOnTouchListener(new OnTouchListener() {
			private int beginY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					/**
					 * sc.getScrollY == 0 scrollview 滑动到头了 lastHeaderPadding >
					 * (-1*headerHeight) 表示header还没完全隐藏起来时 headerState !=
					 * REFRESHING正在刷新时
					 */
					if ((sc.getScrollY() == 0 || lastHeaderPadding > (-1 * headerHeight))
							&& headerState != REFRESHING) {
						// 拿到滑动的Y轴距离
						int interval = (int) (event.getY() - beginY);
						// 是向下滑动而不是向上滑动
						if (interval > 0) {
							interval = interval / 2;// 下滑阻力
							lastHeaderPadding = interval + (-1 * headerHeight);
							header.setPadding(10, lastHeaderPadding, 0, 20);
							if (lastHeaderPadding > 0) {
								// txView.setText("我要刷新咯");
								headerState = RELEASE_To_REFRESH;
								// 是否已经更新了UI
								if (!isBack) {
									isBack = true; // 到了Release状态，如果往回滑动到了pull则启动动画
									changeHeaderViewByState();
								}
							} else {
								headerState = PULL_To_REFRESH;
								changeHeaderViewByState();
								// txView.setText("看到我了哦");
								// sc.scrollTo(0, headerPadding);
							}
						}
					}
					break;
				case MotionEvent.ACTION_DOWN:
					// 加上下滑阻力与实际滑动距离的差（大概值）
					beginY = (int) ((int) event.getY() + sc.getScrollY() * 1.5);
					break;
				case MotionEvent.ACTION_UP:
					if (headerState != REFRESHING) {
						switch (headerState) {
						case DONE:
							// 什么也不干
							break;
						case PULL_To_REFRESH:
							headerState = DONE;
							lastHeaderPadding = -1 * headerHeight;
							header.setPadding(10, lastHeaderPadding, 0, 0);
							changeHeaderViewByState();
							break;
						case RELEASE_To_REFRESH:
							isBack = false; // 准备开始刷新，此时将不会往回滑动
							headerState = REFRESHING;
							changeHeaderViewByState();
							onRefresh();
							break;
						default:
							break;
						}
					}
					break;
				}
				// 如果Header是完全被隐藏的则让ScrollView正常滑动，让事件继续否则的话就阻断事件
				if (lastHeaderPadding > (-1 * headerHeight)
						&& headerState != REFRESHING) {
					return true;
				} else {
					return false;
				}

			}

		});
	}

	private void onRefresh() {
		new AsyncTask<Void, Void, Void>() {
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				onRefreshComplete();
			}

		}.execute(null);
	}

	public void onRefreshComplete() {
		headerState = DONE;
		changeHeaderViewByState();
	}

	/**
	 * 初始化分类
	 */
	public void initCategory() {
		gv_type = (GridView) findViewById(R.id.gv_type);

		types = new ArrayList<Integer>();
		types.add(R.drawable.ic_category_0);
		types.add(R.drawable.ic_category_1);
		types.add(R.drawable.ic_category_2);
		types.add(R.drawable.ic_category_3);
		types.add(R.drawable.ic_category_4);
		types.add(R.drawable.ic_category_5);
		types.add(R.drawable.ic_category_6);
		types.add(R.drawable.ic_category_7);

		typeAdapter = new TypeAdapter(types, this);

		gv_type.setAdapter(typeAdapter);
		gv_type.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Intent food = new Intent(TuangouActivity.this,
							FoodActivity.class);
					startActivity(food);
					break;
				case 1:
					Intent movie = new Intent(TuangouActivity.this,
							MovieActivity.class);
					startActivity(movie);
					break;
				case 2:
					Intent hotel = new Intent(TuangouActivity.this,
							HotelActivity.class);
					startActivity(hotel);
					break;
				case 3:
					Intent ktv = new Intent(TuangouActivity.this,
							KtvActivity.class);
					startActivity(ktv);
					break;
				case 4:
					Intent health = new Intent(TuangouActivity.this,
							HealthActivity.class);
					startActivity(health);
					break;
				case 5:
					Intent amusement = new Intent(TuangouActivity.this,
							AmusementActivity.class);
					startActivity(amusement);
					break;
				case 6:
					Intent today = new Intent(TuangouActivity.this,
							TodayActivity.class);
					startActivity(today);
					break;
				case 7:
					Intent all = new Intent(TuangouActivity.this,
							AllActivity.class);
					startActivity(all);
					break;

				default:
					break;
				}

			}
		});
	}

	/**
	 * 多线程查询商品数据
	 */
	class InitDataTask implements Runnable {
		@Override
		public void run() {
//			merchsList = new ArrayList<Merchs>();
			Log.d("geek", "开启线程");
			// 加载数据:接受服务端的数据
			merchsList = TuanJsonParser.parse(TuangouActivity.path);
			Log.d("geek", "大小：" + merchsList.size());
			// 查询完了将数据给适配器
			handler.sendEmptyMessage(MESSAGE_INIT_DATA_SUCCESS);
		}

	}

	/**
	 * 各控件的监听
	 */
	public void setListener() {
		iv_map.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				iv_map.setBackgroundColor(getResources().getColor(
						R.color.abs__background_holo_light));
				Intent intent = new Intent(TuangouActivity.this,
						MapActivity.class);
				startActivity(intent);
			}
		});
		iv_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	/**
	 * 拿到头部高度,onCreate里面得不到
	 */
	private void measureView(View childView) {
		LayoutParams p = childView.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int height = p.height;
		int childHeightSpec;
		if (height > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(height,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		childView.measure(childWidthSpec, childHeightSpec);
	}

	/**
	 * 通过状态来改变头部视图
	 */
	private void changeHeaderViewByState() {
		switch (headerState) {
		case PULL_To_REFRESH:
			// 是由RELEASE_To_REFRESH状态转变来的
			if (isBack) { // 向上送
				isBack = false;
				// 开启动画
				iv_anim_first.startAnimation(anim);
				ad.start();
				tv_text.setText("下拉刷新");
			}
			tv_text.setText("下拉刷新");
			break;
		case RELEASE_To_REFRESH: // 向下拖：这里只有右边的进度动画
			iv_anim_first.setVisibility(View.VISIBLE);
			iv_header_fresh_anim.setVisibility(View.VISIBLE);
			tv_text.setVisibility(View.VISIBLE);
			iv_anim_first.startAnimation(anim); // 右边的进度动画
			tv_text.setText("松手刷新");
			break;
		case REFRESHING:
			lastHeaderPadding = 0;
			header.setPadding(10, lastHeaderPadding, 0, 20);
			header.invalidate();
			iv_header_fresh_anim.setVisibility(View.VISIBLE);
			iv_anim_first.setVisibility(View.VISIBLE);
			tv_text.setText("载入中...");
			ad.start();
			break;
		case DONE: // 向上送
			lastHeaderPadding = -1 * headerHeight;
			header.setPadding(10, lastHeaderPadding, 0, 20);
			header.invalidate();
			iv_header_fresh_anim.setVisibility(View.GONE);
			tv_text.setText("下拉刷新");
			break;
		default:
			break;
		}
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
