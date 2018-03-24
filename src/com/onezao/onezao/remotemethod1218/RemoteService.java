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

	//调用方法
	public class  MyBinder  extends  Stub{
//		public class  MyBinder  extends  Binder  implements  IService{
		public void callRemoteMethod(){
			remoteMethod();
		}
	}
	
	//需要被其他应用调用的方法
	public void remoteMethod(){
		Log.i("RemoteService", "RemoteService   is  called !");
		//跨进程通信，不能修改UI，不在UI线程
//		Toast.makeText(getApplicationContext(), "RemoteService-------RemoteService   is  called !", Toast.LENGTH_SHORT).show();
		//开启应用的一个服务
		Intent  service  =  new  Intent(getApplicationContext(),RemoteService.class);
		startService(service);
		Intent  service2  =  new  Intent(getApplicationContext(),StartService.class);
		startService(service2);
	}
}
