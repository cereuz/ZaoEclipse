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
		// 1.��ȡ���ݽ�����
		ContentResolver resolver = getContentResolver();
		// ͨ��resolver��ѯ����
		//2.ȷ��Ҫ��ѯ�ı�  ʹ�õ�Uri
		// ��ѯraw_caontact���uri
		Uri raw_contact_uri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		// ��ѯdata���uri
		Uri data_uri = Uri.parse("content://com.android.contacts/data");
		//3.�Ȳ�ѯ raw_contact  ֻ��ѯraw_contact��һ��
		Cursor cursor = resolver.query(raw_contact_uri,
				new String[] { "contact_id" }, null, null, null);
		while (cursor.moveToNext()) {
			//4.ÿ��ѯ��һ�����ݶ�Ӧһ����ϵ�ˣ���ID�����ѯ���
			String id = cursor.getString(0);
			//5. һ��ID��Ӧһ����ϵ�ˣ�ÿ��ѯһ��IDֵ���Ͷ�Ӧһ����ϵ��
			Contact contact = new Contact();
			contact.setId(id);
			if (id == null) {
				Toast.makeText(getApplicationContext(), "IDΪ�գ���" + n++, 
						Toast.LENGTH_SHORT).show();
			} else {
				System.out.println("ID��" + id);
				// Toast.makeText(getApplicationContext(),"ID��"+ id,
				// Toast.LENGTH_LONG).show();
				String[] projection = { "data1", "mimetype" };
				//6.ʹ�ò�ѯ������ID��Ϊ��������ѯdata��Ҫ��ѯ���С�data1��  ����mimetype��
				Cursor cursor2 = resolver.query(data_uri, projection,
						"raw_contact_id = ?", new String[] { id }, null);
				while (cursor2.moveToNext()) {
					//7.��ȡÿһ���е�data1����  ��  mimetype  ����
					String result = cursor2.getString(0);
					String type = cursor2.getString(1);
                    //8.ͨ��mimetype������ȷ��data1���ݣ�ȷ��Ҫ���浽javaBean����һ���ֶ���
					if ("vnd.android.cursor.item/phone_v2".equals(type)) {
						Toast.makeText(getApplicationContext(),
								"result��" + result + "\ntype��" + type,
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
			//9.whileѭ��������˵��һ����ϵ�˱���Ĺ����Ѿ���ɣ����԰������ϵ�˶���ŵ���ϵ�˼����С�                        
			clist.add(contact);
			//10.���е���ϵ�˶��������ˣ����Բ鿴��������������ʾ����
			tv_readcontact1229_contact.setText(clist.toString());
/*			for(Contact con : clist){
				Toast.makeText(getApplicationContext(), n++ + "----"+con.toString(), Toast.LENGTH_SHORT).show();
			}*/
		}
	}
	
	
	public void query2(View view) {
		int n = 0;
		clist.clear();
		// 1.��ȡ���ݽ�����
		ContentResolver resolver = getContentResolver();
		// ͨ��resolver��ѯ����
		//2.ȷ��Ҫ��ѯ�ı�  ʹ�õ�Uri
		// ��ѯraw_caontact���uri
		Uri raw_contact_uri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		// ��ѯdata���uri
		Uri data_uri = Uri.parse("content://com.android.contacts/data");
		//3.�Ȳ�ѯ raw_contact  ֻ��ѯraw_contact��һ��   ,�����ѯ  _id��һ�У��ͻ��ѯ�����е���ϵ�ˣ�����ɾ����û��ɾ����
		Cursor cursor = resolver.query(raw_contact_uri,
				new String[] { "_id" }, null, null, null);
		int columnCount = cursor.getColumnCount();
		while (cursor.moveToNext() && n<columnCount) {
			//4.ÿ��ѯ��һ�����ݶ�Ӧһ����ϵ�ˣ���ID�����ѯ���
			String id = cursor.getString(0);
			//5. һ��ID��Ӧһ����ϵ�ˣ�ÿ��ѯһ��IDֵ���Ͷ�Ӧһ����ϵ��
			Contact contact = new Contact();
			contact.setId(id);
			if (id == null) {
				Toast.makeText(getApplicationContext(), "IDΪ�գ���" + n++, 
						Toast.LENGTH_SHORT).show();
			} else {
				System.out.println("ID��" + id);
				// Toast.makeText(getApplicationContext(),"ID��"+ id,
				// Toast.LENGTH_LONG).show();
				String[] projection = { "data1", "mimetype" };
				//6.ʹ�ò�ѯ������ID��Ϊ��������ѯdata��Ҫ��ѯ���С�data1��  ����mimetype��
				Cursor cursor2 = resolver.query(data_uri, projection,
						"raw_contact_id = ?", new String[] { id }, null);
				while (cursor2.moveToNext()) {
					//7.��ȡÿһ���е�data1����  ��  mimetype  ����
					String result = cursor2.getString(0);
					String type = cursor2.getString(1);
                    //8.ͨ��mimetype������ȷ��data1���ݣ�ȷ��Ҫ���浽javaBean����һ���ֶ���
					if ("vnd.android.cursor.item/phone_v2".equals(type)) {
						Toast.makeText(getApplicationContext(),
								"result��" + result + "\ntype��" + type,
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
			//9.whileѭ��������˵��һ����ϵ�˱���Ĺ����Ѿ���ɣ����԰������ϵ�˶���ŵ���ϵ�˼����С�                        
			clist.add(contact);
			//10.���е���ϵ�˶��������ˣ����Բ鿴��������������ʾ����
			tv_readcontact1229_contact.setText(clist.toString());
/*			for(Contact con : clist){
				Toast.makeText(getApplicationContext(), n++ + "----"+con.toString(), Toast.LENGTH_SHORT).show();
			}*/
		}
	}
}
