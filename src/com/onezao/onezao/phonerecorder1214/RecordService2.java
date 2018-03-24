package com.onezao.onezao.phonerecorder1214;

import java.io.IOException;

import com.onezao.onezao.zao.ZaoUtils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class RecordService2 extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		 Toast.makeText(RecordService2.this, "onCreate", Toast.LENGTH_SHORT).show();   
		super.onCreate();
		//����һ���绰��������״̬��ͨ�����������Լ����绰��״̬
		TelephonyManager  manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//�绰״̬������
		MyPhoneStateListener   listener = new MyPhoneStateListener();
		//���õ绰��������listen����ע�����
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	private class  MyPhoneStateListener  extends  PhoneStateListener {
		private MediaRecorder recorder;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
                 switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					if(recorder != null){
						try{
						//ֹͣ��ǰ¼��
						recorder.stop();
						//����recorder�������ﻹ��������ʹ��
						recorder.reset();
						//�ͷ���Դ��recorder������Ͳ��ܱ��ظ������ˡ�
						recorder.release();
						}   catch (Exception  e)  {
							
						}
						Toast.makeText(RecordService2.this, "¼������", Toast.LENGTH_SHORT).show();
					}   else  {
						Toast.makeText(RecordService2.this, "����״̬", Toast.LENGTH_SHORT).show();
					}
					break;
				case  TelephonyManager.CALL_STATE_RINGING:   
					Toast.makeText(RecordService2.this, "�����ˣ�"+incomingNumber+"\n׼��һ��¼������", Toast.LENGTH_SHORT).show();
					recorder = new  MediaRecorder();
                   //������Ƶ������Դ��MIC��ֻ��¼�Լ���������VOICE_CALL¼˫��������
                   recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
                   //������Ƶ����ĸ�ʽ  3gp   �ļ�С�������ϲ�
                   recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                   //������Ƶ����  amr  ���ڲ��������ֻ���ʹ�õ���Ƶ��ʽ��һ�������ֻ�����NB  �� Narrow Band խ����Wide Band ���
                   recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                   //�������֮���ļ������·��
                   recorder.setOutputFile(Environment.getExternalStorageDirectory().getPath()+"/"+incomingNumber+ZaoUtils.getSystemTime()+".3gp");
                   //¼������ʼ׼��
					try {
						recorder.prepare();
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(RecordService2.this, "�ӵ绰�ˣ�"+incomingNumber+"\n��ʼ¼����", Toast.LENGTH_SHORT).show();
					// ժ�µ绰��֮�󣬿�ʼ¼��
					recorder.start();
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
