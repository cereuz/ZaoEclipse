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
	
	//����֮�����õ����ݿ����֣��ļ�����ǰ����Ҫ����  /
	public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/onezao.xml";
	private String originatingAddress;
	private String messageBody;
	private long timestampMillis;
	private String str;
	private String concat = "";
	private SmsMessage smsMessage;

	@Override
	public void onReceive(Context context, Intent intent) {
   		//�������
   		SharedPreferences sp = context.getSharedPreferences(ZaoUtils.ONEZAO, Context.MODE_APPEND);
   		Editor editor = sp.edit();
             //��ȡ��������
		Object[]  object =(Object[]) intent.getExtras().get("pdus");
   		//������������
		for(int i = 0;i<object.length;i++){
			//�������ŵ���Ϣ����
			smsMessage = SmsMessage.createFromPdu((byte[])object[i]);
	   		//��ȡ���ŷ�����  �ͷ���ʱ�䡣���Ͷ�����������from����������messageBody
			originatingAddress = smsMessage.getOriginatingAddress();
			timestampMillis = smsMessage.getTimestampMillis();
			messageBody = smsMessage.getMessageBody();
   		    //��ƴ�������� 
	   		concat  = concat.concat(messageBody);	   		
		}	
   		//	ת��һ��ʱ����Ϣ
   		SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss ");       
   		Date    smsDate    =   new    Date(timestampMillis);//��ȡʱ��       
   		str = formatter.format(smsDate);
		//��ƴ���ŵ���Ϣ
   		String  smsSave = " ���ڣ�"+str +"\n ���ԣ�"+originatingAddress+ "\n ���ݣ�"+concat +"\n";	
		//�������ݵ�sp
		editor.putString("smsSave", smsSave);
		editor.commit();
/*		//��������Activity
		Intent intent2 = new Intent(context,SmsReceiverActivity.class);
        context.startActivity(intent2);*/
        
        			//���ƶ��ŵ��ڴ濨
		ZaoUtils.copyFile(sp.getClass().getResource("onezao").toString(), fileName);	
	}

}
