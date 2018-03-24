package com.onezao.onezao.htmlcodeviewer1124;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.onezao.onezao.dialer1113.DialerActivity0004;
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
import android.widget.TextView;
import android.widget.Toast;

public class HtmlCodeActivity extends Activity  implements OnClickListener {
          private EditText et_url;
		private Button btn_show;
		private TextView tv_code;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_htmlcode_1124);
        	et_url = (EditText) findViewById(R.id.et_url);
        	btn_show = (Button) findViewById(R.id.btn_show);
        	tv_code = (TextView) findViewById(R.id.tv_code);
        	
        	btn_show.setOnClickListener(this);
        }

		@Override
		public void onClick(View v) {
			Toast.makeText(this,et_url.getText().toString(), Toast.LENGTH_LONG).show();
			//联网不能在主线程操作
			new Thread(new Runnable() {				
				@Override
				public void run() {			
                     //获取URL
			   final String path = et_url.getText().toString();
			   try {
			   URL  url = new URL(path);
			    //拿着URL联网
				HttpURLConnection   urlConnection =(HttpURLConnection)url.openConnection();
				//设置请求方式，需要大写，默认是GET请求
				urlConnection.setRequestMethod("GET");
				//设置连接超时的时间
				urlConnection.setConnectTimeout(10000);
				//获取响应码   , 判断响应码，如果是200，就表示获取成功
				int responseCode = urlConnection.getResponseCode();
				if(responseCode==200){
					//联网后获得响应内容
					InputStream inputStream = urlConnection.getInputStream();
                    final String result = HtmlCodeUtils.getStringFromStream(inputStream);
                  
					//修改UI只能在主线程
					runOnUiThread(new Runnable() {
						public void run() {
							//通过TextView   展示相应的内容
							Toast.makeText(getApplicationContext(), "result", Toast.LENGTH_SHORT).show();
		                    tv_code.setText(result);
						}
					});
					
				}  else {
					//修改UI只能在主线程
					runOnUiThread(new Runnable() {
						public void run() {
							//通过TextView   展示相应的内容
							Toast.makeText(getApplicationContext(), "result", Toast.LENGTH_SHORT).show();
		                    tv_code.setText("result");
						}
					});
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				}
				}).start();
			
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
			AdminUtils.AdminMenu(HtmlCodeActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
