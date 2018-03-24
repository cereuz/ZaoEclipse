package com.onezao.onezao.htmlcodeviewer1124;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.onezao.onezao.dialer1113.DialerActivity0004;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HtmlCodeHandlerActivity extends Activity  implements  OnClickListener{
        private EditText et_url;
		private Button btn_show;
		private Button btn_show_cache;
		private TextView tv_code;
		//�����̴߳���һ��handler�����handler����Ϣ�ͻᷢ�͵����̵߳���Ϣ�����С�
		private Handler  handler = new Handler(){
			   public void handleMessage(android.os.Message msg) {
				   // ����ͨ��handler�����߳��д�����Ϣ����������handleMessage�����д�����Ϣ��
				 String  temp = (String) msg.obj;
				 tv_code.setText(temp);
				 Toast.makeText(getApplicationContext(), "ʹ������������ʾxml�ĵ�", Toast.LENGTH_SHORT).show();
			   };
		};
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_htmlcode_1124);
        	et_url = (EditText) findViewById(R.id.et_url);
        	btn_show = (Button) findViewById(R.id.btn_show);
        	btn_show_cache = (Button) findViewById(R.id.btn_show_cache);
        	tv_code = (TextView) findViewById(R.id.tv_code);
        	
        	et_url.setText("https://zhihu.com/rss");
        	btn_show.setOnClickListener(this);
        	btn_show_cache.setOnClickListener(this);        	
        }

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_show:
				htmlCodeHandler();
				break;

			case R.id.btn_show_cache:
				htmlCodeHandlerCache();
				break;
			}
			  
		}

	

		//��ֱ�Ӽ������ݡ�
		private void htmlCodeHandler() {
			//**********��������Ҫ�����߳�**************
			new Thread(new Runnable() {					
					@Override
			     public void run() {
	             //��ȡURL
				String path = et_url.getText().toString();	
				try {
					URL url = new URL(path);				
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
	                    String result = HtmlCodeUtils.getStringFromStream(inputStream);
	                    //����ֱ���޸Ľ��棬����Ҫ֪ͨ���̣߳���ȡ�������ݣ����Ұ����ݶ������̣߳������߳�����ʾ��
	                    //��ͨ��handler������ϢMessage   ��Ҫ���½����õ������ݣ�ͨ��Message  Я����handler�����͵���Ϣ���У������߳�������Ȼ����ִ�С�
	                    Message  msg = new Message();  //������Ϣ����
	                    msg.obj = result;     //Я������
	                    handler.sendMessage(msg);//handler���÷�������
	                    
/*						//************�����ڷ�UI�߳��޸�View    !!**************
	                    //ͨ��TextView   չʾ��Ӧ������
	                    tv_code.setText(result);*/
	           	}
				}    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
				}
			}).start();
		}
		
		//�������ļ����ݡ�
		private void htmlCodeHandlerCache() {
			final File  file  =  new File(getCacheDir() + "zao.xml");
			if(file.exists() && file.length() > 0){
	                 String readFile = ZaoUtils.readFile(file.getAbsolutePath());
	                 tv_code.setText(readFile);
	                 Toast.makeText(this, "ʹ�û����xml�ļ�", Toast.LENGTH_SHORT).show();
			}
			//**********��������Ҫ�����߳�**************
			new Thread(new Runnable() {					
					@Override
			     public void run() {
	             //��ȡURL
				String path = et_url.getText().toString();	
				try {
					URL url = new URL(path);				
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
						FileOutputStream   fos  =  new  FileOutputStream(file);
						byte[]  buffer  =  new byte[1024];
						int  len  =  -1;
						while((len = inputStream.read(buffer)) != -1 ){
							fos.write(buffer,0,len);
						}
						fos.close();
						inputStream.close();
						//�����ļ������Ǵ�������ص����ĵ���ʱ�򣬸����ĵ����ڴ濨��
						ZaoUtils.copyFile(file.getAbsolutePath(), Environment.getExternalStorageDirectory().getPath()+"/Z"+ZaoUtils.getSystemTime()+".xml");
						
//	                    String result = HtmlCodeUtils.getStringFromStream(inputStream);
	                    String result = HtmlCodeUtils.getStringFromPath(path);
	                    //����ֱ���޸Ľ��棬����Ҫ֪ͨ���̣߳���ȡ�������ݣ����Ұ����ݶ������̣߳������߳�����ʾ��
	                    //��ͨ��handler������ϢMessage   ��Ҫ���½����õ������ݣ�ͨ��Message  Я����handler�����͵���Ϣ���У������߳�������Ȼ����ִ�С�
	                    Message  msg = new Message();  //������Ϣ����
	                    msg.obj = result;     //Я������
	                    handler.sendMessage(msg);//handler���÷�������
	                    
/*						//************�����ڷ�UI�߳��޸�View    !!**************
	                    //ͨ��TextView   չʾ��Ӧ������
	                    tv_code.setText(result);*/
	           	}
				}    catch (IOException e) {
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

			AdminUtils.AdminMenu(HtmlCodeHandlerActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
} 
	
