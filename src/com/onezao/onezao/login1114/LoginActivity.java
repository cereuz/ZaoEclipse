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
            
            
            
            //��ȡ�û��������Ϣ   ,���ֲ�ͬ�ķ�ʽ��ȡ�ļ���Ϣ���ֱ�ʹ�á�
//            String[]  info = LoginUtils.readInfo(LoginUtils.urlCo);
//           String[]  info = LoginUtils.readInfoByContext(LoginActivity.this);
//           String[]  info = LoginUtils.readInfoByContext2(LoginActivity.this);
//            String[]  info = LoginUtils.readInfo(LoginUtils.urlSD);
            String[]  info = LoginUtils.readInfoByEnvironment(LoginActivity.this,LoginUtils.fileName);
            //������ز�Ϊ�գ�˵���Ѿ��ɹ���ȡ���������Ϣ������Ϣ��ʾ��EditText��
            if(info != null){
            	et_login_username.setText(info[0]);
            	et_login_password.setText(info[1]);
               }
           }
		
         //������ť����¼�  
   		@Override
   		public void onClick(View v) {
   			       String username = et_login_username.getText().toString().trim();
   			       String password = et_login_password.getText().toString().trim();
					switch (v.getId()) {
					//1.0 ͨ���̶�·�������û��������뱣�浽�ֻ��ڴ�
					case R.id.btn_login_in:
						LoginUtils.loginMT(LoginActivity.this, username, password, cb_login_rem,LoginUtils.urlCo);
						break;
						
                    //2.0 ͨ��Context������߻�ȡ�û���������
					case R.id.btn_login_context:
						LoginUtils.loginMTByContext(LoginActivity.this, username, password, cb_login_rem);
						    break;
						    
				    //3.0 ͨ��Context������߻�ȡ�û���������
					case R.id.btn_login_context2:
						LoginUtils.loginMTByContext(LoginActivity.this, username, password, cb_login_rem);
						break;
						
					//4.0ͨ��SDcard�����û��������룬
					case R.id.btn_login_sdcard:
						LoginUtils.loginMT(LoginActivity.this, username, password, cb_login_rem,LoginUtils.urlSD );
						break;
					
						//5.0ͨ��Environment  ��ȡSDcard·���������û���������
					case R.id.btn_login_sdcard_context:
						LoginUtils.loginMTByEnvironment(LoginActivity.this, username, password, cb_login_rem);
						break;
					}
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
   			AdminUtils.AdminMenu(LoginActivity.this, item);
   			return super.onOptionsItemSelected(item);
   	 }
}
