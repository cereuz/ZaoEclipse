package com.onezao.onezao.contentprovider1224;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context) {
		super(context, "provider1224.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
          db.execSQL("CREATE TABLE info (_id integer primary key autoincrement,name varchar(20),phone varchar(20),email varchar(20))");
          db.execSQL("INSERT into info (name,phone,email) values ('zneo','1322222','zneo@qq.com')");
          db.execSQL("INSERT into info (name,phone,email) values ('zsky','1366666','zsky@live.com')");
          db.execSQL("INSERT into info (name,phone,email) values ('znote','1700000','znote@qq.com')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            
	}

}
