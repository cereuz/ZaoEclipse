package com.onezao.onezao.ipdialer1210;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IPDialerActivity extends Activity {
          private EditText et_input_ipnum;
		private Button btn_ipnum;
		private TextView tv_ipnum;
		private SharedPreferences sp;
		private String ip_num;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_ipdialer_1210);
        	
        	et_input_ipnum = (EditText) findViewById(R.id.et_input_ipnum);
        	btn_ipnum = (Button) findViewById(R.id.btn_ipnum);
        	tv_ipnum = (TextView) findViewById(R.id.tv_ipnum);
        	sp = getSharedPreferences("onezao", MODE_PRIVATE);
        	ip_num = sp.getString("IP_NUM", null);
        	if(!TextUtils.isEmpty(ip_num)){
        		et_input_ipnum.setText(ip_num);
        		tv_ipnum.setText("你的IP号码是："+ip_num);        		
        	}        	
        	
           	btn_ipnum.setOnClickListener(new OnClickListener() {
    				@Override
    				public void onClick(View v) {
                      	    Editor editor = sp.edit();
    					    ip_num = et_input_ipnum.getText().toString().trim();
    					    if(TextUtils.isEmpty(ip_num)){
    					    	Toast.makeText(getApplicationContext(), "你输入的IP号码是空的哦！已关闭IP拨号功能", Toast.LENGTH_SHORT).show();
    					    	editor.clear();
    					    	editor.commit();
    					    }  else  {
                          	editor.putString("IP_NUM", ip_num);                      	
                          	editor.commit();
                          	Toast.makeText(getApplicationContext(), "IP"+ip_num+"拨打功能开启了", Toast.LENGTH_SHORT).show();
    				    }
    				}
    			});     
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

			AdminUtils.AdminMenu(IPDialerActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
