package com.onezao.onezao.rpcalc1207;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RpCalcActivity extends Activity  implements OnClickListener{
         private EditText et_rpcalc_name;
		private RadioGroup rg_rpcalc_gender;
		private Button btn_calc_jump;
		private String GENDER_MALE = "男";
		private String GENDER_FEMALE  = "女";
		private String GENDER_NONE ="无";

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_rpcalc_1207);
        	
        	et_rpcalc_name = (EditText) findViewById(R.id.et_rpcalc_name);
        	rg_rpcalc_gender = (RadioGroup) findViewById(R.id.rg_rpcalc_gender);
        	
        	btn_calc_jump = (Button) findViewById(R.id.btn_calc_jump);
        	btn_calc_jump.setOnClickListener(this);
        }

		@Override
		public void onClick(View v) {
            //获取姓名   
			String rpcalc_name = et_rpcalc_name.getText().toString().trim();
            //获取性别 
			String  gender  =  null; 
			int radioButtonId = rg_rpcalc_gender.getCheckedRadioButtonId();
            switch (radioButtonId) {
			case R.id.rb_male:
				gender =  GENDER_MALE;
				break;
				
			case R.id.rb_female:	
				gender = GENDER_FEMALE;
                break;
                
			case R.id.rb_none:
				gender = GENDER_NONE;
				break;
			}
               if(TextUtils.isEmpty(rpcalc_name) || TextUtils.isEmpty(gender)){
            	   Toast.makeText(getApplicationContext(), ZaoUtils.SELECTGENDERANDNAME, Toast.LENGTH_SHORT).show();
               }  else  {
              //要把姓名和性别信息传递到下一个页面
               Intent  intent =  new Intent(getApplicationContext(),ShowRpCalcActivity.class);
               intent.putExtra("name", rpcalc_name);
               intent.putExtra("gender", gender);
               //启动下一个activity
               startActivity(intent);              
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

			AdminUtils.AdminMenu(RpCalcActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
