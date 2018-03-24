package com.onezao.onezao.playnetmusic0113;

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
import android.view.View;
import android.widget.EditText;

public class PlayNetMusicActivity extends Activity {
            private EditText et_netmusic_0113;
			private MediaPlayer player;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_playnetmusic_0113);
            	
            	et_netmusic_0113 = (EditText) findViewById(R.id.et_netmusic_0113);
            }
			
			public  void  play(View  view){
				//获取网络音频文件的播放路径
				String  path =  et_netmusic_0113.getText().toString().trim();
				//初始化播放器
				if(player == null){
				player = new   MediaPlayer();
				try {
					//设置播放源地址
					player.setDataSource(path);
					//由于是播放网络音乐，需要使用异步操作
					player.prepareAsync();
					//添加准备好的监听
					player.setOnPreparedListener(new  OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							//如果当前媒体文件已经准备好了，就会走onPrepared()方法
							mp.start();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}   else  {
				  if(player.isPlaying()){
					  player.pause();
				  }   else  {
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

				AdminUtils.AdminMenu(PlayNetMusicActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
			}
