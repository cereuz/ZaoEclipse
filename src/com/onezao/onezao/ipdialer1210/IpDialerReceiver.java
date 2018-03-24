package com.onezao.onezao.ipdialer1210;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class IpDialerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡIP����
		SharedPreferences sp = context.getSharedPreferences("onezao", Context.MODE_PRIVATE);
		String ip_num = sp.getString("IP_NUM", null);
        //��ȡ��绰�ĺ���
		String number =  getResultData();
		setResultData(ip_num+number);
	}

}
