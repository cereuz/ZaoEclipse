package com.onezao.onezao.sendrice1211;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendOrderBroadcastActivity extends Activity implements  OnClickListener {
		    private Button btn_sendbroadcast;
			private EditText et_action;
          @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_sendbroadcast_1211);
        	
          	et_action = (EditText) findViewById(R.id.et_action);
          	et_action.setText("com.onezao.broadcast.sendrice");
        	btn_sendbroadcast = (Button) findViewById(R.id.btn_sendbroadcast);        	
        	btn_sendbroadcast.setOnClickListener(this);
        }
		@Override
		public void onClick(View v) {
			String action = et_action.getText().toString().trim();
			Intent intent = new Intent();
			intent.setAction(action);
			//发送数据
			intent.putExtra("key", "Hello，zneo！");
			//收到广播需要的权限
	        String receiverPermission = null;
	        //作为最终的广播接收者，不需要在AndroidManifest.xml里边注册，会在有序广播全部执行之后才执行这个广播。
			BroadcastReceiver resultReceiver = new FinalReceiver();
            //处理最终的广播接收者用到的handler，如果传null，会在主线程处理。
			Handler scheduler = null;
			//初始化数据
			String initialData = "皇帝发放赈灾粮食，每人100斤。";
			//发送有序广播
			sendOrderedBroadcast(intent, receiverPermission , resultReceiver, scheduler, Activity.RESULT_OK, initialData, null);
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

			AdminUtils.AdminMenu(SendOrderBroadcastActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
