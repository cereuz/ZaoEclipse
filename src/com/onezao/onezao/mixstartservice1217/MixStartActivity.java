package com.onezao.onezao.mixstartservice1217;

import com.onezao.onezao.zao.AdminActivity;
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
import android.widget.Toast;

public class MixStartActivity extends Activity {
              private MyConnection conn;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_mixstartservice_1217);
            }
              
              //���� ����
              public  void start(View view){
            	Intent service = new  Intent(this,MixStartService.class);
				startService(service );
              }
              
              //ֹͣ����
              public  void stop(View view){
              	Intent service = new  Intent(this,MixStartService.class);
  				stopService(service );
              }
              
              //�󶨷���
              public  void bind(View view){
            	  Intent  service  =  new  Intent(this,MixStartService.class);
            	  conn = new  MyConnection();
            	  bindService(service, conn, BIND_AUTO_CREATE);
              }
              
              //������
              public  void unbind(View view){
            	unbindService(conn);  
              }
              
              //Activity  ��Service���������
              public  class  MyConnection  implements  ServiceConnection{

				private MyConnection conn;

				@Override
				public void onServiceConnected(ComponentName name,
						IBinder service) {
					Toast.makeText(getApplicationContext(), "MixStartActivity---onServiceConnected()", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onServiceDisconnected(ComponentName name) {
					Toast.makeText(getApplicationContext(), "MixStartActivity---onServiceDisconnected()", Toast.LENGTH_SHORT).show();
				}
            	  
              }
              
              @Override
            protected void onDestroy() {
			   Toast.makeText(getApplicationContext(), "MixStartActivity---onDestroy()", Toast.LENGTH_SHORT).show();
               super.onDestroy();
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

      			AdminUtils.AdminMenu(MixStartActivity.this, item);
      			return super.onOptionsItemSelected(item);
      	 }
}
