package com.onezao.onezao.sendbroadcast1211;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		      //��������
		      String extra = intent.getStringExtra("key");
		      String action = intent.getAction();
              Toast.makeText(context, "���յ����Զ���Ĺ㲥��\nextra��"+extra+"\nAction="+action, Toast.LENGTH_LONG).show();
	}

}
