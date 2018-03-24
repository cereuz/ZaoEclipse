package com.onezao.onezao.musicframework1216;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicPlayerFrameService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---onBind()", Toast.LENGTH_SHORT).show();
		return new  MyBinder();
	}
	
	//定义一个Binder的类，调用自己在Service中定义的方法。相当于开发了一套接口
	public class  MyBinder  extends  Binder{
		
		//调用自己在Service中定义的方法
		   public void callPlay(){
			   play();
		   }
		   //调用自己在Service中定义的方法
		   public void callPause(){
			   pause();
		   }
		   //调用自己在Service中定义的方法
		   public void callPre(){
			   pre();
		   }
		   //调用自己在Service中定义的方法
		   public void callNext(){
			   next();
		   }
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---onCreate");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---onCreate()", Toast.LENGTH_SHORT).show();
	}
	
	//Service中自己定义的播放的方法
	public void  play(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---play");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---play", Toast.LENGTH_SHORT).show();
	}
	//暂停的方法
	public void  pause(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---pause");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---pause", Toast.LENGTH_SHORT).show();

	}
	//上一首的方法
	public void  pre(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---pre");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---pre", Toast.LENGTH_SHORT).show();
	}
	//下一首的方法
	public void  next(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---next");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---next", Toast.LENGTH_SHORT).show();
	}
   
/*	//走onDestroy方法
	@Override
	public void onDestroy() {
//		super.onDestroy();
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---onDestroy()", Toast.LENGTH_SHORT).show();
	}*/
}
