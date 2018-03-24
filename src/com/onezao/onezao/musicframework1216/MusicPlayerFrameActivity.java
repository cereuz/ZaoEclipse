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
   
            	  // �����������һ���أ��ͻ�ִ��onDestroy�����������Ҫִ�а�ť�Ĺ��ܣ������ȳ�ʼ��һ�¡�
            	   Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);
            	   conn = new  MyConnection();
            	   //ͨ��bindService�������ֲ��ŷ���Service
            	   bindService(service, conn, BIND_AUTO_CREATE);
            	   
            	   //��Ϸ�ʽ��������
            	   startService(service);
            }
               
              //��������
               public void musicplayer_start(View view){
            	   Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);            	   
            	   //��Ϸ�ʽ��������
            	   startService(service);
               }
               
               //�󶨷���
               public void musicplayer_onbind(View view){
            		Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);
                	conn = new  MyConnection();
                	//ͨ��bindService�������ֲ��ŷ���Service
                	bindService(service, conn, BIND_AUTO_CREATE);
               }
               
               //��Ͽ�������
               public void musicplayer_mixstart(View view){
            	   Intent  service   =  new  Intent(this,MusicPlayerFrameService.class);
            	   conn = new  MyConnection();
            	   //ͨ��bindService�������ֲ��ŷ���Service
            	   bindService(service, conn, BIND_AUTO_CREATE);
            	   
            	   //��Ϸ�ʽ��������
            	   startService(service);
               }
               
               
               //��������
               public  void  musicplayerframe_play(View  view){
            	     musicControl.callPlay();
               }
               //��ͣ����
               public  void  musicplayerframe_pause(View  view){
            	   musicControl.callPause();
               }
               //��һ������
               public  void  musicplayerframe_pre(View  view){
            	   musicControl.callPre();
               }
               //��һ������
               public  void  musicplayerframe_next(View  view){
            	   musicControl.callNext();
               }               
               
               
               //����ServiceConnection ����
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
               
               //bindService�����Service�����˳���ʱ������񣬲�Ȼ����ַ���й©
               @Override
            protected void onDestroy() {
            	super.onDestroy();
            	if(conn !=null){
            	unbindService(conn);
                 	}
            	Toast.makeText(getApplicationContext(), "MusicPlayerFrameActivity---onDestroy()", Toast.LENGTH_SHORT).show();
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

       			AdminUtils.AdminMenu(MusicPlayerFrameActivity.this, item);
       			return super.onOptionsItemSelected(item);
       	 }
}
