package com.onezao.onezao.htmlcodeviewer1124;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.onezao.onezao.dialer1113.DialerActivity0004;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowImageActivity extends Activity implements OnClickListener{
			private EditText et_url;
			private Button btn_show;
			private Button btn_show_cache;
			private ImageView iv_iamge;
			
			private final int GET_DATA_SUCCESS = 1;
			private final int SERVER_ERROR = 2;
			private final int NETWORK_ERROR = 3;
			
			public Handler  handler  =  new Handler(){
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case GET_DATA_SUCCESS:
				         Bitmap   bitmap  =  (Bitmap) msg.obj;    	
				         iv_iamge.setImageBitmap(bitmap);	
				         Toast.makeText(getApplicationContext(), "从网络获取数据", Toast.LENGTH_SHORT).show();
						break;
						
					case SERVER_ERROR:
						Toast.makeText(ShowImageActivity.this, "服务器返回异常", Toast.LENGTH_SHORT).show();
						break;

					case NETWORK_ERROR:
						Toast.makeText(ShowImageActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();			
						break;
					}
			         
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
            	iv_iamge = (ImageView) findViewById(R.id.iv_iamge);
            	
            	et_url.setText("https://img3.doubanio.com/view/photo/photo/public/p2294662303.webp");
            	btn_show.setOnClickListener(this);
            	btn_show_cache.setOnClickListener(this);
            }

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_show:
					  showPic();		
					break;

				case R.id.btn_show_cache:
					showPicCache();
					break;
				}
               		
			}

			private void showPic() {
				//安卓4.0之后联网必须开子线程     
				new Thread(new  Runnable() {
						@Override
						public void run() {
							try {
								//获取url
								String  path  =  et_url.getText().toString().trim();
								URL  url  =  new  URL(path);
								//获取HttpUrlConnection
								HttpURLConnection connection = (HttpURLConnection) url.openConnection();
								connection.setRequestMethod("GET");  //设置请求方式
								connection.setConnectTimeout(HtmlCodeUtils.timeout);  //设置超时时间
								int code = connection.getResponseCode();  //获取响应码
								if(code ==200){   //连接成功
									//获取流
									InputStream  inputStream  =  connection.getInputStream();
									Bitmap  bitmap  =  BitmapFactory.decodeStream(inputStream);
									//设置图片到imageView，需要发送消息到主线程操作。
									Message  msg  = Message.obtain();
									msg.what = GET_DATA_SUCCESS;
									msg.obj  =  bitmap;
									handler.sendMessage(msg);
								}  else  {
									Message  msg = Message.obtain();
									msg.what =SERVER_ERROR;
									handler.sendMessage(msg);
								}
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Message msg = Message.obtain();
								msg.what = NETWORK_ERROR;
								handler.sendMessage(msg);
							}
						}
					}).start();
			}
			
			//缓存  加载的图片。
			private void showPicCache() {
				final File  file =  new File(getCacheDir() , "zao.png");
				if(file  !=  null  &&  file.length() > 0){
					Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
					iv_iamge.setImageBitmap(bm);
					Toast.makeText(this, "使用缓存的图片", Toast.LENGTH_SHORT).show();
					return;
				}
				//安卓4.0之后联网必须开子线程     
				new Thread(new  Runnable() {
					@Override
					public void run() {
						try {
							//获取url
							String  path  =  et_url.getText().toString().trim();
							URL  url  =  new  URL(path);
							//获取HttpUrlConnection
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							connection.setRequestMethod("GET");  //设置请求方式
							connection.setConnectTimeout(HtmlCodeUtils.timeout);  //设置超时时间
							int code = connection.getResponseCode();  //获取响应码
							if(code ==200){   //连接成功
								//获取流
								InputStream  inputStream  =  connection.getInputStream();
								//保存图片
								FileOutputStream fos = new FileOutputStream(file);
								int len =  -1;
								byte buffer[] = new byte[1024];
								while((len = inputStream.read(buffer)) != -1){
									fos.write(buffer,0,len);
								}
								fos.close();
								inputStream.close();
								//当是从网络加载的新图片的时候，复制图片到内存卡，
								ZaoUtils.copyFile(file.getAbsolutePath(), Environment.getExternalStorageDirectory().getPath()+"/Z"+ZaoUtils.getSystemTime()+".png");
							//	Bitmap  bitmap  =  BitmapFactory.decodeStream(inputStream);
							    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
								//设置图片到imageView，需要发送消息到主线程操作。
								Message  msg  = Message.obtain();
								msg.what = GET_DATA_SUCCESS;
								msg.obj  =  bitmap;
								handler.sendMessage(msg);
							}  else  {
								Message  msg = Message.obtain();
								msg.what =SERVER_ERROR;
								handler.sendMessage(msg);
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Message msg = Message.obtain();
							msg.what = NETWORK_ERROR;
							handler.sendMessage(msg);
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
				AdminUtils.AdminMenu(ShowImageActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
