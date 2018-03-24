package com.onezao.onezao.login1114;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSpActivity extends Activity  implements  OnClickListener{
         private EditText et_login_username;
		private EditText et_login_password;
		private CheckBox cb_login_rem;
		private Button btn_login_in;
		private String name = "zneo.txt";
		private String dUsername = "";
		private String dPassword ="";
		private SharedPreferences sp;


		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_login_1114);
        	
        	//找到控件
        	et_login_username = (EditText) findViewById(R.id.et_login_username);
        	et_login_password = (EditText) findViewById(R.id.et_login_password);
        	cb_login_rem = (CheckBox) findViewById(R.id.cb_login_rem);
        	btn_login_in = (Button) findViewById(R.id.btn_login_in);
        	
        	btn_login_in.setOnClickListener(this);
        	//获取Sp对象，第一个参数是SP文件保存的名字，第二个参数是保存的模式
        	//如果访问的文件不存在，在编辑保存生效之后会创建该文件。
        	
        	sp = getSharedPreferences(name, MODE_APPEND);
        	//获取保存的内容
        	boolean spCheck = sp.getBoolean("isChecked", false);
        	String spUsername = sp.getString("username", dUsername);
        	String spPassword = sp.getString("password", dPassword);
        	if(spCheck){        		
        		//将内容设置到编辑框
        		et_login_username.setText(spUsername);
        		et_login_password.setText(spPassword);
        		cb_login_rem.setChecked(spCheck);
        	}  else {
        		//将用户名内容设置到编辑框
        		et_login_username.setText(spUsername);
        	}
        }

		@Override
		public void onClick(View v) {
                 //1.获取用户名  和  密码
                String username = et_login_username.getText().toString().trim();
                String password  = et_login_password.getText().toString().trim();
        	    boolean  checked = cb_login_rem.isChecked();
                //2.非空判断
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                	Toast.makeText(LoginSpActivity.this, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();  
                	}   else  {
                		//获取编辑器，存入用户名和密码,以及checkbox勾选与否的内容，是以键值对的方式存储，最后记得commit()
                		Editor editor = sp.edit();
                		editor.putString("username", username);
                		editor.putString("password", password);
                		editor.putBoolean("isChecked", checked);
                		editor.commit();
                		if(!checked){
                			Toast.makeText(LoginSpActivity.this, ZaoUtils.selectCB, Toast.LENGTH_LONG).show();  
                		}  else {
        						Toast.makeText(LoginSpActivity.this, ZaoUtils.loginToastSaveSucc, Toast.LENGTH_LONG).show();
                		}
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

	   			AdminUtils.AdminMenu(LoginSpActivity.this, item);
	   			return super.onOptionsItemSelected(item);
	   	 }
}
