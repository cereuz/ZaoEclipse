package com.onezao.onezao.smsbackup1225;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import com.onezao.onezao.xmlseralizer1116.XmlUtils;
import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SmsBackupActivity extends Activity {
	private  ArrayList<Sms>   smsList  =  new  ArrayList<>();
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_smsbackup_1225);
        }
           
           public void querySms(View view){
        	   //获取内容解析者
        	   ContentResolver resolver = getContentResolver();
        	   //通过查看安卓系统源代码，smsProvider，发现子路径传null代表查询所有的短信。
        	   Uri uri = Uri.parse("content://sms");
        	   String[] projection = {"address","date","body"};
        	   Cursor cursor = resolver.query(uri, projection, null, null, null);
        	   while(cursor.moveToNext()){
        		   Sms  sms =  new  Sms();
        		   String address = cursor.getString(0);
        		   String date = cursor.getString(1);
        		   String date1 = ZaoUtils.tranTime(date);
        		   String body = cursor.getString(2);
        		   sms.setAddress(address);
        		   sms.setDate(date1);
        		   sms.setBody(body);
        		   smsList.add(sms);
        		   
        		   String text = "发件人："+address + "\n时间：" + date1 + "\n正文："+body;
        		   Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        	   }
           }
           
           public void  backupsms(View  view){
        	   //获取Xml序列化器
        	   XmlSerializer  seralizer = Xml.newSerializer();
        	   try {
        		   String path = Environment.getExternalStorageDirectory().getPath();
        		   //给Xml序列化器设置输出
//				seralizer.setOutput(openFileOutput(path+"/sms1225.xml", MODE_PRIVATE),"utf-8");
        		   File file = new File(path+"/sms1225.xml");
				seralizer.setOutput(new FileOutputStream(file,true),  "utf-8");
				//写文档的开始标记
				if(!file.exists()){
				seralizer.startDocument("utf-8", true);
				}
				seralizer.startTag(null, "SmsList");
				for(Sms  sms :  smsList){
					// sms
					seralizer.startTag(null, "sms");
					//address
					seralizer.startTag(null, "address");
					seralizer.text(sms.getAddress());
					seralizer.endTag(null, "address");
					//date
					seralizer.startTag(null, "date");
					seralizer.text(sms.getDate());
					seralizer.endTag(null, "date");
					//body
					seralizer.startTag(null, "body");
					seralizer.text(sms.getBody());
					seralizer.endTag(null, "body");
					
					seralizer.endTag(null, "sms");
				}
				seralizer.endTag(null, "SmsList");
				seralizer.endDocument();
				Toast.makeText(getApplicationContext(), "备份成功！"+ZaoUtils.getSystemTime(), Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				e.printStackTrace();
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

   			AdminUtils.AdminMenu(SmsBackupActivity.this, item);
   			return super.onOptionsItemSelected(item);
   	 }
}
