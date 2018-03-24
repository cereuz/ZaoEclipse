package com.onezao.onezao.bootreceiver1211;

import com.onezao.onezao.zao.AdminActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//开启自己的APP
		Intent  intent2  =  new Intent(context,AdminActivity.class);
		//开机启动，是在广播接收者开启Activity，当前APP没有任何Activity正在运行，没有任何任务栈。需要自己创建一个Activity栈，不然不能开启一个任务栈。
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
		//获取应用包名
		String data = intent2.getPackage();
		Toast.makeText(context, "手机开机了，开启了APP：\n"+data, Toast.LENGTH_SHORT).show();
	}

}
