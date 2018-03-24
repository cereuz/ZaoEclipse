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
			//�������������̲߳���
			new Thread(new Runnable() {				
				@Override
				public void run() {			
                     //��ȡURL
			   final String path = et_url.getText().toString();
			   try {
			   URL  url = new URL(path);
			    //����URL����
				HttpURLConnection   urlConnection =(HttpURLConnection)url.openConnection();
				//��������ʽ����Ҫ��д��Ĭ����GET����
				urlConnection.setRequestMethod("GET");
				//�������ӳ�ʱ��ʱ��
				urlConnection.setConnectTimeout(10000);
				//��ȡ��Ӧ��   , �ж���Ӧ�룬�����200���ͱ�ʾ��ȡ�ɹ�
				int responseCode = urlConnection.getResponseCode();
				if(responseCode==200){
					//����������Ӧ����
					InputStream inputStream = urlConnection.getInputStream();
                    final String result = HtmlCodeUtils.getStringFromStream(inputStream);
                  
					//�޸�UIֻ�������߳�
					runOnUiThread(new Runnable() {
						public void run() {
							//ͨ��TextView   չʾ��Ӧ������
							Toast.makeText(getApplicationContext(), "result", Toast.LENGTH_SHORT).show();
		                    tv_code.setText(result);
						}
					});
					
				}  else {
					//�޸�UIֻ�������߳�
					runOnUiThread(new Runnable() {
						public void run() {
							//ͨ��TextView   չʾ��Ӧ������
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
			AdminUtils.AdminMenu(HtmlCodeActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
