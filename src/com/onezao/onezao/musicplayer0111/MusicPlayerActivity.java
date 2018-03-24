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
            	
            	//��Ͽ�����������һ�㶼���ڷ����н��в���
            	Intent  service = new  Intent(this, MusicPlayerService.class);
            	startService(service);
            	conn = new  MyConnection();
            	bindService(service, conn, BIND_AUTO_CREATE);
            }
             
             public void play(View  view){
            	 musicController.playPause();
            	//���ݲ���״̬�����²���ͼ�ꡣ
            	 updatePlayIcon();
/*            //ֱ��ͨ��Activity���������֡�
            	 activityPlay();*/
             }
             
             //���ݲ���״̬�����²���ͼ�ꡣ
			private void updatePlayIcon() {
				if(musicController.isPlaying()){
            		 iv_music_0111.setImageResource(R.drawable.ctgu2012);
            		 //������Ϣ������SeekBar
            		 handler.sendEmptyMessage(UPDATE_PROGRESS);
            	 }  else  {
            		 iv_music_0111.setImageResource(R.drawable.button_play_32px);
            		 //��ͣ��ʱ��û�б�Ҫһֱ����״̬����ʱ���԰Ѹ���״̬����Ϣ�Ƴ�
            		 handler.removeMessages(UPDATE_PROGRESS);
            	 }
			}
			
			/*
			 * ���²�������ǰ���Ž��ȣ����������õ��������ϡ�
			 */
			private  void   updateProgress(){
				int currentPosition = musicController.getCurrentPosition();
				sb_musicplayer_0111.setProgress(currentPosition);
				//����һ���ӳ�500����ִ�еķ�����ͨ��handler��500ms֮���ٴθ���seekBar��ÿ500msѭ��һ��
				handler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 500);
			}

			private void activityPlay() {
				String  path  =  ZaoUtils.pathSD + et_musicplayer_0111.getText().toString().trim();
            	 MediaPlayer   player  =  new  MediaPlayer();
            	try{
            	 //����Ҫ���ŵ����ֵ�·��
            	 player.setDataSource(path);
            	 //��ʼ׼����  ��ʼ����
            	 player.prepare();
            	 player.start();
            	}  catch  (Exception   e){
            		e.printStackTrace();
            	}
			}
             
             //����ServiceConnection
             public  class  MyConnection   implements   ServiceConnection{
				@Override
				public void onServiceConnected(ComponentName arg0, IBinder service) {
					   musicController = (MyBinder) service;
					   //�����������һ�����ȡ��musicController���MyBinder����
					   //��Ϊ���п������ֵķ�������Ҫʹ��MyBinder�����ã����Գ�ʼ������ķ�����д�����
					   //���²���ͼ��
					   updatePlayIcon();
                     
					   //������ʱ��
					   sb_musicplayer_0111.setMax(musicController.getDuration());
					   //������ʱ��Ҳ��Ҫ���浱ǰ���ŵĽ���λ��
					   sb_musicplayer_0111.setProgress(musicController.getCurrentPosition());
					   //���ƽ�������ͨ��������������λ�ã������ò��ŵ�λ��
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
							    	//�޸ĵ�ǰ���ֲ������Ĳ��Ž���
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
              *  �˳���ʱ�����ٸ���UI�Ĳ�������ֹ�ڴ�й©
              */
             @Override
            protected void onStop() {
            super.onStop();
            handler.removeCallbacksAndMessages(null);
            }
             
             @Override
            protected void onDestroy() {
            super.onDestroy();
             //ҳ�����ٵ�ʱ�����unBindService����
            unbindService(conn);
            }
             
             //�ڽ���ˢ�µ�ʱ�����·���Ϣ�����½�����
             @Override
            protected void onResume() {
            super.onResume();
            if(musicController != null){
            	handler.sendEmptyMessage(UPDATE_PROGRESS);
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

     			AdminUtils.AdminMenu(MusicPlayerActivity.this, item);
     			return super.onOptionsItemSelected(item);
     	 }
}
