package com.onezao.onezao.sendrice1211;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FinalReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        //��Ϊ���յĹ㲥�����ߣ�����Ҫ��AndroidManifest.xml���ע�ᣬ��������㲥ȫ��ִ��֮���ִ������㲥��
                 String resultData = getResultData();
                 Toast.makeText(context, "FINAL��"+resultData, Toast.LENGTH_SHORT).show();
	}

}
