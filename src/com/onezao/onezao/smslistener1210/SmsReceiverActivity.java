package com.onezao.onezao.smslistener1210;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SmsReceiverActivity extends Activity {
              private Button btn_sms_listener_show;
			private TextView tv_sms_listener;
			public  StringBuilder sms_text;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_sms_receiver_1210);
            	
            	btn_sms_listener_show = (Button) findViewById(R.id.btn_sms_listener_show);
            	tv_sms_listener = (TextView) findViewById(R.id.tv_sms_listener);
            	
            	final SharedPreferences sp = getSharedPreferences(ZaoUtils.ONEZAO, MODE_APPEND);	
            	btn_sms_listener_show.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View v) {
			            	String sms_text = sp.getString("smsSave", null);
			            	if(TextUtils.isEmpty(sms_text)){
			            		Toast.makeText(getApplicationContext(), "没有收到短信哦！", Toast.LENGTH_SHORT).show();
			            	}
                          tv_sms_listener.setText(sms_text);						
					}
				});
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

				AdminUtils.AdminMenu(SmsReceiverActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
			
}
