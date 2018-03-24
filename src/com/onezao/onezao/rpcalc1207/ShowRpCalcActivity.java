package com.onezao.onezao.rpcalc1207;

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
import android.widget.TextView;

public class ShowRpCalcActivity extends Activity {
          private TextView tv_info;
		private TextView tv_result;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_showrpcalc_1207);
        	
        	tv_info = (TextView) findViewById(R.id.tv_info);
        	tv_result = (TextView) findViewById(R.id.tv_result);
        	
        	Intent  intent  =  getIntent();
        	String name = intent.getStringExtra("name");
        	String gender = intent.getStringExtra("gender");       	
        	
        	tv_info.setText("������"+name+"    �Ա�"+gender);
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

			AdminUtils.AdminMenu(ShowRpCalcActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
