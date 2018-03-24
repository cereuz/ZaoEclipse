package com.onezao.onezao.phonerecorder1214;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootServiceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	             Intent  service = new  Intent(context,RecordService.class);
                context.startService(service);  
                Intent  service2 = new  Intent(context,RecordService2.class);
                context.startService(service2);  
	}

}
