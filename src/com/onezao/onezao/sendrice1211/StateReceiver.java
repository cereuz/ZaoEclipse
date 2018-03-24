package com.onezao.onezao.sendrice1211;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
                 String resultData = getResultData();
                 Toast.makeText(context, resultData, Toast.LENGTH_SHORT).show();
                 setResultData("皇帝发放赈灾粮食，每人60斤！");
	}

}
