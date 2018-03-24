package com.onezao.onezao.ipdialer1210;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class IpDialerReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取IP号码
		SharedPreferences sp = context.getSharedPreferences("onezao", Context.MODE_PRIVATE);
		String ip_num = sp.getString("IP_NUM", null);
        //获取打电话的号码
		String number =  getResultData();
		setResultData(ip_num+number);
	}

}
