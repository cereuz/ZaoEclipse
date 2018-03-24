package com.onezao.onezao.rigisterreceiver1212;

import com.onezao.onezao.autosmslistener1212.AutoSmsReceiverActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class RigisterReceiverActivity extends Activity {
         private TextView tv_receiver_screenoff;
		private  TextView tv_receiver_screenon;
		public final String SCREEN_ON  = "android.intent.action.SCREEN_ON";
		public final String SCREEN_OFF  = "android.intent.action.SCREEN_OFF";

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_rigister_receiver_1212);
        	
        	//��̬ע��㲥
        	BroadcastReceiver   receiver  =  new  ScreenReceiver();
        	IntentFilter  filter  =  new IntentFilter();
        	filter.addAction("android.intent.action.SCREEN_ON");
        	filter.addAction("android.intent.action.SCREEN_OFF");
        	registerReceiver(receiver, filter);
        	
        	tv_receiver_screenoff = (TextView) findViewById(R.id.tv_receiver_screenoff);
        	tv_receiver_screenon = (TextView) findViewById(R.id.tv_receiver_screenon);
			tv_receiver_screenon.setText("�رպͿ�����Ļ���鿴��ʾ���ֱ仯��");
        }
          
          public class ScreenReceiver  extends  BroadcastReceiver{
        	  @Override
        	public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        switch (action) {
						case SCREEN_ON:
							tv_receiver_screenon.setText("��Ļ�����ˣ�");
							break;
						case SCREEN_OFF:
							tv_receiver_screenoff.setText("��ĻϨ���ˣ�");
							break;
						}
        	}
          }
          
          
  		//���ض����˵�����Ӳ˵��ĵ���¼���
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				  //�������Ͻǵ�ͼ��ĵ���¼�  ActionBar
			      ActionBar actionBar = this.getActionBar();
			     actionBar.setHomeButtonEnabled(true);
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.admin, menu);
				return true;
			}
			
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {

				AdminUtils.AdminMenu(RigisterReceiverActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
