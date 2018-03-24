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
					//使用显式意图开启Service
					Intent service = new  Intent(this,BindService.class);
				     conn = new   MyConnection();
					//使用bindService开启服务（
				     //1.意图  Intent，
				     //2.ServiceConnection接口-通过这个接口接收服务开启或者结束的消息
				     //3.开启服务的时候操作的选项，一般传入BIND_AUTO_CREATE，自动创建Service
					bindService(service, conn, BIND_AUTO_CREATE);
					break;
					
				case R.id.btn_unbind_service:
					//使用bindService开启的服务，需要使用unbindService来停止
					unbindService(conn);
					break;
					
				case R.id.btn_call_unbind_service:	
					//通过IBinder对象调用服务里边的方法
					myBinder.callShowToast("Hello   zneo !");
					break;
				}				
		}
					
		 //2.ServiceConnection接口-通过这个接口接收服务开启或者结束的消息
		public  class  MyConnection  implements  ServiceConnection{

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				myBinder = (MyBinder)service;
		        Toast.makeText(getApplicationContext(), "onServiceConnected", Toast.LENGTH_SHORT).show();   				
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				//当服务正常退出的时候，不会调用onServiceDisconnected
		        Toast.makeText(getApplicationContext(), "onServiceDisconnected", Toast.LENGTH_SHORT).show();   				
			}
			
		}
		//使用onBind启动服务，必须在onDestory方法中unbindService()，这个是强制要求
		@Override
		protected void onDestroy() {
			super.onDestroy();
			if(conn != null){
			unbindService(conn);
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

			AdminUtils.AdminMenu(BindServiceActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
