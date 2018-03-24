package com.onezao.onezao.playnetvideo0113;

import java.io.IOException;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

public class PlayNetVideoActivity extends Activity {
                private SurfaceView sv_netvideo_0113;
				private EditText et_netvideo_0113;
				private SurfaceHolder holder;
				private MediaPlayer player;

				@Override
                protected void onCreate(Bundle savedInstanceState) {
                	super.onCreate(savedInstanceState);
                	setContentView(R.layout.activity_playnetvideo_0113);
                	et_netvideo_0113 = (EditText) findViewById(R.id.et_netvideo_0113);
                	sv_netvideo_0113 = (SurfaceView) findViewById(R.id.sv_netvideo_0113);
                	//获取 视频容器
                	holder = sv_netvideo_0113.getHolder();
                }
                
				public  void  prepare(View view){
					String  path  =  et_netvideo_0113.getText().toString().trim();
					player = new  MediaPlayer();
					try {
						player.setDataSource(path);
						player.prepareAsync();
						//设置展示容器
						player.setDisplay(holder);
						player.setOnPreparedListener(new  OnPreparedListener() {
							@Override
							public void onPrepared(MediaPlayer mp) {
								mp.start();
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				public  void  start(View  view){
					  if(player != null){
						  if(player.isPlaying()){
							  player.pause();
						  }  else  {
							  player.start();
						  }
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

					AdminUtils.AdminMenu(PlayNetVideoActivity.this, item);
					return super.onOptionsItemSelected(item);
			 }
}
