package com.onezao.onezao.smssender1209;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SmsSenderActivity extends Activity implements OnClickListener {
	   public final int RequestCode = 1;
	    private final int RequestCode2 = 2;
	    EditText et_smssender_num;
	    EditText et_smssender_context;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_sms_sender_1209);

	       et_smssender_num = (EditText)findViewById(R.id.et_smssender_num);
	       et_smssender_context = (EditText)findViewById(R.id.et_smssender_context);

	      Button btn_smssender_select = (Button) findViewById(R.id.btn_smssender_select);
	      Button btn_smssender_context = (Button) findViewById(R.id.btn_smssender_context);
	      Button btn_smssender_send = (Button) findViewById(R.id.btn_smssender_send);
	      btn_smssender_select.setOnClickListener(this);
	      btn_smssender_context.setOnClickListener(this);
	      btn_smssender_send.setOnClickListener(this);
	    }

	    @Override
	    public void onClick(View view) {
	        int id = view.getId();
	         switch (id){
	             case R.id.btn_smssender_select:
	                 Intent intent =  new Intent(getApplicationContext(),ContactActivity.class);
	                 startActivityForResult(intent,RequestCode);
	                 break;
	             case R.id.btn_smssender_context:
	                 Intent intent2 = new Intent(getApplicationContext(),ReplyActivity.class);
	                 startActivityForResult(intent2,RequestCode2);
	                 break;
	             case R.id.btn_smssender_send:
	                 String num_send = et_smssender_num.getText().toString().trim();
	                 String content_send = et_smssender_context.getText().toString();
	                 //设置短信服务中心的号码，使用默认号码传入null
	                 String scAddress = null;
	                 if(TextUtils.isEmpty(num_send) || TextUtils.isEmpty(content_send)){
	                     Toast.makeText(getApplicationContext(),"请输入电话号码和短信内容！",Toast.LENGTH_SHORT).show();
	                 }  else {
	                	 num_send = num_send.split("：")[1];
		                 //发送短信的管理者，需要收费的
		                 SmsManager manager = SmsManager.getDefault();
	                     //发送回执
	                     PendingIntent sendIntent = null;
	                     //对方接收回执
	                     PendingIntent deliveryIntent = null;
	           //          public void sendTextMessage(String destinationAddress, String scAddress,String text, PendingIntent sentIntent, .PendingIntent deliveryIntent)
	                     manager.sendTextMessage(num_send,scAddress,content_send,sendIntent,deliveryIntent);
	                     Toast.makeText(getApplicationContext(),num_send + "\n" + content_send,Toast.LENGTH_SHORT).show();
	                 }
	                 break;
	         }
	    }

	    //当开启的Activity关闭之后，会走这个方法
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        //但用户直接点击返回，或者没有获取到data数据，就直接return
	        if(data == null){
	            return;
	        }
	        switch (requestCode){
	            case RequestCode:
	                String name = data.getStringExtra("name");
	                String phone = data.getStringExtra("phone");
	                et_smssender_num.setText(name +"："+ phone);
	                break;
	            case RequestCode2:
	                String content = data.getStringExtra("item");
	                et_smssender_context.setText(content);
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

			AdminUtils.AdminMenu(SmsSenderActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
	}
