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
		//����֮�����õ����ݿ����֣��ļ�����ǰ����Ҫ����  /
		public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/zao20171121.db";
	    //���������ݿ�����,
      	public  String name =  "zao20171120.db";
      	//�汾���
      	private EditText et_sqlite_version;
        //CursorFactory   �α깤��
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
				if(versionSqlite <  spVersion){    //�汾���ͣ����ܴ�������������
					Toast.makeText(SqliteActivity.this, SqliteUtils.low_version, Toast.LENGTH_LONG).show();
				}    else  if (versionSqlite == spVersion){   //�汾��ͬ������Ҫ����
					Toast.makeText(SqliteActivity.this, SqliteUtils.same_version, Toast.LENGTH_LONG).show();
					mySqliteOpenHelper = new MySqliteOpenHelper(SqliteActivity.this, name, factory, versionSqlite);
					//��ȡSqliteDatabase����
					database = mySqliteOpenHelper.getReadableDatabase();
					//����TextView����ʾ��Ϣ
					String  textCreate   =  database.toString() +"��"+ database.getVersion();
					tv_database.setText(textCreate);
					//�������ݿ⵽SD����
					ZaoUtils.copyFile(database.getPath(), fileName);	
				}	else  {    //�汾���ˣ��������������Ѱ汾�����浽sp��
					Editor edit = sp.edit();
					edit.putInt(spFileKey, versionSqlite);
					edit.commit();
					mySqliteOpenHelper = new MySqliteOpenHelper(SqliteActivity.this, name, factory, versionSqlite);
					//��ȡSqliteDatabase����
					database = mySqliteOpenHelper.getReadableDatabase();
					String  textCreate   =  database.toString() +"��"+ database.getVersion();
					tv_database.setText(textCreate);
					//�������ݿ⵽SD����
					ZaoUtils.copyFile(database.getPath(), fileName);	
					 Toast.makeText(SqliteActivity.this, SqliteUtils.update_version, Toast.LENGTH_LONG).show();
				}	        	
				break;

			case R.id.btn_insert_sqlite:
				//��ȡSqliteDatabase����
				database = mySqliteOpenHelper.getWritableDatabase();
				String sql = "INSERT INTO info (name,phone,sex,email) values('СӢ',17301216959,'female','cone@qq.com')";
				database.execSQL(sql);
				String sql2 = "INSERT INTO info (name,phone,sex,email) values('��ţ',13282380039,'male','sunedo@qq.com')";
				database.execSQL(sql2);
				String sql3 = "INSERT INTO info (name,phone,sex,email) values('neo',17010076022,'boy','zsky@live.com')";
				database.execSQL(sql3);
				database.close();
				//�������ݿ⵽SD����
				ZaoUtils.copyFile(database.getPath(), fileName);
				//�������ݳɹ�
				Toast.makeText(SqliteActivity.this, SqliteUtils.insert_succ, Toast.LENGTH_SHORT).show();
				break;
			
			
		case R.id.btn_delete_sqlite:
			//��ȡSqliteDatabase����
			database = mySqliteOpenHelper.getWritableDatabase();
			String sql4 = "delete from info where _id=2";
			database.execSQL(sql4);
			database.close();
			//�������ݿ⵽SD����
			ZaoUtils.copyFile(database.getPath(), fileName);
			//�������ݳɹ�
			Toast.makeText(SqliteActivity.this, SqliteUtils.delete_succ, Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.btn_update_sqlite:
			//��ȡSqliteDatabase����
			database = mySqliteOpenHelper.getWritableDatabase();
			String sql5 = "UPDATE info set age=27  where email='sunedo@qq.com'";
			database.execSQL(sql5);
			String sql6 = "UPDATE info set age=26  where name='СӢ'  and  _id%2==0 ";
			database.execSQL(sql6);
			String sql7 = "UPDATE info set age=1  where name='neo'";
			database.execSQL(sql7);
			database.close();
			//�������ݿ⵽SD����
			ZaoUtils.copyFile(database.getPath(), fileName);
			//�������ݳɹ�
			Toast.makeText(SqliteActivity.this, SqliteUtils.update_succ, Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.btn_query_sqlite:
			ArrayList<SqliteBean>  list = new ArrayList<SqliteBean>();
			//��ȡSqliteDatabase����
			database = mySqliteOpenHelper.getWritableDatabase();
			String sql8 = "SELECT *  from info";
			Cursor cursor = database.rawQuery(sql8, null);
			//��ѯ֮��Ľ��������һ���α�cursor ��ͨ���α���Է������������
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
			//�������ݿ⵽SD����
			ZaoUtils.copyFile(database.getPath(), fileName);
			//�������ݳɹ�
			tv_database.setText(list.toString());
			Toast.makeText(SqliteActivity.this, SqliteUtils.query_succ, Toast.LENGTH_SHORT).show();
			break;			
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
					AdminUtils.AdminMenu(SqliteActivity.this, item);
					return super.onOptionsItemSelected(item);
			 }
}
