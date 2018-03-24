package com.onezao.onezao.bindservice1214;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BindService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
        Toast.makeText(getBaseContext(), "onBind", Toast.LENGTH_SHORT).show();   
		return new  MyBinder();
	}
	
	@Override
	public void onCreate() {
        Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_SHORT).show();   
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "onStartCommand", Toast.LENGTH_SHORT).show();   
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
        Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_SHORT).show();   
		super.onDestroy();
	}
	
	//创建Binder的继承类
	public  class  MyBinder  extends  Binder {
		  public void  callShowToast(String s){
			  showToast(s);
		  }
	}
	
	//定义一个服务里边的方法
	public void showToast(String  s){
		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
	}
}