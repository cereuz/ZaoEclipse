package com.onezao.onezao.musicplayer0111;

import com.onezao.onezao.zao.ZaoUtils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicPlayerService extends Service {
	String  path  =  ZaoUtils.pathSD +  "/ame/ɽ��-����ʢ .mp3";
	private MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
//	String 	path = intent.getStringExtra("path");    //Service������ִ��onCreate��onBind�ں��ִ�У����Դ�·����ȡ֮����Ч��
		return new  MyBinder();
	}
	
	public  class  MyBinder  extends  Binder {
		/*
		 * ������ͣ�ķ���
		 */
		 public  void  playPause(){
			 if(player.isPlaying()){
				 //������ڲ��ţ���������ͣ
			 player.pause();
		 }  else  {
			 //����������ڲ��ţ��Ϳ�ʼ���š�
			 player.start();
		 }
	  }
		 
		 /*
		  *   ���ص�ǰ�������Ĳ���״̬
		  */
		 public  boolean   isPlaying(){
			 return player.isPlaying();
		 }
		 /*
		  * ��ȡ�������ֵ���ʱ�������ص�������ʱ���ĺ���ֵ
		  */
		 public  int  getDuration(){
			 return  player.getDuration();
		 }
		 /*
		  * ��ȡ��ǰ���ŵĽ���
		  * ��ǰ���Ž��ȵĺ���ֵ
		  */
		 public  int  getCurrentPosition(){
			 return  player.getCurrentPosition();
		 }
		 /*
		  * ��¶һ���������ֲ���λ�õķ���
		  */
		 public  void  seekTo(int  msec){
			 player.seekTo(msec);
		 }
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		player = new   MediaPlayer();
		try {
		player.setDataSource(path);
		player.prepare();
	  }   catch  (Exception  e) {
		  e.printStackTrace();
	  }
	}

}
