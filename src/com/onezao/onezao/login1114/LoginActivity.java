package com.onezao.onezao.login1114;

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
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity  implements  OnClickListener{
           private EditText et_login_username;
		private EditText et_login_password;
		private CheckBox cb_login_rem;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_1114);
            
            et_login_username = (EditText)findViewById(R.id.et_login_username);
            et_login_password = (EditText)findViewById(R.id.et_login_password);
            cb_login_rem = (CheckBox) findViewById(R.id.cb_login_rem);
            
            Button  btn_login_in  = (Button)findViewById(R.id.btn_login_in);
            btn_login_in.setOnClickListener(this);
            Button  btn_login_context  = (Button)findViewById(R.id.btn_login_context);
            btn_login_context.setOnClickListener(this);
            Button  btn_login_context2  = (Button)findViewById(R.id.btn_login_context2);
            btn_login_context2.setOnClickListener(this);
            Button  btn_login_sdcard  = (Button)findViewById(R.id.btn_login_sdcard);
            btn_login_sdcard.setOnClickListener(this);
            Button  btn_login_sdcard_context  = (Button)findViewById(R.id.btn_login_sdcard_context);
            btn_login_sdcard_context.setOnClickListener(this);
            
            
            
            //获取用户保存的信息   ,三种不同的方式获取文件信息。分别使用。
//            String[]  info = LoginUtils.readInfo(LoginUtils.urlCo);
//           String[]  info = LoginUtils.readInfoByContext(LoginActivity.this);
//           String[]  info = LoginUtils.readInfoByContext2(LoginActivity.this);
//            String[]  info = LoginUtils.readInfo(LoginUtils.urlSD);
            String[]  info = LoginUtils.readInfoByEnvironment(LoginActivity.this,LoginUtils.fileName);
            //如果返回不为空，说明已经成功获取到保存的信息，将信息显示到EditText上
            if(info != null){
            	et_login_username.setText(info[0]);
            	et_login_password.setText(info[1]);
               }
           }
		
         //监听按钮点击事件  
   		@Override
   		public void onClick(View v) {
   			       String username = et_login_username.getText().toString().trim();
   			       String password = et_login_password.getText().toString().trim();
					switch (v.getId()) {
					//1.0 通过固定路径保存用户名和密码保存到手机内存
					case R.id.btn_login_in:
						LoginUtils.loginMT(LoginActivity.this, username, password, cb_login_rem,LoginUtils.urlCo);
						break;
						
                    //2.0 通过Context保存或者获取用户名和密码
					case R.id.btn_login_context:
						LoginUtils.loginMTByContext(LoginActivity.this, username, password, cb_login_rem);
						    break;
						    
				    //3.0 通过Context保存或者获取用户名和密码
					case R.id.btn_login_context2:
						LoginUtils.loginMTByContext(LoginActivity.this, username, password, cb_login_rem);
						break;
						
					//4.0通过SDcard保存用户名和密码，
					case R.id.btn_login_sdcard:
						LoginUtils.loginMT(LoginActivity.this, username, password, cb_login_rem,LoginUtils.urlSD );
						break;
					
						//5.0通过Environment  获取SDcard路径来保存用户名和密码
					case R.id.btn_login_sdcard_context:
						LoginUtils.loginMTByEnvironment(LoginActivity.this, username, password, cb_login_rem);
						break;
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
   			AdminUtils.AdminMenu(LoginActivity.this, item);
   			return super.onOptionsItemSelected(item);
   	 }
}
