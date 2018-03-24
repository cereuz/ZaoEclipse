package com.onezao.onezao.phonerecorder1214;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class RecordService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		 Toast.makeText(RecordService.this, "onCreate", Toast.LENGTH_SHORT).show();   
		super.onCreate();
		//创建一个电话监听器的状态，通过这个对象可以监听电话的状态
		TelephonyManager  manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//电话状态监听器
		MyPhoneStateListener   listener = new MyPhoneStateListener();
		//调用电话管理器的listen方法注册监听
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	private class  MyPhoneStateListener  extends  PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
                 switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					Toast.makeText(RecordService.this, "空闲状态 or  录音结束", Toast.LENGTH_SHORT).show();
					break;
				case  TelephonyManager.CALL_STATE_RINGING:   
					Toast.makeText(RecordService.this, "响铃了："+incomingNumber+"\n准备一个录音机。", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(RecordService.this, "接电话了："+incomingNumber+"\n开始录音。", Toast.LENGTH_SHORT).show();
					break;
				} 
		}
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
}
