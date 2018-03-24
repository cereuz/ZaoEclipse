package com.onezao.onezao.startservice1214;

import com.onezao.onezao.listview1121.SimpleAdapterActivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class StartService extends Service {

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}
	
	@Override
	public void onCreate() {
        Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_SHORT).show();
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "onStartCommand", Toast.LENGTH_SHORT).show();   
		//开启应用的一个Activity  , 因为是在Service中开启，没有界面的时候开启Activity需要设置对应的Flags
		Intent  intentn  =  new Intent(getApplicationContext(),SimpleAdapterActivity.class);
		intentn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intentn);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
        Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_SHORT).show();   
		super.onDestroy();
	}
	
}
