package com.onezao.onezao.musicplayer0111;

import com.onezao.onezao.musicplayer0111.MusicPlayerService.MyBinder;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MusicPlayerActivity extends Activity {
             private static final int UPDATE_PROGRESS = 0;
			private EditText et_musicplayer_0111;
			private MyConnection conn;
			private MyBinder musicController;
			private ImageView iv_music_0111;
			private SeekBar sb_musicplayer_0111;
			@SuppressLint("HandlerLeak")
			private Handler  handler   =  new   Handler(){
				public void handleMessage(android.os.Message msg) {
					switch (msg.what) {
					case UPDATE_PROGRESS:
						updateProgress();  	
						break;
					}
				};
			};

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_musicplayer_0111);
            	et_musicplayer_0111 = (EditText) findViewById(R.id.et_musicplayer_0111);
            	iv_music_0111 = (ImageView) findViewById(R.id.iv_music_0111);
            	
            	sb_musicplayer_0111 = (SeekBar) findViewById(R.id.sb_musicplayer_0111);
            	
            	//混合开启服务，音乐一般都是在服务中进行操作
            	Intent  service = new  Intent(this, MusicPlayerService.class);
            	startService(service);
            	conn = new  MyConnection();
            	bindService(service, conn, BIND_AUTO_CREATE);
            }
             
             public void play(View  view){
            	 musicController.playPause();
            	//根据播放状态，更新播放图标。
            	 updatePlayIcon();
/*            //直接通过Activity来播放音乐。
            	 activityPlay();*/
             }
             
             //根据播放状态，更新播放图标。
			private void updatePlayIcon() {
				if(musicController.isPlaying()){
            		 iv_music_0111.setImageResource(R.drawable.ctgu2012);
            		 //发空消息来更新SeekBar
            		 handler.sendEmptyMessage(UPDATE_PROGRESS);
            	 }  else  {
            		 iv_music_0111.setImageResource(R.drawable.button_play_32px);
            		 //暂停的时候没有必要一直更新状态。这时可以把更新状态的消息移除
            		 handler.removeMessages(UPDATE_PROGRESS);
            	 }
			}
			
			/*
			 * 更新播放器当前播放进度，将进度设置到进度条上。
			 */
			private  void   updateProgress(){
				int currentPosition = musicController.getCurrentPosition();
				sb_musicplayer_0111.setProgress(currentPosition);
				//发送一个延迟500毫秒执行的方法，通过handler，500ms之后再次更新seekBar。每500ms循环一次
				handler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 500);
			}

			private void activityPlay() {
				String  path  =  ZaoUtils.pathSD + et_musicplayer_0111.getText().toString().trim();
            	 MediaPlayer   player  =  new  MediaPlayer();
            	try{
            	 //设置要播放的音乐的路径
            	 player.setDataSource(path);
            	 //开始准备，  开始播放
            	 player.prepare();
            	 player.start();
            	}  catch  (Exception   e){
            		e.printStackTrace();
            	}
			}
             
             //创建ServiceConnection
             public  class  MyConnection   implements   ServiceConnection{
				@Override
				public void onServiceConnected(ComponentName arg0, IBinder service) {
					   musicController = (MyBinder) service;
					   //在这个方法中一定会获取到musicController这个MyBinder对象
					   //因为所有控制音乐的方法都需要使用MyBinder来调用，所以初始化界面的方法都写在这里。
					   //更新播放图标
					   updatePlayIcon();
                     
					   //设置总时长
					   sb_musicplayer_0111.setMax(musicController.getDuration());
					   //进来的时候也需要保存当前播放的进度位置
					   sb_musicplayer_0111.setProgress(musicController.getCurrentPosition());
					   //控制进度条，通过调整进度条的位置，来设置播放的位置
					   sb_musicplayer_0111.setOnSeekBarChangeListener(new  OnSeekBarChangeListener() {
						
						@Override
						public void onStopTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onStartTrackingTouch(SeekBar arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
							    if(fromUser){
							    	//修改当前音乐播放器的播放进度
							    	musicController.seekTo(progress);
							    }
						}
					});
					   
				}
			
				@Override
				public void onServiceDisconnected(ComponentName arg0) {
					
				     }
             }
             
             /*
              *  退出的时候销毁更新UI的操作，防止内存泄漏
              */
             @Override
            protected void onStop() {
            super.onStop();
            handler.removeCallbacksAndMessages(null);
            }
             
             @Override
            protected void onDestroy() {
            super.onDestroy();
             //页面销毁的时候调用unBindService方法
            unbindService(conn);
            }
             
             //在界面刷新的时候重新发消息来更新进度条
             @Override
            protected void onResume() {
            super.onResume();
            if(musicController != null){
            	handler.sendEmptyMessage(UPDATE_PROGRESS);
            }
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

     			AdminUtils.AdminMenu(MusicPlayerActivity.this, item);
     			return super.onOptionsItemSelected(item);
     	 }
}
