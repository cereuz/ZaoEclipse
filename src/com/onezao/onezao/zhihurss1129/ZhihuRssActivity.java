package com.onezao.onezao.zhihurss1129;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.onezao.onezao.newstoclient1113.MySmartImageView;
import com.onezao.onezao.newstoclient1113.ToClientActivity;
import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhihuRssActivity extends Activity {
             private String PATH = "https://www.zhihu.com/rss";
			private int TIMEOUT =  10000 ;
			ArrayList<ZhihuBean> list  ;
			private ListView lv_rss_listview;			
            private  Handler  handler =  new  Handler(){
            	 public void handleMessage(android.os.Message msg) {
            		 switch (msg.what) {
					case 0:
						int rCode = msg.arg1;
						Toast.makeText(ZhihuRssActivity.this, String.valueOf(rCode), Toast.LENGTH_SHORT).show();
						break;

					default:
						break;
					}
            	 };
            };
			
			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_zhihurss_1129);
            	lv_rss_listview = (ListView) findViewById(R.id.lv_rss_listview);            	
            	initData();
            }

			public  void initData() {
				  new Thread(new Runnable() {					
					@Override
					public void run() {
						try {
							 
							//ͨ����ַ������ȡ����
							URL   url =  new URL(PATH );
							//ͨ��url�������
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							//��������ʽ
							connection.setRequestMethod("GET");
							//���ó�ʱʱ��
							connection.setConnectTimeout(TIMEOUT );
							//��ȡ���ص���Ӧ��
							int responseCode = connection.getResponseCode();
						    if(responseCode == 200){  //��Ӧ����200˵������ɹ�
						    	Message msg =  Message.obtain();
						    	msg.arg1 = 200;
						    	msg.what = 0 ;
						    	handler.sendMessage(msg);
						    	 //ͨ������������
						    	InputStream inputStream = connection.getInputStream();
						    	list = ZhihuXmlPullParserUtils.zhiHuPullParser(inputStream);
						    	runOnUiThread(new Runnable() {									
									@Override
									public void run() {
										lv_rss_listview.setAdapter(new MyAdapter());										
									}
								});
						    }						
						} catch (IOException e) {
							e.printStackTrace();
						}										
					}
				}).start();
			}
			
			
			// ��������������
			private  class  MyAdapter  extends  BaseAdapter {

				@Override
				public int getCount() {
					return   list.size();
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
						view = View.inflate(ZhihuRssActivity.this,R.layout.item_zhihurss_1129,null);
					} else {
						view  =  convertView;
					}
					// [1] �ҵ��ؼ�����ʾ�������������
					TextView  tv_rss_title = (TextView) view.findViewById(R.id.tv_rss_title);
					TextView  tv_rss_writer = (TextView) view.findViewById(R.id.tv_rss_writer);
/*					//[1.1] չʾͼƬ������
					String imageUrl = newsLists.get(position).getImage();
					iv_newstoclient_icon.setImageUrl(imageUrl,R.id.iv_newstoclient_icon);*/
					
					//[2] ��ʾ����
					tv_rss_title.setText(list.get(position).getTitle());
                   tv_rss_writer.setText(list.get(position).getLink());
                   
					return view;
				}		
			}
			
			//���ض����˵�����Ӳ˵��ĵ���¼���
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				  //�������Ͻǵ�ͼ��ĵ���¼�  ActionBar
			      ActionBar actionBar = this.getActionBar();
			     actionBar.setHomeButtonEnabled(true);
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.admin, menu);
				return true;
			}
			
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				AdminUtils.AdminMenu(ZhihuRssActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
}
