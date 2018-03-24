package com.onezao.onezao.dialer1113;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class DialerActivity0004 extends Activity {
         private EditText et_num_1113;
  
		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_dialer_1113);
        	
        	et_num_1113 = (EditText) findViewById(R.id.et_num_1113);
        }
		
		public void dialer1113(View view){
			String number = et_num_1113.getText().toString();
            DialerUtil.Dialer(number, DialerActivity0004.this, DialerUtil.toastNonum4);
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

			AdminUtils.AdminMenu(DialerActivity0004.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
