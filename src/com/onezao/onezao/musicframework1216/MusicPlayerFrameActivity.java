package com.onezao.onezao.musicframework1216;

import com.onezao.onezao.musicframework1216.MusicPlayerFrameService.MyBinder;
import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MusicPlayerFrameActivity extends Activity {
	private MyBinder musicControl;
	private MyConnection conn;
               @Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_musicplayer_frame_1216);           	
   
            	  // 由于这个界面一返回，就会执行onDestroy，所以如果需要执行按钮的功能，必须先初始化一下。
            	   Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);
            	   conn = new  MyConnection();
            	   //通过bindService开启音乐播放服务Service
            	   bindService(service, conn, BIND_AUTO_CREATE);
            	   
            	   //混合方式开启服务
            	   startService(service);
            }
               
              //开启服务
               public void musicplayer_start(View view){
            	   Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);            	   
            	   //混合方式开启服务
            	   startService(service);
               }
               
               //绑定服务
               public void musicplayer_onbind(View view){
            		Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);
                	conn = new  MyConnection();
                	//通过bindService开启音乐播放服务Service
                	bindService(service, conn, BIND_AUTO_CREATE);
               }
               
               //混合开启服务
               public void musicplayer_mixstart(View view){
            	   Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);
            	   conn = new  MyConnection();
            	   //通过bindService开启音乐播放服务Service
            	   bindService(service, conn, BIND_AUTO_CREATE);
            	   
            	   //混合方式开启服务
            	   startService(service);
               }
               
               
               //播放音乐
               public  void  musicplayerframe_play(View  view){
            	     musicControl.callPlay();
               }
               //暂停音乐
               public  void  musicplayerframe_pause(View  view){
            	   musicControl.callPause();
               }
               //上一首音乐
               public  void  musicplayerframe_pre(View  view){
            	   musicControl.callPre();
               }
               //下一首音乐
               public  void  musicplayerframe_next(View  view){
            	   musicControl.callNext();
               }               
               
               
               //创建ServiceConnection 对象
               public class  MyConnection   implements   ServiceConnection{



				@Override
				public void onServiceConnected(ComponentName name,
						IBinder service) {
                         musicControl = (MyBinder) service;					
				}

				@Override
				public void onServiceDisconnected(ComponentName name) {
					// TODO Auto-generated method stub
					
				}            	   
       }
               
               //bindService务必在Service销毁退出的时候解绑服务，不然会出现方法泄漏
               @Override
            protected void onDestroy() {
            	super.onDestroy();
            	if(conn !=null){
            	unbindService(conn);
                 	}
            	Toast.makeText(getApplicationContext(), "MusicPlayerFrameActivity---onDestroy()", Toast.LENGTH_SHORT).show();
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

       			AdminUtils.AdminMenu(MusicPlayerFrameActivity.this, item);
       			return super.onOptionsItemSelected(item);
       	 }
}
