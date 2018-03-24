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
				//��ȡ������Ƶ�ļ��Ĳ���·��
				String  path =  et_netmusic_0113.getText().toString().trim();
				//��ʼ��������
				if(player == null){
				player = new   MediaPlayer();
				try {
					//���ò���Դ��ַ
					player.setDataSource(path);
					//�����ǲ����������֣���Ҫʹ���첽����
					player.prepareAsync();
					//���׼���õļ���
					player.setOnPreparedListener(new  OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							//�����ǰý���ļ��Ѿ�׼�����ˣ��ͻ���onPrepared()����
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

				AdminUtils.AdminMenu(PlayNetMusicActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
			}
