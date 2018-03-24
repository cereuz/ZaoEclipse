package com.onezao.onezao.dialer1113;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DialerActivity0001 extends Activity {
       private EditText et_num_1113;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_dialer_1113);
    	
    	et_num_1113 = (EditText) findViewById(R.id.et_num_1113);    	
    	Button dial1113 = (Button) findViewById(R.id.dial1113);
    	dial1113.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String number = et_num_1113.getText().toString();	
               DialerUtil.Dialer(number, DialerActivity0001.this, DialerUtil.toastNonum);
			}
		});
    }
	
	
	//���ض����˵������Ӳ˵��ĵ���¼���
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

		AdminUtils.AdminMenu(DialerActivity0001.this, item);
		return super.onOptionsItemSelected(item);
 }
}