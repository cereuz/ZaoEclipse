package com.onezao.onezao.insertcontact0101;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertContactActivity extends Activity {
           private EditText et_insertcontact_name;
		private EditText et_insertcontact_phone;
		private EditText et_insertcontact_email;
		private EditText et_insertcontact_address;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_insertcontact_0101);
        	
        	et_insertcontact_name = (EditText) findViewById(R.id.et_insertcontact_name);
        	et_insertcontact_phone = (EditText) findViewById(R.id.et_insertcontact_phone);
        	et_insertcontact_email = (EditText) findViewById(R.id.et_insertcontact_email);
        	et_insertcontact_address = (EditText) findViewById(R.id.et_insertcontact_address);
        }
		
		public void  save(View view){
			//1.获取内容解析者
			ContentResolver resolver = getContentResolver();
			//2. 获取Uri
			// 查询raw_caontact表的uri
			Uri raw_contact_uri = Uri.parse("content://com.android.contacts/raw_contacts");
			// 查询data表的uri
			Uri data_uri = Uri.parse("content://com.android.contacts/data");
			//3.确定要插入到raw_contact表中id的值      查询contact_id这一列
			Cursor cursor = resolver.query(raw_contact_uri, new String[]{"contact_id"}, null, null, null);
			//4.获取查询的结果 一共有多少行，然后使用结果加上1  作为新插入的contact_id的值
			int count = cursor.getCount();
			//5.插入数据
			ContentValues values = new ContentValues();
			values.put("contact_id", count+1);
             resolver.insert(raw_contact_uri, values);
             //6.操作data表，插入对应的内容
             
             
             String et_name = et_insertcontact_name.getText().toString().trim();
             String et_phone = et_insertcontact_phone.getText().toString().trim();
             String et_email = et_insertcontact_email.getText().toString().trim();
             String et_address = et_insertcontact_address.getText().toString().trim();
	      
             ContentValues   nameValues = new ContentValues();
             nameValues.put("raw_contact_id", count+1);
             nameValues.put("data1", et_name);
             nameValues.put("mimetype", "vnd.android.cursor.item/name");
             resolver.insert(data_uri, nameValues);
             
             ContentValues   phoneValues = new ContentValues();
             phoneValues.put("raw_contact_id", count+1);
             phoneValues.put("data1", et_name);
             phoneValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
             resolver.insert(data_uri, phoneValues);
             
             ContentValues   emailValues = new ContentValues();
             emailValues.put("raw_contact_id", count+1);
             emailValues.put("data1", et_name);
             emailValues.put("mimetype",  "vnd.android.cursor.item/email_v2");
             resolver.insert(data_uri, emailValues);
             
             ContentValues   addValues = new ContentValues();
             addValues.put("raw_contact_id", count+1);
             addValues.put("data1", et_name);
             addValues.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
             resolver.insert(data_uri, addValues);
		}
}
