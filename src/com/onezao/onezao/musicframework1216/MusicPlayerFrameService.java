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
	
	//����һ��Binder���࣬�����Լ���Service�ж���ķ������൱�ڿ�����һ�׽ӿ�
	public class  MyBinder  extends  Binder{
		
		//�����Լ���Service�ж���ķ���
		   public void callPlay(){
			   play();
		   }
		   //�����Լ���Service�ж���ķ���
		   public void callPause(){
			   pause();
		   }
		   //�����Լ���Service�ж���ķ���
		   public void callPre(){
			   pre();
		   }
		   //�����Լ���Service�ж���ķ���
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
	
	//Service���Լ�����Ĳ��ŵķ���
	public void  play(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---play");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---play", Toast.LENGTH_SHORT).show();
	}
	//��ͣ�ķ���
	public void  pause(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---pause");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---pause", Toast.LENGTH_SHORT).show();

	}
	//��һ�׵ķ���
	public void  pre(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---pre");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---pre", Toast.LENGTH_SHORT).show();
	}
	//��һ�׵ķ���
	public void  next(){
		Log.i("MusicPlayerFrameService","MusicPlayerFrameService---next");
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---next", Toast.LENGTH_SHORT).show();
	}
   
/*	//��onDestroy����
	@Override
	public void onDestroy() {
//		super.onDestroy();
		Toast.makeText(getApplicationContext(), "MusicPlayerFrameService---onDestroy()", Toast.LENGTH_SHORT).show();
	}*/
}
