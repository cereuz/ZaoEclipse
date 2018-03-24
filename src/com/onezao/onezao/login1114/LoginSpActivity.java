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
        	
        	//�ҵ��ؼ�
        	et_login_username = (EditText) findViewById(R.id.et_login_username);
        	et_login_password = (EditText) findViewById(R.id.et_login_password);
        	cb_login_rem = (CheckBox) findViewById(R.id.cb_login_rem);
        	btn_login_in = (Button) findViewById(R.id.btn_login_in);
        	
        	btn_login_in.setOnClickListener(this);
        	//��ȡSp���󣬵�һ��������SP�ļ���������֣��ڶ��������Ǳ����ģʽ
        	//������ʵ��ļ������ڣ��ڱ༭������Ч֮��ᴴ�����ļ���
        	
        	sp = getSharedPreferences(name, MODE_APPEND);
        	//��ȡ���������
        	boolean spCheck = sp.getBoolean("isChecked", false);
        	String spUsername = sp.getString("username", dUsername);
        	String spPassword = sp.getString("password", dPassword);
        	if(spCheck){        		
        		//���������õ��༭��
        		et_login_username.setText(spUsername);
        		et_login_password.setText(spPassword);
        		cb_login_rem.setChecked(spCheck);
        	}  else {
        		//���û����������õ��༭��
        		et_login_username.setText(spUsername);
        	}
        }

		@Override
		public void onClick(View v) {
                 //1.��ȡ�û���  ��  ����
                String username = et_login_username.getText().toString().trim();
                String password  = et_login_password.getText().toString().trim();
        	    boolean  checked = cb_login_rem.isChecked();
                //2.�ǿ��ж�
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                	Toast.makeText(LoginSpActivity.this, ZaoUtils.loginToast, Toast.LENGTH_LONG).show();  
                	}   else  {
                		//��ȡ�༭���������û���������,�Լ�checkbox��ѡ�������ݣ����Լ�ֵ�Եķ�ʽ�洢�����ǵ�commit()
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

	   			AdminUtils.AdminMenu(LoginSpActivity.this, item);
	   			return super.onOptionsItemSelected(item);
	   	 }
}
