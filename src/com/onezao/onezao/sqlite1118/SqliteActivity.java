package com.onezao.onezao.sqlite1118;

import java.util.ArrayList;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SqliteActivity extends Activity  implements  OnClickListener {
		//复制之后设置的数据库名字，文件名称前边需要加上  /
		public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/zao20171121.db";
	    //创建的数据库名字,
      	public  String name =  "zao20171120.db";
      	//版本编号
      	private EditText et_sqlite_version;
        //CursorFactory   游标工厂
	    public   CursorFactory  factory  = null;
		private int spVersion;
		private SharedPreferences sp;
		public   String   spFileKey  = "spVersion" ;

	    private Button btn_create_sqlite;
	    private Button btn_insert_sqlite;
	    private Button btn_delete_sqlite;
	    private Button btn_update_sqlite;
	    private Button btn_query_sqlite;
		private TextView tv_database;
		private MySqliteOpenHelper mySqliteOpenHelper;
		private SQLiteDatabase database;

		
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	
        	setContentView(R.layout.activity_sqlite_1118);
        	
        	
        	btn_create_sqlite = (Button) findViewById(R.id.btn_create_sqlite);
        	btn_create_sqlite.setOnClickListener(this);
        	btn_insert_sqlite = (Button) findViewById(R.id.btn_insert_sqlite);
        	btn_insert_sqlite.setOnClickListener(this);
        	btn_delete_sqlite = (Button) findViewById(R.id.btn_delete_sqlite);
        	btn_delete_sqlite.setOnClickListener(this);
        	btn_update_sqlite = (Button) findViewById(R.id.btn_update_sqlite);
        	btn_update_sqlite.setOnClickListener(this);
        	btn_query_sqlite = (Button) findViewById(R.id.btn_query_sqlite);
        	btn_query_sqlite.setOnClickListener(this);
        	
        	tv_database = (TextView) findViewById(R.id.tv_database);
        	et_sqlite_version = (EditText) findViewById(R.id.et_sqlite_version);
        	sp = getSharedPreferences("zao1120.xml", MODE_PRIVATE);
        	spVersion = sp.getInt(spFileKey, 1);
        	et_sqlite_version.setText(String.valueOf(spVersion));
			mySqliteOpenHelper = new MySqliteOpenHelper(this, name, factory, spVersion);
     }
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_create_sqlite:		
				Integer versionSqlite  = Integer.valueOf(et_sqlite_version.getText().toString());		
				spVersion = sp.getInt(spFileKey, 1);
				if(versionSqlite <  spVersion){    //版本过低，不能创建，不能升级
					Toast.makeText(SqliteActivity.this, SqliteUtils.low_version, Toast.LENGTH_LONG).show();
				}    else  if (versionSqlite == spVersion){   //版本相同，不需要创建
					Toast.makeText(SqliteActivity.this, SqliteUtils.same_version, Toast.LENGTH_LONG).show();
					mySqliteOpenHelper = new MySqliteOpenHelper(SqliteActivity.this, name, factory, versionSqlite);
					//获取SqliteDatabase对象
					database = mySqliteOpenHelper.getReadableDatabase();
					//设置TextView的显示信息
					String  textCreate   =  database.toString() +"，"+ database.getVersion();
					tv_database.setText(textCreate);
					//复制数据库到SD卡中
					ZaoUtils.copyFile(database.getPath(), fileName);	
				}	else  {    //版本高了，可以升级，并把版本数保存到sp中
					Editor edit = sp.edit();
					edit.putInt(spFileKey, versionSqlite);
					edit.commit();
					mySqliteOpenHelper = new MySqliteOpenHelper(SqliteActivity.this, name, factory, versionSqlite);
					//获取SqliteDatabase对象
					database = mySqliteOpenHelper.getReadableDatabase();
					String  textCreate   =  database.toString() +"，"+ database.getVersion();
					tv_database.setText(textCreate);
					//复制数据库到SD卡中
					ZaoUtils.copyFile(database.getPath(), fileName);	
					 Toast.makeText(SqliteActivity.this, SqliteUtils.update_version, Toast.LENGTH_LONG).show();
				}	        	
				break;

			case R.id.btn_insert_sqlite:
				//获取SqliteDatabase对象
				database = mySqliteOpenHelper.getWritableDatabase();
				String sql = "INSERT INTO info (name,phone,sex,email) values('小英',17301216959,'female','cone@qq.com')";
				database.execSQL(sql);
				String sql2 = "INSERT INTO info (name,phone,sex,email) values('蜗牛',13282380039,'male','sunedo@qq.com')";
				database.execSQL(sql2);
				String sql3 = "INSERT INTO info (name,phone,sex,email) values('neo',17010076022,'boy','zsky@live.com')";
				database.execSQL(sql3);
				database.close();
				//复制数据库到SD卡中
				ZaoUtils.copyFile(database.getPath(), fileName);
				//插入数据成功
				Toast.makeText(SqliteActivity.this, SqliteUtils.insert_succ, Toast.LENGTH_SHORT).show();
				break;
			
			
		case R.id.btn_delete_sqlite:
			//获取SqliteDatabase对象
			database = mySqliteOpenHelper.getWritableDatabase();
			String sql4 = "delete from info where _id=2";
			database.execSQL(sql4);
			database.close();
			//复制数据库到SD卡中
			ZaoUtils.copyFile(database.getPath(), fileName);
			//插入数据成功
			Toast.makeText(SqliteActivity.this, SqliteUtils.delete_succ, Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.btn_update_sqlite:
			//获取SqliteDatabase对象
			database = mySqliteOpenHelper.getWritableDatabase();
			String sql5 = "UPDATE info set age=27  where email='sunedo@qq.com'";
			database.execSQL(sql5);
			String sql6 = "UPDATE info set age=26  where name='小英'  and  _id%2==0 ";
			database.execSQL(sql6);
			String sql7 = "UPDATE info set age=1  where name='neo'";
			database.execSQL(sql7);
			database.close();
			//复制数据库到SD卡中
			ZaoUtils.copyFile(database.getPath(), fileName);
			//插入数据成功
			Toast.makeText(SqliteActivity.this, SqliteUtils.update_succ, Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.btn_query_sqlite:
			ArrayList<SqliteBean>  list = new ArrayList<SqliteBean>();
			//获取SqliteDatabase对象
			database = mySqliteOpenHelper.getWritableDatabase();
			String sql8 = "SELECT *  from info";
			Cursor cursor = database.rawQuery(sql8, null);
			//查询之后的结果，返回一个游标cursor ，通过游标可以访问整个结果集
			while(cursor.moveToNext()){
				SqliteBean  sqliteBean = new  SqliteBean();
				String id = cursor.getString(cursor.getColumnIndex("_id"));
				sqliteBean.setId(id);
				String name = cursor.getString(cursor.getColumnIndex("name"));
				sqliteBean.setName(name);
				String phone = cursor.getString(cursor.getColumnIndex("phone"));
				sqliteBean.setPhone(phone);
				String sex = cursor.getString(cursor.getColumnIndex("sex"));
				sqliteBean.setSex(sex);
				String age = cursor.getString(cursor.getColumnIndex("age"));
				sqliteBean.setAge(age);
				String email = cursor.getString(cursor.getColumnIndex("email"));
				sqliteBean.setEmail(email);
				String time = cursor.getString(cursor.getColumnIndex("time"));
				sqliteBean.setTime(time);
				list.add(sqliteBean);
			}
			database.close();
			//复制数据库到SD卡中
			ZaoUtils.copyFile(database.getPath(), fileName);
			//插入数据成功
			tv_database.setText(list.toString());
			Toast.makeText(SqliteActivity.this, SqliteUtils.query_succ, Toast.LENGTH_SHORT).show();
			break;			
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
					AdminUtils.AdminMenu(SqliteActivity.this, item);
					return super.onOptionsItemSelected(item);
			 }
}
