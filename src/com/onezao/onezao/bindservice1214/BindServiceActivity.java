package com.onezao.onezao.bindservice1214;

import com.onezao.onezao.bindservice1214.BindService.MyBinder;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BindServiceActivity extends Activity  implements OnClickListener{
    private Button btn_bind_service;
		private Button btn_unbind_service;
		private MyConnection conn;
		public  MyBinder myBinder;
		private Button btn_call_unbind_service;

		@Override
     protected void onCreate(Bundle savedInstanceState) {
     	super.onCreate(savedInstanceState);
     	setContentView(R.layout.activity_bindservice_1214);
     	
     	btn_bind_service = (Button) findViewById(R.id.btn_bind_service);
     	btn_unbind_service = (Button) findViewById(R.id.btn_unbind_service);
     	btn_call_unbind_service = (Button) findViewById(R.id.btn_call_unbind_service);
     	
     	btn_bind_service.setOnClickListener(this);
     	btn_unbind_service.setOnClickListener(this);
     	btn_call_unbind_service.setOnClickListener(this);
     }

		@Override
		public void onClick(View v) {
              switch (v.getId()) {
				case R.id.btn_bind_service:
					//ʹ����ʽ��ͼ����Service
					Intent service = new  Intent(this,BindService.class);
				     conn = new   MyConnection();
					//ʹ��bindService��������
				     //1.��ͼ  Intent��
				     //2.ServiceConnection�ӿ�-ͨ������ӿڽ��շ��������߽�������Ϣ
				     //3.���������ʱ�������ѡ�һ�㴫��BIND_AUTO_CREATE���Զ�����Service
					bindService(service, conn, BIND_AUTO_CREATE);
					break;
					
				case R.id.btn_unbind_service:
					//ʹ��bindService�����ķ�����Ҫʹ��unbindService��ֹͣ
					unbindService(conn);
					break;
					
				case R.id.btn_call_unbind_service:	
					//ͨ��IBinder������÷�����ߵķ���
					myBinder.callShowToast("Hello   zneo !");
					break;
				}				
		}
					
		 //2.ServiceConnection�ӿ�-ͨ������ӿڽ��շ��������߽�������Ϣ
		public  class  MyConnection  implements  ServiceConnection{

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				myBinder = (MyBinder)service;
		        Toast.makeText(getApplicationContext(), "onServiceConnected", Toast.LENGTH_SHORT).show();   				
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				//�����������˳���ʱ�򣬲������onServiceDisconnected
		        Toast.makeText(getApplicationContext(), "onServiceDisconnected", Toast.LENGTH_SHORT).show();   				
			}
			
		}
		//ʹ��onBind�������񣬱�����onDestory������unbindService()�������ǿ��Ҫ��
		@Override
		protected void onDestroy() {
			super.onDestroy();
			if(conn != null){
			unbindService(conn);
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

			AdminUtils.AdminMenu(BindServiceActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
