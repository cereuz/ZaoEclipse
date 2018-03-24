package com.onezao.onezao.handlerdelay1127;

import com.onezao.onezao.dialer1113.DialerActivity0004;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DelayHandlerActivity extends Activity {
             private static final long delayMillis = 1000;
			private TextView tv_handler_time;
			private TextView tv_handler_date;
			public String date;
			public  int count = 0;
             public Handler  handler = new Handler(){
            	  public void handleMessage(Message msg) {
            		  countDown();
            	  }; 
             };
            	 
             
             
			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_delay_handler_1127);
            	
            	tv_handler_time = (TextView) findViewById(R.id.tv_handler_time);
            	tv_handler_time.setText(0+"");
            	tv_handler_date = (TextView) findViewById(R.id.tv_handler_date);            	
            	tv_handler_date.setText(ZaoUtils.getSystemTime());
/*            	Message  msg =  Message.obtain();
            	//sendMessageDelayed ����һ���ӳ�ִ�е���Ϣ��������Ϣ���ӳ�1000����ִ��
               handler.sendMessageDelayed(msg, delayMillis);*/
            	//����һ������Ϣ������Ϣ������û����Ϣ��������Ϣ��Я�����ݣ�����ĵ�һ��������what������msg.what�������������ֲ�ͬ����Ϣ 
            	handler.sendEmptyMessageDelayed(1, delayMillis);
            }
			
			public void countDown(){
				date = ZaoUtils.getSystemTime();
				tv_handler_time.setText(count+1+"");
				tv_handler_date.setText(date);
				count++;
				Message  msg  =  Message.obtain();
				//sendMessageDelayed ����һ���ӳ�ִ�е���Ϣ��������Ϣ���ӳ�1000����ִ��
				handler.sendMessageDelayed(msg, delayMillis);
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
				AdminUtils.AdminMenu(DelayHandlerActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
