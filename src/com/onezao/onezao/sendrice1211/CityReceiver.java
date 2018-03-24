package com.onezao.onezao.sendrice1211;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CityReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
                 String resultData = getResultData();
                 Toast.makeText(context, resultData, Toast.LENGTH_SHORT).show();
                 setResultData("皇帝发放赈灾粮食，每人20斤！");
                 
 /*                //中断广播，只有有序广播才可以中断。在无序广播中中断，会报异常。non-ordered broadcast
                 abortBroadcast();*/
	}

}
