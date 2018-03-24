package com.onezao.onezao.sendrice1211;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FinalReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        //作为最终的广播接收者，不需要在AndroidManifest.xml里边注册，会在有序广播全部执行之后才执行这个广播。
                 String resultData = getResultData();
                 Toast.makeText(context, "FINAL："+resultData, Toast.LENGTH_SHORT).show();
	}

}
