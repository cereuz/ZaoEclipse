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
			//��������
			intent.putExtra("key", "Hello��zneo��");
			//�յ��㲥��Ҫ��Ȩ��
	        String receiverPermission = null;
	        //��Ϊ���յĹ㲥�����ߣ�����Ҫ��AndroidManifest.xml���ע�ᣬ��������㲥ȫ��ִ��֮���ִ������㲥��
			BroadcastReceiver resultReceiver = new FinalReceiver();
            //�������յĹ㲥�������õ���handler�������null���������̴߳���
			Handler scheduler = null;
			//��ʼ������
			String initialData = "�ʵ۷���������ʳ��ÿ��100�";
			//��������㲥
			sendOrderedBroadcast(intent, receiverPermission , resultReceiver, scheduler, Activity.RESULT_OK, initialData, null);
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

			AdminUtils.AdminMenu(SendOrderBroadcastActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
