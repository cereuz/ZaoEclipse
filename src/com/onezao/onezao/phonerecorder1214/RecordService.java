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
		//����һ���绰��������״̬��ͨ�����������Լ����绰��״̬
		TelephonyManager  manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//�绰״̬������
		MyPhoneStateListener   listener = new MyPhoneStateListener();
		//���õ绰��������listen����ע�����
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	private class  MyPhoneStateListener  extends  PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
                 switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					Toast.makeText(RecordService.this, "����״̬ or  ¼������", Toast.LENGTH_SHORT).show();
					break;
				case  TelephonyManager.CALL_STATE_RINGING:   
					Toast.makeText(RecordService.this, "�����ˣ�"+incomingNumber+"\n׼��һ��¼������", Toast.LENGTH_SHORT).show();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(RecordService.this, "�ӵ绰�ˣ�"+incomingNumber+"\n��ʼ¼����", Toast.LENGTH_SHORT).show();
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
