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
	     * 1.第一个参数：上下文
	     * 2.第二个参数：数据库的名字，如果传入null，就是在内存中创建一个数据库，内存中的数据库在应用退出或者被杀死之后，数据就会被清空,丢失。
	     * 3.第三个参数：游标工厂，如果使用系统默认的游标工厂就传入null，一般都是使用默认的。
	     * 4.第四个参数：数据库的版本号，用版本号来控制数据库的升级和降级。版本号从1开始。
	     */
		super(context, name, factory, version);
		Toast.makeText(context, SqliteUtils.create_sqlite, Toast.LENGTH_SHORT).show();
	}

	//当数据库文件第一次创建的时候，会调用这个方法，在这个方法中，我们一般做表机构的创建和数据的初始化操作
	@Override
	public void onCreate(SQLiteDatabase db) {
		       //SQLite中  id这一列的字段名，一般都习惯称为  _id
		      //SQLite数据存储的都是字符串，但是写出来的语句还是使用SQL语句
               db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");               
               db.execSQL("CREATE TABLE trans (_id integer primary key autoincrement,name varchar(20),phone varchar(20),money varchar(20))");
       		   db.execSQL("INSERT into trans (name,phone,money) values ('neo','1366666','2000')");
       	      db.execSQL("INSERT into trans (name,phone,money) values ('sky','1378888','6000')");
       	   db.execSQL("CREATE TABLE listview(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20))");
	}

	//当数据库升级的时候的操作。
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             //表结构的修改，比如添加新的表格 (在不同的数据库版本的升级都会有所不同。需要根据版本的不同来做控制)
    	db.execSQL("ALTER table info add sex varchar(4)");
		db.execSQL("ALTER table info add age integer");
		db.execSQL("ALTER table info add email varchar(32)");		
		db.execSQL("ALTER table info add time varchar(32)");		
	}
	
	//当需要降级数据库的时候调用，不是必须调用这个方法的。需要的时候可以拿来使用的
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		super.onDowngrade(db, oldVersion, newVersion);
	}

}
