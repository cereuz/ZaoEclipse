package com.onezao.onezao.smslistener1210;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.onezao.onezao.database2listview.DataToListActivity;
import com.onezao.onezao.listview1121.SimpleAdapterActivity;
import com.onezao.onezao.zao.ZaoUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {
	
	//复制之后设置的数据库名字，文件名称前边需要加上  /
	public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/onezao.xml";
	private String originatingAddress;
	private String messageBody;
	private long timestampMillis;
	private String str;
	private String concat = "";
	private SmsMessage smsMessage;

	@Override
	public void onReceive(Context context, Intent intent) {
   		//保存短信
   		SharedPreferences sp = context.getSharedPreferences(ZaoUtils.ONEZAO, Context.MODE_APPEND);
   		Editor editor = sp.edit();
             //获取短信内容
		Object[]  object =(Object[]) intent.getExtras().get("pdus");
   		//遍历短信数组
		for(int i = 0;i<object.length;i++){
			//创建短信的消息对象，
			smsMessage = SmsMessage.createFromPdu((byte[])object[i]);
	   		//获取短信发送人  和发送时间。发送短信来自哪里from，短信内容messageBody
			originatingAddress = smsMessage.getOriginatingAddress();
			timestampMillis = smsMessage.getTimestampMillis();
			messageBody = smsMessage.getMessageBody();
   		    //组拼整条短信 
	   		concat  = concat.concat(messageBody);	   		
		}	
   		//	转化一下时间信息
   		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss ");       
   		Date    smsDate    =   new    Date(timestampMillis);//获取时间       
   		str = formatter.format(smsDate);
		//组拼短信的信息
   		String  smsSave = " 日期："+str +"\n 来自："+originatingAddress+ "\n 内容："+concat +"\n";	
		//保存数据到sp
		editor.putString("smsSave", smsSave);
		editor.commit();
/*		//启动其他Activity
		Intent intent2 = new Intent(context,SmsReceiverActivity.class);
        context.startActivity(intent2);*/
        
        			//复制短信到内存卡
		ZaoUtils.copyFile(sp.getClass().getResource("onezao").toString(), fileName);	
	}

}
