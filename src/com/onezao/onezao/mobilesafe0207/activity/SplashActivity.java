package com.onezao.onezao.mobilesafe0207.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.onezao.onezao.listview1121.SimpleAdapterActivity;
import com.onezao.onezao.mobilesafe0207.utils.StreamUtil;
import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {
	      protected static  final  String  tag  =  "SplashActivity";
		/**
		 * �����°汾
		 */
		protected static final int UPDATE_VERSION = 100;
		/**
		 * ����������
		 */
		protected static final int ENTER_HOME = 101;
		/**
		 *   �Ѻ���ʾ��Ϣ
		 */
		protected static final int SHOW_TOAST = 102;
		/*
		 * URL����IO ����   ��JSON����
		 */
		protected static final int URL_ERROR = 103;
		protected static final int IO_ERROR = 104;
		protected static final int JSON_ERROR = 1050;
           private TextView tv_splash_0207;
		   private int mLocalVersionCode;
		private EditText et_splash_0207;
		
		private String versionName;
		private String versionDes;
		private String downloadUrl;
		private String versionCode;

		private Handler  mHandler  = new Handler(){
			public void handleMessage(android.os.Message msg) {
				    switch (msg.what) {
					case UPDATE_VERSION:
						//����json�ļ��ɹ���������ʾ��
						et_splash_0207.setText(downloadUrl);
						//�������ѿ�
						showDownLoad(versionName,versionDes);
						break;
						
					case ENTER_HOME:
						//�����û�����������
						Intent  intent = new Intent(SplashActivity.this,SimpleAdapterActivity.class);
						startActivity(intent);
						//�ڿ���һ���µĽ���󣬽���������رգ���������ֻ�ɼ�һ�Σ�
						finish();
						break;
						
					case SHOW_TOAST:
                        Toast.makeText(getApplicationContext(), "�Ѿ������°汾Ŷ��", Toast.LENGTH_SHORT).show();
						EnterHome();
                        break;
					case URL_ERROR:
						Toast.makeText(getApplicationContext(), "���ص�ַ����", Toast.LENGTH_SHORT).show();
						EnterHome();
						break;
					case IO_ERROR:
						Toast.makeText(getApplicationContext(), "��д�ļ�����", Toast.LENGTH_SHORT).show();
						EnterHome();
						break;
					case JSON_ERROR:
						Toast.makeText(getApplicationContext(), "��������", Toast.LENGTH_SHORT).show();
						EnterHome();
						break;
					}
			}

			private void EnterHome() {
				//������ҳ
				Intent  intentN = new Intent(SplashActivity.this,HomeActivity.class);
				startActivity(intentN);
			};
		};
		private RelativeLayout rl_root;
		
		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	//ȥ����ǰActivity��title��ע�⣺������setContentView֮ǰָ�������
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	setContentView(R.layout.activity_splash_0207);
        	
        	//��ʼ��UI
        	initUI();
        	//��ʼ������
        	initData();
        	//��ʼ������
        	initAnimation();
        }

        //�ֶ���ѯ���¡�
		public void update(View view){
			showDownLoad(versionName, versionDes);
		}
		
           
		/**
		 * ��ӵ��붯��Ч��
		 */
		private void initAnimation() {
			AlphaAnimation alphaAnimation = new  AlphaAnimation(0, 1);
			alphaAnimation.setDuration(3000);
			rl_root.setAnimation(alphaAnimation);
			
		}

		/**
		 * ��ʼ������
		 */
		private void initData() {
			    String splash_url = et_splash_0207.getText().toString().trim();
                 //1.��ȡӦ�ð汾����
		     	String mVersionName = getPackageInfo().versionName;
		     	tv_splash_0207.setText("�汾���ƣ�"+mVersionName);
		     	//2.��⣨���ذ汾�źͷ������汾�űȶԣ��Ƿ��и��£�����и��£���ʾ�û����أ�member��
		     	mLocalVersionCode = getPackageInfo().versionCode;
		     	//3. ��ȡ�������汾�ţ��ͻ��˷����󣬷���˸���Ӧ����json��xml����
		     	//http://www.onezao.com/update36.json?key=value  �������200������ɹ���ͨ�����ķ�ʽ�����ݶ�ȡ����
		     	//json�а������ݣ�
		     	/*���°汾�İ汾����
		     	 * �°汾��������Ϣ
		     	 * �������汾��
		     	 * �°汾apk���ص�ַ
		     	 */
		     	checkVersion(splash_url);
		}
		
		/**
		 * ���汾��
		 */
		private void checkVersion(final String  splash_url) {
               new  Thread(){
				private Message msg;
				long startTime = System.currentTimeMillis();

				public void run() {
            		   //�������󣬻�ȡ���ݣ�����Ϊ����json�����ӵ�ַ
            		   try {
            			   //0.����������Ϣ�Ķ���
            			   msg = Message.obtain();
            			   
            			   //1.  ��װURL��ַ
						URL url = new  URL(splash_url);
//						URL url = new  URL("http://192.168.1.111:8080/zao36update.json");
						//2. ����һ������
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						//3. ���ó����������������ͷ��
						//����ʱ
						connection.setConnectTimeout(3000);
						//��ȡ��ʱ
//						connection.setReadTimeout(2000);
						//��������ʽ��Ĭ�Ͼ���get����ʽ
						connection.setRequestMethod("POST");
						//4. ��ȡ��Ӧ��     200����ɹ�
						if(connection.getResponseCode() == 200){
							//5. ��������ʽ�������ݻ�ȡ����
							InputStream is = connection.getInputStream();
							//6. ����ת�����ַ������������װ��
							final String json = StreamUtil.streamToString(is);
							Log.i(tag ,json);
				
							//7. json����
							JSONObject jsonObject = new  JSONObject(json);
							
							versionName = jsonObject.getString("versionName");
							versionDes = jsonObject.getString("versionDes");
							versionCode = jsonObject.getString("versionCode");
							downloadUrl = jsonObject.getString("downloadUrl");
							
							Log.i(tag,versionName);
							Log.i(tag,versionDes);
							Log.i(tag,versionCode);
							Log.i(tag,downloadUrl);
							
							//8.�ȶ԰汾�ţ��������汾��> ���ذ汾�ţ���ʾ�û����£�
							if(Integer.parseInt(versionCode) > mLocalVersionCode){
								//��ʾ�û�����
								msg.what = UPDATE_VERSION;
								//��Ϊ�е����޸�UI��������Ҫ��UI�̲߳�����
/*								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										//�������ѿ�
										showDownLoad(versionName,versionDes);
									}
								});*/
								
							}  else  if(Integer.parseInt(versionCode) == mLocalVersionCode){
								msg.what = SHOW_TOAST;
								//��ǰ�汾��������汾һ�£�����Ҫ���¡�
/*								runOnUiThread(new Runnable() {
									@Override
									public void run() {
                                          Toast.makeText(getApplicationContext(), "�Ѿ������°汾Ŷ��", Toast.LENGTH_SHORT).show();
									}
								});*/
								
							}  else {
								msg.what = ENTER_HOME;
								//����������
							}
							
						}
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
						msg.what = URL_ERROR;
					} catch (IOException e) {
						e.printStackTrace();
						msg.what = IO_ERROR;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						msg.what = JSON_ERROR;
					}  finally  {
/*						//ָ��˯��ʱ�䣬���������ʱ������4����������
						//���������ʱ��С��4�룬ǿ������˯����4����
						long endTime = System.currentTimeMillis();
						if(endTime - startTime < 4000){
							try {
								Thread.sleep(4000-(endTime - startTime));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}*/
						mHandler.sendMessage(msg);
					}
            	   };
               }.start();			
               
/*          new  Thread(new Runnable() {
				@Override
				public void run() {
                  					
				}
			});*/
		}


         //�������µ���ʾ��
		protected void showDownLoad(String  versionName,String versionDes) {
			final String positive = "����" ;
		    final String negative = "ȡ��";
			//�����ť������һ����ͨ�Ի���
			// ͨ��builder ������������
				AlertDialog.Builder   builder =  new  Builder(this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle(versionName+"  ���������ˣ�");
				builder.setMessage(versionDes);
				builder.setPositiveButton(positive, new OnClickListener() {			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("�����ȷ����ť");
						Toast.makeText(SplashActivity.this, positive, Toast.LENGTH_SHORT).show();
//						tv_mydialog_textview.setText(positive);
						//��ת����������
/*						Intent  intent = new Intent(SplashActivity.this,SimpleAdapterActivity.class);
						startActivity(intent);*/
						//����APK��APK���ӵ�ַ��downLoadUrl
						downloadApk();						
					}
				});
				builder.setNegativeButton(negative, new OnClickListener() {			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("�����ȡ����ť");
						Toast.makeText(SplashActivity.this, negative, Toast.LENGTH_SHORT).show();
						//������ҳ
						Intent  intent = new Intent(SplashActivity.this,HomeActivity.class);
						startActivity(intent);
//						tv_mydialog_textview.setText(negative);
					}
				});
				
				//��� ���ذ�ť���û�
				builder.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface arg0) {
						//������ҳ
						Intent  intent = new Intent(SplashActivity.this,HomeActivity.class);
						startActivity(intent);
					}
				});
				//���һ����һ��Ҫ�ǵú�Toastһ����show������
				builder.show();
			}			

             //���ذ�װ��
		protected void downloadApk() {
               //1. APK���ص�ַ��  2.  ����APK��·��
			//1.�ж�SD���Ƿ���ã��Ƿ������
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				//2. ��ȡSD��·��
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator + "zao0207.apk";
		      //3.�������󣬻�ȡAPK�����ҷ��õ�ָ��·��
			HttpUtils   httpUtils  =   new  HttpUtils();
			
			//����RequestCallBack<File>����
			RequestCallBack<File> callback =  new  RequestCallBack<File>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// ����ʧ��
					Log.i(tag, "����ʧ��");
				}
				
				@Override
				public void onStart() {
					// ��ʼ����
					Log.i(tag, "�ոտ�ʼ����");
					super.onStart();
				}
				
				@Override
				public void onLoading(long total, long current,
						boolean isUploading) {
					// �����С�����
					Log.i(tag, "�����С�����");
					Log.i(tag, "total = "+total);
					Log.i(tag, "current = "+current);
					super.onLoading(total, current, isUploading);
				}

				@Override
				public void onSuccess(ResponseInfo<File> responseInfo) {
					// ���سɹ������ع�����ļ�������SD����
					Log.i(tag, "���سɹ�");
					File  file = responseInfo.result;
					//��ʾ�û���װ
					installApk(file);
					
				}
			};
			
			//4.�������ط���
			httpUtils.download(downloadUrl, sdPath, callback );
			}
		}

		/**
		 * ��ʾ�û���װAPK
		 * @param file
		 */
		protected void installApk(File  file) {
              //ϵͳӦ�ý��棬Դ�룬��װAPK���
			Intent  intent  =  new  Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
/*			//�ļ���Ϊ����Դ
			intent.setData(Uri.fromFile(file));
			//�����ļ�����
			intent.setType("application/vnd.android.package-archive");*/
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
			startActivity(intent);
		}

		/**
		 * ��ȡӦ�ð����󣬴��嵥�ļ��л�ȡ
		 * @return  Ӧ�ð����󣬷���null�����쳣
		 */
		private PackageInfo  getPackageInfo() {
            //1.��ȡ�������߶���	packageManager
			PackageManager pm = getPackageManager();
			//2.�Ӱ������߶����л�ȡָ�������Ļ�����Ϣ���汾���ƣ��汾�ţ�����0���������Ϣ
			try {
				PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
				//3.��ȡ�汾����
				String versionName = packageInfo.versionName;
				int versionCode = packageInfo.versionCode;
				return  packageInfo;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    return  null;
		}



		/**
		 * ��ʼ��UI
		 */
		private void initUI() {
                    tv_splash_0207 = (TextView) findViewById(R.id.tv_splash_0207);
                    et_splash_0207 = (EditText) findViewById(R.id.et_splash_0207);
                    rl_root = (RelativeLayout) findViewById(R.id.rl_root);
		}
}
