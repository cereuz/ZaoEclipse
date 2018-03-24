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
		//创建一个电话监听器的状态，通过这个对象可以监听电话的状态
		TelephonyManager  manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//电话状态监听器
		MyPhoneStateListener   listener = new MyPhoneStateListener();
		//调用电话管理器的listen方法注册监听
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
						//停止当前录音
						recorder.stop();
						//重置recorder，在这里还可以重新使用
						recorder.reset();
						//释放资源，recorder到这里就不能被重复利用了。
						recorder.release();
						}   catch (Exception  e)  {
							
						}
						Toast.makeText(RecordService2.this, "录音结束", Toast.LENGTH_SHORT).show();
					}   else  {
						Toast.makeText(RecordService2.this, "空闲状态", Toast.LENGTH_SHORT).show();
					}
					break;
				case  TelephonyManager.CALL_STATE_RINGING:   
					Toast.makeText(RecordService2.this, "响铃了："+incomingNumber+"\n准备一个录音机。", Toast.LENGTH_SHORT).show();
					recorder = new  MediaRecorder();
                   //设置音频的输入源。MIC：只能录自己的声音；VOICE_CALL录双方的声音
                   recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
                   //设置音频输出的格式  3gp   文件小，质量较差
                   recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                   //设置音频编码  amr  早期彩屏彩铃手机上使用的音频格式，一般用作手机铃声NB  ： Narrow Band 窄带，Wide Band 宽带
                   recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                   //设置输出之后文件保存的路径
                   recorder.setOutputFile(Environment.getExternalStorageDirectory().getPath()+"/"+incomingNumber+ZaoUtils.getSystemTime()+".3gp");
                   //录音机开始准备
					try {
						recorder.prepare();
					} catch (IllegalStateException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					Toast.makeText(RecordService2.this, "接电话了："+incomingNumber+"\n开始录音。", Toast.LENGTH_SHORT).show();
					// 摘下电话机之后，开始录音
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
