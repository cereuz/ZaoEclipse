package com.onezao.onezao.remotemethod1218;

import com.onezao.onezao.remotemethod1218.IService.Stub;
import com.onezao.onezao.startservice1214.StartService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}

	//���÷���
	public class  MyBinder  extends  Stub{
//		public class  MyBinder  extends  Binder  implements  IService{
		public void callRemoteMethod(){
			remoteMethod();
		}
	}
	
	//��Ҫ������Ӧ�õ��õķ���
	public void remoteMethod(){
		Log.i("RemoteService", "RemoteService   is  called !");
		//�����ͨ�ţ������޸�UI������UI�߳�
//		Toast.makeText(getApplicationContext(), "RemoteService-------RemoteService   is  called !", Toast.LENGTH_SHORT).show();
		//����Ӧ�õ�һ������
		Intent  service  =  new  Intent(getApplicationContext(),RemoteService.class);
		startService(service);
		Intent  service2  =  new  Intent(getApplicationContext(),StartService.class);
		startService(service2);
	}
}
