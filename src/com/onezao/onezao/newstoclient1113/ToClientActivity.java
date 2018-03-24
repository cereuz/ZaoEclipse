package com.onezao.onezao.newstoclient1113;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ToClientActivity extends Activity {
	private List<News> newsLists;
	private ListView lv_newstoclient_listview;
	public static Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
		       switch (msg.what) {
			case 1:
				
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newstoclient_view_1113);
		lv_newstoclient_listview = (ListView) findViewById(R.id.lv_newstoclient_listview);
		//[2] 准备listview 要显示的数据，去服务器去数据，进行封装
		initListData();		
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
	}
     //准备ListView的数据
	private void initListData() {
			new  Thread(new Runnable() {
			public void run() {
				   try {
		    		   //[2.1] 去服务器去数据    http://192.168.11.86:8080/news.xml
		    		   String path = "http://193.168.1.103:8080/news.xml";
		    		   //[2.2] 创建 URL对象指定我们要访问的网址(路径)
		    		   URL  url = new  URL(path);
		    		   Toast.makeText(ToClientActivity.this, "URL  url = new  URL(path);", Toast.LENGTH_SHORT).show();
		    		   //[2.3] 拿到httpurlconnection 对象，用于发送或者接受数据。
		    		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    		   //[2.4] 设置发送get请求
		    		   conn.setRequestMethod("GET"); // get要求大写，默认就是GET请求
		    		   // [2.5] 设置请求超时时间
		    		   conn.setConnectTimeout(5000);
		    		   // [2.6] 获取服务器返回的状态码
		    		   int code = conn.getResponseCode();
		    		   // [2.7] 如果code==200 说明请求成功
		    		   if(code == 200){
		    			   //[2.8] 获取服务器返回的数据，是以流的形式返回的，由于把流转换成字符串是一个非常常见的操作，所以我抽出一个工具类(utils)
		    			   InputStream  in = conn.getInputStream();
		    			   Toast.makeText(ToClientActivity.this, "initListData", Toast.LENGTH_SHORT).show();
		    			   newsLists = XmlParserUtils.parserXml(in);	
		    			 //[3] 更新UI，把数据展示到ListView上,  需要在主线程更新UI
		    			   runOnUiThread(new Runnable() {
							public void run() {
								lv_newstoclient_listview.setAdapter(new MyAdapter());
								Toast.makeText(ToClientActivity.this, "setAdapter", Toast.LENGTH_SHORT).show();
							}
						});   			  
		    		   }
		    	   } catch (Exception e) {
		    		   e.printStackTrace();
		    	   }
			}
		}).start();
	}
	// 定义数据适配器
	private  class  MyAdapter  extends  BaseAdapter {

		@Override
		public int getCount() {
			return   newsLists.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view ;
			if(convertView == null){
				view = View.inflate(ToClientActivity.this,R.layout.newstoclient_item_1113,null);
			} else {
				view  =  convertView;
			}
			// [1] 找到控件，显示集合里面的数据
			MySmartImageView  iv_newstoclient_icon = (MySmartImageView) view.findViewById(R.id.iv_newstoclient_icon);
			TextView  tv_newstoclient_title = (TextView) view.findViewById(R.id.tv_newstoclient_title);
			TextView  tv_newstoclient_description = (TextView) view.findViewById(R.id.tv_newstoclient_description);
			TextView  tv_newstoclient_type = (TextView) view.findViewById(R.id.tv_newstoclient_type);
			//[1.1] 展示图片的数据
			String imageUrl = newsLists.get(position).getImage();
			iv_newstoclient_icon.setImageUrl(imageUrl,R.id.iv_newstoclient_icon);
			
			//[2] 显示数据
			tv_newstoclient_title.setText(newsLists.get(position).getTitle());
			tv_newstoclient_description.setText(newsLists.get(position).getDescription());
			String typee = newsLists.get(position).getType();
			int  type = Integer.parseInt(typee);
			switch (type) {
			case  1 :
				tv_newstoclient_type.setText("国内");
				break;
			case  2 :
				tv_newstoclient_type.setText("跟贴");
				break;
			case  3 :
				tv_newstoclient_type.setText("国外");
				break;
			}
			return view;
		}		
	}
	
}
