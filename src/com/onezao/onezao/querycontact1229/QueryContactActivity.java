package com.onezao.onezao.querycontact1229;

import java.util.ArrayList;

import com.onezao.onezao.zao.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class QueryContactActivity extends Activity {
	private ArrayList<Contact>   clist = new ArrayList<Contact>();
	private TextView tv_readcontact1229_contact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_querycontact_1229);
		tv_readcontact1229_contact = (TextView) findViewById(R.id.tv_readcontact1229_contact);
	}

	public void query(View view) {
		int n = 0;
		clist.clear();
		// 1.获取内容解析者
		ContentResolver resolver = getContentResolver();
		// 通过resolver查询数据
		//2.确定要查询的表  使用的Uri
		// 查询raw_caontact表的uri
		Uri raw_contact_uri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		// 查询data表的uri
		Uri data_uri = Uri.parse("content://com.android.contacts/data");
		//3.先查询 raw_contact  只查询raw_contact这一列
		Cursor cursor = resolver.query(raw_contact_uri,
				new String[] { "contact_id" }, null, null, null);
		while (cursor.moveToNext()) {
			//4.每查询出一个数据对应一个联系人，用ID保存查询结果
			String id = cursor.getString(0);
			//5. 一个ID对应一个联系人，每查询一个ID值，就对应一个联系人
			Contact contact = new Contact();
			contact.setId(id);
			if (id == null) {
				Toast.makeText(getApplicationContext(), "ID为空！！" + n++, 
						Toast.LENGTH_SHORT).show();
			} else {
				System.out.println("ID：" + id);
				// Toast.makeText(getApplicationContext(),"ID："+ id,
				// Toast.LENGTH_LONG).show();
				String[] projection = { "data1", "mimetype" };
				//6.使用查询出来的ID作为条件，查询data表，要查询的列“data1”  ，“mimetype”
				Cursor cursor2 = resolver.query(data_uri, projection,
						"raw_contact_id = ?", new String[] { id }, null);
				while (cursor2.moveToNext()) {
					//7.获取每一列中的data1数据  和  mimetype  数据
					String result = cursor2.getString(0);
					String type = cursor2.getString(1);
                    //8.通过mimetype的数据确定data1数据，确定要保存到javaBean的哪一个字段中
					if ("vnd.android.cursor.item/phone_v2".equals(type)) {
						Toast.makeText(getApplicationContext(),
								"result：" + result + "\ntype：" + type,
								Toast.LENGTH_LONG).show();
						contact.setPhone(result);
					} else if ("vnd.android.cursor.item/email_v2".equals(type)) {
						contact.setEmail(result); 
					} else if ("vnd.android.cursor.item/name".equals(type)) {
						contact.setName(result);
					} else if ("vnd.android.cursor.item/postal-address_v2"
							.equals(type)) {
						contact.setAddress(result);
					}
				}
			}
			//9.while循环结束，说明一个联系人保存的过程已经完成，可以把这个联系人对象放到联系人集合中。                        
			clist.add(contact);
			//10.所有的联系人都保存完了，可以查看保存结果。设置显示出来
			tv_readcontact1229_contact.setText(clist.toString());
/*			for(Contact con : clist){
				Toast.makeText(getApplicationContext(), n++ + "----"+con.toString(), Toast.LENGTH_SHORT).show();
			}*/
		}
	}
	
	
	public void query2(View view) {
		int n = 0;
		clist.clear();
		// 1.获取内容解析者
		ContentResolver resolver = getContentResolver();
		// 通过resolver查询数据
		//2.确定要查询的表  使用的Uri
		// 查询raw_caontact表的uri
		Uri raw_contact_uri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		// 查询data表的uri
		Uri data_uri = Uri.parse("content://com.android.contacts/data");
		//3.先查询 raw_contact  只查询raw_contact这一列   ,如果查询  _id这一列，就会查询到所有的联系人，包括删除和没有删除的
		Cursor cursor = resolver.query(raw_contact_uri,
				new String[] { "_id" }, null, null, null);
		int columnCount = cursor.getColumnCount();
		while (cursor.moveToNext() && n<columnCount) {
			//4.每查询出一个数据对应一个联系人，用ID保存查询结果
			String id = cursor.getString(0);
			//5. 一个ID对应一个联系人，每查询一个ID值，就对应一个联系人
			Contact contact = new Contact();
			contact.setId(id);
			if (id == null) {
				Toast.makeText(getApplicationContext(), "ID为空！！" + n++, 
						Toast.LENGTH_SHORT).show();
			} else {
				System.out.println("ID：" + id);
				// Toast.makeText(getApplicationContext(),"ID："+ id,
				// Toast.LENGTH_LONG).show();
				String[] projection = { "data1", "mimetype" };
				//6.使用查询出来的ID作为条件，查询data表，要查询的列“data1”  ，“mimetype”
				Cursor cursor2 = resolver.query(data_uri, projection,
						"raw_contact_id = ?", new String[] { id }, null);
				while (cursor2.moveToNext()) {
					//7.获取每一列中的data1数据  和  mimetype  数据
					String result = cursor2.getString(0);
					String type = cursor2.getString(1);
                    //8.通过mimetype的数据确定data1数据，确定要保存到javaBean的哪一个字段中
					if ("vnd.android.cursor.item/phone_v2".equals(type)) {
						Toast.makeText(getApplicationContext(),
								"result：" + result + "\ntype：" + type,
								Toast.LENGTH_LONG).show();
						contact.setPhone(result);
					} else if ("vnd.android.cursor.item/email_v2".equals(type)) {
						contact.setEmail(result); 
					} else if ("vnd.android.cursor.item/name".equals(type)) {
						contact.setName(result);
					} else if ("vnd.android.cursor.item/postal-address_v2"
							.equals(type)) {
						contact.setAddress(result);
					}
				}
			}
			//9.while循环结束，说明一个联系人保存的过程已经完成，可以把这个联系人对象放到联系人集合中。                        
			clist.add(contact);
			//10.所有的联系人都保存完了，可以查看保存结果。设置显示出来
			tv_readcontact1229_contact.setText(clist.toString());
/*			for(Contact con : clist){
				Toast.makeText(getApplicationContext(), n++ + "----"+con.toString(), Toast.LENGTH_SHORT).show();
			}*/
		}
	}
}
