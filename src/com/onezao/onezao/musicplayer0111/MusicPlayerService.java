package com.onezao.onezao.musicplayer0111;

import com.onezao.onezao.zao.ZaoUtils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicPlayerService extends Service {
	String  path  =  ZaoUtils.pathSD +  "/ame/山丘-李宗盛 .mp3";
	private MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
//	String 	path = intent.getStringExtra("path");    //Service中是先执行onCreate，onBind在后边执行，所以此路径获取之后无效。
		return new  MyBinder();
	}
	
	public  class  MyBinder  extends  Binder {
		/*
		 * 播放暂停的方法
		 */
		 public  void  playPause(){
			 if(player.isPlaying()){
				 //如果正在播放，就让其暂停
			 player.pause();
		 }  else  {
			 //如果不是正在播放，就开始播放。
			 player.start();
		 }
	  }
		 
		 /*
		  *   返回当前播放器的播放状态
		  */
		 public  boolean   isPlaying(){
			 return player.isPlaying();
		 }
		 /*
		  * 获取播放音乐的总时长，返回的是音乐时长的毫秒值
		  */
		 public  int  getDuration(){
			 return  player.getDuration();
		 }
		 /*
		  * 获取当前播放的进度
		  * 当前播放进度的毫秒值
		  */
		 public  int  getCurrentPosition(){
			 return  player.getCurrentPosition();
		 }
		 /*
		  * 暴露一个设置音乐播放位置的方法
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
