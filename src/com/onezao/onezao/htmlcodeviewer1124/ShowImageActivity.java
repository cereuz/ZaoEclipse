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
				         Toast.makeText(getApplicationContext(), "�������ȡ����", Toast.LENGTH_SHORT).show();
						break;
						
					case SERVER_ERROR:
						Toast.makeText(ShowImageActivity.this, "�����������쳣", Toast.LENGTH_SHORT).show();
						break;

					case NETWORK_ERROR:
						Toast.makeText(ShowImageActivity.this, "���������쳣", Toast.LENGTH_SHORT).show();			
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
				//��׿4.0֮���������뿪���߳�     
				new Thread(new  Runnable() {
						@Override
						public void run() {
							try {
								//��ȡurl
								String  path  =  et_url.getText().toString().trim();
								URL  url  =  new  URL(path);
								//��ȡHttpUrlConnection
								HttpURLConnection connection = (HttpURLConnection) url.openConnection();
								connection.setRequestMethod("GET");  //��������ʽ
								connection.setConnectTimeout(HtmlCodeUtils.timeout);  //���ó�ʱʱ��
								int code = connection.getResponseCode();  //��ȡ��Ӧ��
								if(code ==200){   //���ӳɹ�
									//��ȡ��
									InputStream  inputStream  =  connection.getInputStream();
									Bitmap  bitmap  =  BitmapFactory.decodeStream(inputStream);
									//����ͼƬ��imageView����Ҫ������Ϣ�����̲߳�����
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
			
			//����  ���ص�ͼƬ��
			private void showPicCache() {
				final File  file =  new File(getCacheDir() , "zao.png");
				if(file  !=  null  &&  file.length() > 0){
					Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
					iv_iamge.setImageBitmap(bm);
					Toast.makeText(this, "ʹ�û����ͼƬ", Toast.LENGTH_SHORT).show();
					return;
				}
				//��׿4.0֮���������뿪���߳�     
				new Thread(new  Runnable() {
					@Override
					public void run() {
						try {
							//��ȡurl
							String  path  =  et_url.getText().toString().trim();
							URL  url  =  new  URL(path);
							//��ȡHttpUrlConnection
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							connection.setRequestMethod("GET");  //��������ʽ
							connection.setConnectTimeout(HtmlCodeUtils.timeout);  //���ó�ʱʱ��
							int code = connection.getResponseCode();  //��ȡ��Ӧ��
							if(code ==200){   //���ӳɹ�
								//��ȡ��
								InputStream  inputStream  =  connection.getInputStream();
								//����ͼƬ
								FileOutputStream fos = new FileOutputStream(file);
								int len =  -1;
								byte buffer[] = new byte[1024];
								while((len = inputStream.read(buffer)) != -1){
									fos.write(buffer,0,len);
								}
								fos.close();
								inputStream.close();
								//���Ǵ�������ص���ͼƬ��ʱ�򣬸���ͼƬ���ڴ濨��
								ZaoUtils.copyFile(file.getAbsolutePath(), Environment.getExternalStorageDirectory().getPath()+"/Z"+ZaoUtils.getSystemTime()+".png");
							//	Bitmap  bitmap  =  BitmapFactory.decodeStream(inputStream);
							    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
								//����ͼƬ��imageView����Ҫ������Ϣ�����̲߳�����
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
				AdminUtils.AdminMenu(ShowImageActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
