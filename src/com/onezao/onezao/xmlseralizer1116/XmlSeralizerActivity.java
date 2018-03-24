package com.onezao.onezao.xmlseralizer1116;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class XmlSeralizerActivity extends Activity {
          private ArrayList<SMS> smsList;
		private EditText et_sms_show;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_xmlseralizer_1116);
        	
        	smsList = initData();
        	
        	et_sms_show = (EditText) findViewById(R.id.et_sms_show);
        	et_sms_show.setText("zneo@qq.com");
        }

		private ArrayList<SMS>  initData() {
			  ArrayList<SMS>  smsList = new ArrayList<SMS>();
			  for(int  i =0; i < 9; i++){
				  SMS sms = new SMS();
				  sms.from = i  +"_ "+ "10000"  ;
				  sms.content =i  +"_ "+ "content";
				  sms.time =i  +"_ "+   ZaoUtils.getSystemTime() ;
				  smsList.add(sms);
			  }
			  
			  for (SMS  sms1 : smsList) {
				System.out.println(sms1);
			}
			  return  smsList;
		}
		
		//ͨ��StringBuilder�Լ���ƴxml
		public void saveSMS(View view){
			    StringBuilder  sb = new  StringBuilder();
			    //дxml���ĵ�����
			    sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			    //��ƴxml�ļ���
			    sb.append("<SMSList>");
			    for(SMS  sms : smsList){
			    	sb.append("<SMS>");
			    	
			    	sb.append("<from>");
			    	sb.append(sms.from);
			    	sb.append("</from>");
			    	
			    	sb.append("<content>");
			    	sb.append(sms.content);
			    	sb.append("</content>");
			    	
			    	sb.append("<time>");
			    	sb.append(sms.time);
			    	sb.append("</time>");
			    	
			    	sb.append("</SMS>");
			    }
			    sb.append("</SMSList>");
			    
			    String  xml = sb.toString();
		        // �ڴ���
			    XmlUtils.XmlSeralizerIn(XmlSeralizerActivity.this, xml);
			    //SD����
			    XmlUtils.XmlSeralizerSD(XmlSeralizerActivity.this, xml);
		}
		
		
		//ͨ��API���л�xml
		public  void seralizerSMS(View  view){
			//��ȡXml���л���
			XmlSerializer seralizer = Xml.newSerializer();
			try {
				//�����л�������һ�����
//				seralizer.setOutput(openFileOutput("smsSeralizer.xml", MODE_APPEND), "utf-8");
				//��׷�ӵı��浽SD��
				seralizer.setOutput(new FileOutputStream(new File(XmlUtils.pathSD+XmlUtils.seralizerFileSD),true),  "utf-8");
				//д�ĵ���ʼ������  <?xml version="1.0" encoding="utf-8" standalone="true">
				seralizer.startDocument("utf-8", true);
				//��һ�����������ƿռ䣬�����ǰ�ĵ���ĳ��schemaԼ���ʹ���һ�����ƿռ䣬û��Լ���������null
				//<SMSList>
				seralizer.startTag(null, "SMSList");
				//д��ʼ��ǩ�ͽ�����ǩ  SMS
				for (SMS  sms : smsList) {
					seralizer.startTag(null, "SMS");
					
					seralizer.startTag(null, "from");
					seralizer.text(sms.from);
					seralizer.endTag(null, "from");
					
					seralizer.startTag(null, "content");
					seralizer.text(sms.content);
					seralizer.endTag(null, "content");
					
					seralizer.startTag(null, "time");
					seralizer.text(sms.time);
					seralizer.endTag(null, "time");
			
				   seralizer.endTag(null, "SMS");
				}
				seralizer.endTag(null, "SMSList");
				seralizer.endDocument();
				Toast.makeText(XmlSeralizerActivity.this, ZaoUtils.saveSucc, Toast.LENGTH_LONG).show();
				
			} catch (IllegalArgumentException | IllegalStateException 	| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//����XML
		public void parseSMS(View view){
			ArrayList< SMS>  smsList = null;
			SMS sms = null;
			//��ȡ������
			XmlPullParser pullParser = Xml.newPullParser();
			try {
				//������Ҫ�������ļ�·��
//				pullParser.setInput(openFileInput("smsSeralizer.xml"),"utf-8");
				pullParser.setInput(new FileInputStream(new File(XmlUtils.pathSD+XmlUtils.seralizerFileSD)),"utf-8");
				//��ʼ����
				int eventType = pullParser.getEventType();
				while(eventType != XmlPullParser.END_DOCUMENT){
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if("SMSList".equals(pullParser.getName())){
							smsList = new ArrayList<SMS>();
						}  else if ("SMS".equals(pullParser.getName())){
							sms = new SMS();
						}  else if ("from".equals(pullParser.getName())){
							sms.from =pullParser.nextText();
						}  else if ("content".equals(pullParser.getName())){
							sms.content  = pullParser.nextText();
						}  else if ("time".equals(pullParser.getName())){
							sms.time  = pullParser.nextText();
						}
						break;
					case XmlPullParser.END_TAG:	
						if ("SMS".equals(pullParser.getName())){
							smsList.add(sms);
							Toast.makeText(getApplicationContext(), "End_TAG", Toast.LENGTH_LONG).show();
						}
						break;
					}
					eventType = pullParser.next();					
				}
				final String text = smsList.toString();
				runOnUiThread(new Runnable() {					
					@Override
					public void run() {
						et_sms_show.setText(text);						
					}
				});
			} catch (XmlPullParserException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

			AdminUtils.AdminMenu(XmlSeralizerActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
		
}
