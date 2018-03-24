package com.onezao.onezao.autosmslistener1212;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.onezao.onezao.smslistener1210.SmsReceiver;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AutoSmsReceiverActivity extends Activity {
              private Button btn_sms_listener_show;
			private TextView tv_sms_listener ;
			public  StringBuilder sms_text;
			private String action = "android.provider.Telephony.SMS_RECEIVED";
			
			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_sms_receiver_1210);
            	
            	tv_sms_listener = (TextView) findViewById(R.id.tv_sms_listener);
            	tv_sms_listener.setText("接收短信的时候查看本页面的信息变化！");
            	tv_sms_listener.setTextSize(32);
            	btn_sms_listener_show = (Button) findViewById(R.id.btn_sms_listener_show);
            	//动态注册短信接收的广播
            	BroadcastReceiver   receiver  =  new CodeReceiver();
            	IntentFilter  filter  =  new IntentFilter();
            	filter.addAction("com.onezao.onezao.smscode");
            	registerReceiver(receiver, filter);
            	
            	btn_sms_listener_show.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View v) {
                          tv_sms_listener.setText("good  boy!");						
					}
				});
            }
			
			public class CodeReceiver  extends  BroadcastReceiver{
				@Override
				public void onReceive(Context context, Intent intent) {
                          String extra = intent.getStringExtra("smscode");
                          tv_sms_listener.setTextSize(16);	
                          tv_sms_listener.setText(extra);
				}
				
			}
			
			
			
			
			
			//加载顶部菜单，添加菜单的点击事件。
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				  //设置左上角的图标的点击事件  ActionBar
			      ActionBar actionBar = this.getActionBar();
			     actionBar.setHomeButtonEnabled(true);
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.admin, menu);
				return true;
			}
			
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {

				AdminUtils.AdminMenu(AutoSmsReceiverActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
			
}
