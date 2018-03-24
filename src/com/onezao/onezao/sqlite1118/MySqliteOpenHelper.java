package com.onezao.onezao.sqlite1118;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    
	public MySqliteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
	    /*(Context context, String name,CursorFactory factory, int version)
	     * 1.��һ��������������
	     * 2.�ڶ������������ݿ�����֣��������null���������ڴ��д���һ�����ݿ⣬�ڴ��е����ݿ���Ӧ���˳����߱�ɱ��֮�����ݾͻᱻ���,��ʧ��
	     * 3.�������������α깤�������ʹ��ϵͳĬ�ϵ��α깤���ʹ���null��һ�㶼��ʹ��Ĭ�ϵġ�
	     * 4.���ĸ����������ݿ�İ汾�ţ��ð汾�����������ݿ�������ͽ������汾�Ŵ�1��ʼ��
	     */
		super(context, name, factory, version);
		Toast.makeText(context, SqliteUtils.create_sqlite, Toast.LENGTH_SHORT).show();
	}

	//�����ݿ��ļ���һ�δ�����ʱ�򣬻�����������������������У�����һ����������Ĵ��������ݵĳ�ʼ������
	@Override
	public void onCreate(SQLiteDatabase db) {
		       //SQLite��  id��һ�е��ֶ�����һ�㶼ϰ�߳�Ϊ  _id
		      //SQLite���ݴ洢�Ķ����ַ���������д��������仹��ʹ��SQL���
               db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");               
               db.execSQL("CREATE TABLE trans (_id integer primary key autoincrement,name varchar(20),phone varchar(20),money varchar(20))");
       		   db.execSQL("INSERT into trans (name,phone,money) values ('neo','1366666','2000')");
       	      db.execSQL("INSERT into trans (name,phone,money) values ('sky','1378888','6000')");
       	   db.execSQL("CREATE TABLE listview(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20))");
	}

	//�����ݿ�������ʱ��Ĳ�����
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             //��ṹ���޸ģ���������µı�� (�ڲ�ͬ�����ݿ�汾����������������ͬ����Ҫ���ݰ汾�Ĳ�ͬ��������)
    	db.execSQL("ALTER table info add sex varchar(4)");
		db.execSQL("ALTER table info add age integer");
		db.execSQL("ALTER table info add email varchar(32)");		
		db.execSQL("ALTER table info add time varchar(32)");		
	}
	
	//����Ҫ�������ݿ��ʱ����ã����Ǳ��������������ġ���Ҫ��ʱ���������ʹ�õ�
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		super.onDowngrade(db, oldVersion, newVersion);
	}

}
