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
		//[2] ׼��listview Ҫ��ʾ�����ݣ�ȥ������ȥ���ݣ����з�װ
		initListData();		
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
	}
     //׼��ListView������
	private void initListData() {
			new  Thread(new Runnable() {
			public void run() {
				   try {
		    		   //[2.1] ȥ������ȥ����    http://192.168.11.86:8080/news.xml
		    		   String path = "http://193.168.1.103:8080/news.xml";
		    		   //[2.2] ���� URL����ָ������Ҫ���ʵ���ַ(·��)
		    		   URL  url = new  URL(path);
		    		   Toast.makeText(ToClientActivity.this, "URL  url = new  URL(path);", Toast.LENGTH_SHORT).show();
		    		   //[2.3] �õ�httpurlconnection �������ڷ��ͻ��߽������ݡ�
		    		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    		   //[2.4] ���÷���get����
		    		   conn.setRequestMethod("GET"); // getҪ���д��Ĭ�Ͼ���GET����
		    		   // [2.5] ��������ʱʱ��
		    		   conn.setConnectTimeout(5000);
		    		   // [2.6] ��ȡ���������ص�״̬��
		    		   int code = conn.getResponseCode();
		    		   // [2.7] ���code==200 ˵������ɹ�
		    		   if(code == 200){
		    			   //[2.8] ��ȡ���������ص����ݣ�����������ʽ���صģ����ڰ���ת�����ַ�����һ���ǳ������Ĳ����������ҳ��һ��������(utils)
		    			   InputStream  in = conn.getInputStream();
		    			   Toast.makeText(ToClientActivity.this, "initListData", Toast.LENGTH_SHORT).show();
		    			   newsLists = XmlParserUtils.parserXml(in);	
		    			 //[3] ����UI��������չʾ��ListView��,  ��Ҫ�����̸߳���UI
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
	// ��������������
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
			// [1] �ҵ��ؼ�����ʾ�������������
			MySmartImageView  iv_newstoclient_icon = (MySmartImageView) view.findViewById(R.id.iv_newstoclient_icon);
			TextView  tv_newstoclient_title = (TextView) view.findViewById(R.id.tv_newstoclient_title);
			TextView  tv_newstoclient_description = (TextView) view.findViewById(R.id.tv_newstoclient_description);
			TextView  tv_newstoclient_type = (TextView) view.findViewById(R.id.tv_newstoclient_type);
			//[1.1] չʾͼƬ������
			String imageUrl = newsLists.get(position).getImage();
			iv_newstoclient_icon.setImageUrl(imageUrl,R.id.iv_newstoclient_icon);
			
			//[2] ��ʾ����
			tv_newstoclient_title.setText(newsLists.get(position).getTitle());
			tv_newstoclient_description.setText(newsLists.get(position).getDescription());
			String typee = newsLists.get(position).getType();
			int  type = Integer.parseInt(typee);
			switch (type) {
			case  1 :
				tv_newstoclient_type.setText("����");
				break;
			case  2 :
				tv_newstoclient_type.setText("����");
				break;
			case  3 :
				tv_newstoclient_type.setText("����");
				break;
			}
			return view;
		}		
	}
	
}
