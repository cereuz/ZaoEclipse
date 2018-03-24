package com.onezao.onezao.startservice1214;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceActivity extends Activity  implements  OnClickListener{
             private Button btn_start_service;
			private Button btn_stop_service;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_service_1214);
            	
            	btn_start_service = (Button) findViewById(R.id.btn_start_service);
            	btn_stop_service = (Button) findViewById(R.id.btn_stop_service);
            	
            	btn_start_service.setOnClickListener(this);
            	btn_stop_service.setOnClickListener(this);
            }

			@Override
			public void onClick(View v) {
                     switch (v.getId()) {
					case R.id.btn_start_service:
						//ʹ����ʽ��ͼ����Service
						Intent intent = new  Intent(this,StartService.class);
						//��������
						startService(intent);
						break;
						
					case R.id.btn_stop_service:
						
						break;
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

				AdminUtils.AdminMenu(ServiceActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
