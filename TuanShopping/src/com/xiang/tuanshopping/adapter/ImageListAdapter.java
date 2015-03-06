package com.xiang.tuanshopping.adapter;

import java.util.List;

import com.ab.global.AbConstant;
import com.ab.net.AbImageDownloadCallback;
import com.ab.net.AbImageDownloadItem;
import com.ab.net.AbImageDownloadQueue;
import com.ab.util.AbFileUtil;
import com.ab.util.AbStrUtil;
import com.xiang.tuanshopping.R;
import com.xiang.tuanshopping.bean.Merchs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageListAdapter extends BaseAdapter {
	private Context context;
	//xml转View对象
    private LayoutInflater mInflater;
    //列表展现的数据
    private List<Merchs> mData;
    private AbImageDownloadQueue mAbImageDownloadQueue = null;
    
    public ImageListAdapter(Context context,List<Merchs> mData){
    	this.context=context;
    	this.mData=mData;
    	 //用于将xml转为View
        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mAbImageDownloadQueue = AbImageDownloadQueue.getInstance();
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		//优化listview
		if(convertView==null){
			//使用自定义的布局
			convertView=mInflater.inflate(R.layout.first_item,parent, false);
			holder=new ViewHolder();
			//初始化布局中的元素
			holder.bought=(TextView) convertView.findViewById(R.id.tv_bought);
			holder.itemsIcon=(ImageView) convertView.findViewById(R.id.itemsIcon);
			holder.name=(TextView) convertView.findViewById(R.id.tv_merchs_name);
			holder.price=(TextView) convertView.findViewById(R.id.tv_price);
			holder.range=(TextView) convertView.findViewById(R.id.tv_range);
			holder.shortTitle=(TextView) convertView.findViewById(R.id.tv_shorttitle);
			holder.value=(TextView) convertView.findViewById(R.id.tv_value);
			
			convertView.setTag(holder);
		}else{
			 holder = (ViewHolder) convertView.getTag();
		}
		//获取该行的数据
		final Merchs merch=(Merchs) mData.get(position);
		//拿到图片路径
		 String imageUrl =merch.getImage();
		 //设置其他数据
		 //已售
		 holder.bought.setText("已售"+merch.getBought());
		 holder.name.setText(merch.getDescribe());
		 //价格
		 holder.price.setText(merch.getPrice()+"元");
		 holder.price.setTextColor(Color.GREEN);
		 holder.price.setTextSize(16);
		 //地区
		 holder.range.setText("【"+merch.getRange()+"】");
		 //短标题
		 holder.shortTitle.setText(merch.getShorttitle());
		 //原价
		 holder.value.setText("原价"+merch.getValue());
		 holder.value.setTextSize(10);
		 holder.value.setTextColor(Color.GRAY);
		 holder.value.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中间横线
		 holder.value.getPaint().setAntiAlias(true);// 抗锯齿
		 
		 //异步加载图片
		 if(!AbStrUtil.isEmpty(imageUrl)){
			//设置下载项 
             AbImageDownloadItem item = new AbImageDownloadItem(); 
   	      //设置显示的大小
   	      item.width = 120;
   	      item.height = 120;
   	      //设置为缩放
   	      item.type = AbConstant.SCALEIMG;
   	      item.imageUrl = imageUrl;
   	     
             holder.itemsIcon.setImageBitmap(AbFileUtil.getBitmapFormSrc("image/image_loading.png"));
   	      //下载完成后更新界面
   	      item.callback = new AbImageDownloadCallback() { 
   	            @Override 
   	            public void update(Bitmap bitmap, String imageUrl) { 
   	            	if(bitmap!=null){
   	            		holder.itemsIcon.setImageBitmap(bitmap); 
   	            	}else{
   	            	    holder.itemsIcon.setImageBitmap(AbFileUtil.getBitmapFormSrc("image/image_error.png"));
   	            	}
   	            } 
   	      }; 
   	      mAbImageDownloadQueue.download(item); 
         }else{
       	  holder.itemsIcon.setImageBitmap(AbFileUtil.getBitmapFormSrc("image/image_no.png"));
         }
		return convertView;
	}

	/**
	 * 布局中的元素
	 */
	static class ViewHolder{
		ImageView itemsIcon;
		TextView shortTitle;
		TextView range;
		TextView name;
		TextView price;
		TextView value;
		TextView bought;
	}
}
