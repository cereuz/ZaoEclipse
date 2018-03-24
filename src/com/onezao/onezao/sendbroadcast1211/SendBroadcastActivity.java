package com.onezao.onezao.sendbroadcast1211;

import com.onezao.onezao.zao.AdminActivity;
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
import android.widget.EditText;

public  class SendBroadcastActivity extends Activity  implements OnClickListener{
                  private Button btn_sendbroadcast;
				private EditText et_action;
				@Override
                protected void onCreate(Bundle savedInstanceState) {
                	super.onCreate(savedInstanceState);
                	setContentView(R.layout.activity_sendbroadcast_1211);
                	
                	et_action = (EditText) findViewById(R.id.et_action);
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
					sendBroadcast(intent);
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

					AdminUtils.AdminMenu(SendBroadcastActivity.this, item);
					return super.onOptionsItemSelected(item);
			 }
}
