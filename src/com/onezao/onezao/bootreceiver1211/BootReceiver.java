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
		//�����Լ���APP
		Intent  intent2  =  new Intent(context,AdminActivity.class);
		//�������������ڹ㲥�����߿���Activity����ǰAPPû���κ�Activity�������У�û���κ�����ջ����Ҫ�Լ�����һ��Activityջ����Ȼ���ܿ���һ������ջ��
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
		//��ȡӦ�ð���
		String data = intent2.getPackage();
		Toast.makeText(context, "�ֻ������ˣ�������APP��\n"+data, Toast.LENGTH_SHORT).show();
	}

}
