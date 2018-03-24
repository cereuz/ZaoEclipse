package com.onezao.onezao.mixstartservice1217;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MixStartService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "MixStartService---onBind()", Toast.LENGTH_SHORT).show();
		return new Binder();
	}
	
	@Override
	public void onCreate() {
		Toast.makeText(getApplicationContext(), "MixStartService---onCreate()", Toast.LENGTH_SHORT).show();
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), "MixStartService---onStartCommand()", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "MixStartService---onDestroy()", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}

}
