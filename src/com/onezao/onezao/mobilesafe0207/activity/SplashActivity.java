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
		 * 更新新版本
		 */
		protected static final int UPDATE_VERSION = 100;
		/**
		 * 进入主界面
		 */
		protected static final int ENTER_HOME = 101;
		/**
		 *   友好提示信息
		 */
		protected static final int SHOW_TOAST = 102;
		/*
		 * URL错误，IO 错误   ，JSON错误
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
						//加载json文件成功，设置显示框
						et_splash_0207.setText(downloadUrl);
						//弹出提醒框
						showDownLoad(versionName,versionDes);
						break;
						
					case ENTER_HOME:
						//进入用户程序主界面
						Intent  intent = new Intent(SplashActivity.this,SimpleAdapterActivity.class);
						startActivity(intent);
						//在开启一个新的界面后，将导航界面关闭（导航界面只可见一次）
						finish();
						break;
						
					case SHOW_TOAST:
                        Toast.makeText(getApplicationContext(), "已经是最新版本哦！", Toast.LENGTH_SHORT).show();
						EnterHome();
                        break;
					case URL_ERROR:
						Toast.makeText(getApplicationContext(), "下载地址错误！", Toast.LENGTH_SHORT).show();
						EnterHome();
						break;
					case IO_ERROR:
						Toast.makeText(getApplicationContext(), "读写文件错误！", Toast.LENGTH_SHORT).show();
						EnterHome();
						break;
					case JSON_ERROR:
						Toast.makeText(getApplicationContext(), "解析错误！", Toast.LENGTH_SHORT).show();
						EnterHome();
						break;
					}
			}

			private void EnterHome() {
				//进入首页
				Intent  intentN = new Intent(SplashActivity.this,HomeActivity.class);
				startActivity(intentN);
			};
		};
		private RelativeLayout rl_root;
		
		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	//去除当前Activity的title，注意：必须在setContentView之前指定这个。
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	setContentView(R.layout.activity_splash_0207);
        	
        	//初始化UI
        	initUI();
        	//初始化数据
        	initData();
        	//初始化动画
        	initAnimation();
        }

        //手动查询更新。
		public void update(View view){
			showDownLoad(versionName, versionDes);
		}
		
           
		/**
		 * 添加淡入动画效果
		 */
		private void initAnimation() {
			AlphaAnimation alphaAnimation = new  AlphaAnimation(0, 1);
			alphaAnimation.setDuration(3000);
			rl_root.setAnimation(alphaAnimation);
			
		}

		/**
		 * 初始化数据
		 */
		private void initData() {
			    String splash_url = et_splash_0207.getText().toString().trim();
                 //1.获取应用版本名称
		     	String mVersionName = getPackageInfo().versionName;
		     	tv_splash_0207.setText("版本名称："+mVersionName);
		     	//2.检测（本地版本号和服务器版本号比对）是否有更新，如果有更新，提示用户下载（member）
		     	mLocalVersionCode = getPackageInfo().versionCode;
		     	//3. 获取服务器版本号（客户端发请求，服务端给响应，（json，xml））
		     	//http://www.onezao.com/update36.json?key=value  如果返回200，请求成功，通过流的方式将数据读取下来
		     	//json中包含内容：
		     	/*更新版本的版本名称
		     	 * 新版本的描述信息
		     	 * 服务器版本号
		     	 * 新版本apk下载地址
		     	 */
		     	checkVersion(splash_url);
		}
		
		/**
		 * 检测版本号
		 */
		private void checkVersion(final String  splash_url) {
               new  Thread(){
				private Message msg;
				long startTime = System.currentTimeMillis();

				public void run() {
            		   //发送请求，获取数据，参数为请求json的链接地址
            		   try {
            			   //0.创建发送消息的对象
            			   msg = Message.obtain();
            			   
            			   //1.  封装URL地址
						URL url = new  URL(splash_url);
//						URL url = new  URL("http://192.168.1.111:8080/zao36update.json");
						//2. 开启一个链接
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						//3. 设置常见请求参数（请求头）
						//请求超时
						connection.setConnectTimeout(3000);
						//读取超时
//						connection.setReadTimeout(2000);
						//设置请求方式，默认就是get请求方式
						connection.setRequestMethod("POST");
						//4. 获取响应码     200代表成功
						if(connection.getResponseCode() == 200){
							//5. 以流的形式，将数据获取下来
							InputStream is = connection.getInputStream();
							//6. 将流转换成字符串（工具类封装）
							final String json = StreamUtil.streamToString(is);
							Log.i(tag ,json);
				
							//7. json解析
							JSONObject jsonObject = new  JSONObject(json);
							
							versionName = jsonObject.getString("versionName");
							versionDes = jsonObject.getString("versionDes");
							versionCode = jsonObject.getString("versionCode");
							downloadUrl = jsonObject.getString("downloadUrl");
							
							Log.i(tag,versionName);
							Log.i(tag,versionDes);
							Log.i(tag,versionCode);
							Log.i(tag,downloadUrl);
							
							//8.比对版本号（服务器版本号> 本地版本号，提示用户更新）
							if(Integer.parseInt(versionCode) > mLocalVersionCode){
								//提示用户更新
								msg.what = UPDATE_VERSION;
								//因为有弹框，修改UI，所以需要在UI线程操作。
/*								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										//弹出提醒框
										showDownLoad(versionName,versionDes);
									}
								});*/
								
							}  else  if(Integer.parseInt(versionCode) == mLocalVersionCode){
								msg.what = SHOW_TOAST;
								//当前版本与服务器版本一致，不需要更新。
/*								runOnUiThread(new Runnable() {
									@Override
									public void run() {
                                          Toast.makeText(getApplicationContext(), "已经是最新版本哦！", Toast.LENGTH_SHORT).show();
									}
								});*/
								
							}  else {
								msg.what = ENTER_HOME;
								//进入主界面
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
/*						//指定睡眠时间，请求网络的时长超过4秒则不作处理
						//请求网络的时长小于4秒，强制让其睡眠满4秒钟
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


         //弹出更新的提示框
		protected void showDownLoad(String  versionName,String versionDes) {
			final String positive = "更新" ;
		    final String negative = "取消";
			//点击按钮，弹出一个普通对话框
			// 通过builder 构建器来构造
				AlertDialog.Builder   builder =  new  Builder(this);
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle(versionName+"  可以下载了！");
				builder.setMessage(versionDes);
				builder.setPositiveButton(positive, new OnClickListener() {			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("点击了确定按钮");
						Toast.makeText(SplashActivity.this, positive, Toast.LENGTH_SHORT).show();
//						tv_mydialog_textview.setText(positive);
						//跳转到其他界面
/*						Intent  intent = new Intent(SplashActivity.this,SimpleAdapterActivity.class);
						startActivity(intent);*/
						//下载APK，APK链接地址，downLoadUrl
						downloadApk();						
					}
				});
				builder.setNegativeButton(negative, new OnClickListener() {			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						System.out.println("点击了取消按钮");
						Toast.makeText(SplashActivity.this, negative, Toast.LENGTH_SHORT).show();
						//进入首页
						Intent  intent = new Intent(SplashActivity.this,HomeActivity.class);
						startActivity(intent);
//						tv_mydialog_textview.setText(negative);
					}
				});
				
				//点击 返回按钮的用户
				builder.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface arg0) {
						//进入首页
						Intent  intent = new Intent(SplashActivity.this,HomeActivity.class);
						startActivity(intent);
					}
				});
				//最后一步，一定要记得和Toast一样，show出来。
				builder.show();
			}			

             //下载安装包
		protected void downloadApk() {
               //1. APK下载地址，  2.  放置APK的路径
			//1.判断SD卡是否可用，是否挂载上
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				//2. 获取SD卡路径
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator + "zao0207.apk";
		      //3.发送请求，获取APK，并且放置到指定路径
			HttpUtils   httpUtils  =   new  HttpUtils();
			
			//定义RequestCallBack<File>变量
			RequestCallBack<File> callback =  new  RequestCallBack<File>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// 下载失败
					Log.i(tag, "下载失败");
				}
				
				@Override
				public void onStart() {
					// 开始下载
					Log.i(tag, "刚刚开始下载");
					super.onStart();
				}
				
				@Override
				public void onLoading(long total, long current,
						boolean isUploading) {
					// 下载中。。。
					Log.i(tag, "下载中。。。");
					Log.i(tag, "total = "+total);
					Log.i(tag, "current = "+current);
					super.onLoading(total, current, isUploading);
				}

				@Override
				public void onSuccess(ResponseInfo<File> responseInfo) {
					// 下载成功，下载过后的文件放置在SD卡中
					Log.i(tag, "下载成功");
					File  file = responseInfo.result;
					//提示用户安装
					installApk(file);
					
				}
			};
			
			//4.调用下载方法
			httpUtils.download(downloadUrl, sdPath, callback );
			}
		}

		/**
		 * 提示用户安装APK
		 * @param file
		 */
		protected void installApk(File  file) {
              //系统应用界面，源码，安装APK入口
			Intent  intent  =  new  Intent("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
/*			//文件作为数据源
			intent.setData(Uri.fromFile(file));
			//设置文件类型
			intent.setType("application/vnd.android.package-archive");*/
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
			startActivity(intent);
		}

		/**
		 * 获取应用包对象，从清单文件中获取
		 * @return  应用包对象，返回null代表异常
		 */
		private PackageInfo  getPackageInfo() {
            //1.获取包管理者对象	packageManager
			PackageManager pm = getPackageManager();
			//2.从包管理者对象中获取指定包名的基本信息（版本名称，版本号），传0代表基本信息
			try {
				PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
				//3.获取版本名称
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
		 * 初始化UI
		 */
		private void initUI() {
                    tv_splash_0207 = (TextView) findViewById(R.id.tv_splash_0207);
                    et_splash_0207 = (EditText) findViewById(R.id.et_splash_0207);
                    rl_root = (RelativeLayout) findViewById(R.id.rl_root);
		}
}
