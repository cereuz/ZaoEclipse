package com.onezao.onezao.htmlcodeviewer1124;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.onezao.onezao.dialer1113.DialerActivity0004;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HtmlCodeHandlerActivity extends Activity  implements  OnClickListener{
        private EditText et_url;
		private Button btn_show;
		private Button btn_show_cache;
		private TextView tv_code;
		//在主线程创建一个handler，这个handler的消息就会发送到主线程的消息队列中。
		private Handler  handler = new Handler(){
			   public void handleMessage(android.os.Message msg) {
				   // 可以通过handler在主线程中处理消息，具体是在handleMessage方法中处理消息。
				 String  temp = (String) msg.obj;
				 tv_code.setText(temp);
				 Toast.makeText(getApplicationContext(), "使用联网数据显示xml文档", Toast.LENGTH_SHORT).show();
			   };
		};
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_htmlcode_1124);
        	et_url = (EditText) findViewById(R.id.et_url);
        	btn_show = (Button) findViewById(R.id.btn_show);
        	btn_show_cache = (Button) findViewById(R.id.btn_show_cache);
        	tv_code = (TextView) findViewById(R.id.tv_code);
        	
        	et_url.setText("https://zhihu.com/rss");
        	btn_show.setOnClickListener(this);
        	btn_show_cache.setOnClickListener(this);        	
        }

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_show:
				htmlCodeHandler();
				break;

			case R.id.btn_show_cache:
				htmlCodeHandlerCache();
				break;
			}
			  
		}

	

		//【直接加载内容】
		private void htmlCodeHandler() {
			//**********联网必须要开子线程**************
			new Thread(new Runnable() {					
					@Override
			     public void run() {
	             //获取URL
				String path = et_url.getText().toString();	
				try {
					URL url = new URL(path);				
				    //拿着URL联网
					HttpURLConnection   urlConnection =(HttpURLConnection)url.openConnection();
					//设置请求方式，需要大写，默认是GET请求
					urlConnection.setRequestMethod("GET");
					//设置连接超时的时间
					urlConnection.setConnectTimeout(10000);
					//获取响应码   , 判断响应码，如果是200，就表示获取成功
					int responseCode = urlConnection.getResponseCode();
					if(responseCode==200){
						//联网后获得响应内容
						InputStream inputStream = urlConnection.getInputStream();
	                    String result = HtmlCodeUtils.getStringFromStream(inputStream);
	                    //不能直接修改界面，而是要通知主线程，获取到了数据，并且把数据丢到主线程，在主线程中显示。
	                    //☆通过handler发送消息Message   把要更新界面用到的数据，通过Message  携带到handler，发送到消息队列，在主线程中排序，然后按序执行。
	                    Message  msg = new Message();  //创建消息对象
	                    msg.obj = result;     //携带数据
	                    handler.sendMessage(msg);//handler调用方法发送
	                    
/*						//************不能在非UI线程修改View    !!**************
	                    //通过TextView   展示相应的内容
	                    tv_code.setText(result);*/
	           	}
				}    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
				}
			}).start();
		}
		
		//【缓存文件内容】
		private void htmlCodeHandlerCache() {
			final File  file  =  new File(getCacheDir() + "zao.xml");
			if(file.exists() && file.length() > 0){
	                 String readFile = ZaoUtils.readFile(file.getAbsolutePath());
	                 tv_code.setText(readFile);
	                 Toast.makeText(this, "使用缓存的xml文件", Toast.LENGTH_SHORT).show();
			}
			//**********联网必须要开子线程**************
			new Thread(new Runnable() {					
					@Override
			     public void run() {
	             //获取URL
				String path = et_url.getText().toString();	
				try {
					URL url = new URL(path);				
				    //拿着URL联网
					HttpURLConnection   urlConnection =(HttpURLConnection)url.openConnection();
					//设置请求方式，需要大写，默认是GET请求
					urlConnection.setRequestMethod("GET");
					//设置连接超时的时间
					urlConnection.setConnectTimeout(10000);
					//获取响应码   , 判断响应码，如果是200，就表示获取成功
					int responseCode = urlConnection.getResponseCode();
					if(responseCode==200){
						//联网后获得响应内容
						InputStream inputStream = urlConnection.getInputStream();
						FileOutputStream   fos  =  new  FileOutputStream(file);
						byte[]  buffer  =  new byte[1024];
						int  len  =  -1;
						while((len = inputStream.read(buffer)) != -1 ){
							fos.write(buffer,0,len);
						}
						fos.close();
						inputStream.close();
						//复制文件，当是从网络加载的新文档的时候，复制文档到内存卡，
						ZaoUtils.copyFile(file.getAbsolutePath(), Environment.getExternalStorageDirectory().getPath()+"/Z"+ZaoUtils.getSystemTime()+".xml");
						
//	                    String result = HtmlCodeUtils.getStringFromStream(inputStream);
	                    String result = HtmlCodeUtils.getStringFromPath(path);
	                    //不能直接修改界面，而是要通知主线程，获取到了数据，并且把数据丢到主线程，在主线程中显示。
	                    //☆通过handler发送消息Message   把要更新界面用到的数据，通过Message  携带到handler，发送到消息队列，在主线程中排序，然后按序执行。
	                    Message  msg = new Message();  //创建消息对象
	                    msg.obj = result;     //携带数据
	                    handler.sendMessage(msg);//handler调用方法发送
	                    
/*						//************不能在非UI线程修改View    !!**************
	                    //通过TextView   展示相应的内容
	                    tv_code.setText(result);*/
	           	}
				}    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
				}
			}).start();			
		}
		
		
		//加载顶部菜单，添加菜单的点击事件。
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			  //设置左上角的图标的点击事件  ActionBar
		      ActionBar actionBar = this.getActionBar();
		     actionBar.setHomeButtonEnabled(true);
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.admin, menu);
			return true;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {

			AdminUtils.AdminMenu(HtmlCodeHandlerActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
} 
	
