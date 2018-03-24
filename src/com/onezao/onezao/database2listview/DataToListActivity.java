package com.onezao.onezao.database2listview;

import java.util.ArrayList;

import com.onezao.onezao.sqlite1118.MySqliteOpenHelper;
import com.onezao.onezao.sqlite1118.SqliteUtils;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.Person;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DataToListActivity extends Activity {
			//����֮�����õ����ݿ����֣��ļ�����ǰ����Ҫ����  /
			public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/zao20171121.db";
		    //���������ݿ�����,
		  	public  String name =  "zao20171120.db";
		  	//�汾���
//		  	private EditText et_sqlite_version;
		    //CursorFactory   �α깤��
		    public   CursorFactory  factory  = null;
			private int spVersion;
			//ʹ��SP�洢���ݿ�İ汾��
			private SharedPreferences sp;
			public   String   spFileKey  = "spVersion" ;

          private MySqliteOpenHelper listViewOpenHelper;
          public  ArrayList<Person>   lists = new ArrayList<Person>();
		private ListView lv_list;
		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_database_listview_1122);
        	
        	lv_list = (ListView) findViewById(R.id.lv_list);        	
        	sp = getSharedPreferences("zao1120.xml", MODE_PRIVATE);
        	spVersion = sp.getInt(spFileKey, 1);
//        	et_sqlite_version.setText(String.valueOf(spVersion));
        	listViewOpenHelper = new MySqliteOpenHelper(this,name , null, spVersion);
        	
        }
		
           //�����ݿ��������
           public void insert(View view){
                  SQLiteDatabase database = listViewOpenHelper.getReadableDatabase();
                  database.execSQL("INSERT into listview  (name,phone) VALUES('neo','17010076022');");
                  database.execSQL("INSERT into listview  (name,phone) VALUES('��ţ','13636127440');");
                  database.execSQL("INSERT into listview  (name,phone) VALUES('СӢ','17301216959');");
                  database.execSQL("INSERT into listview  (name,phone) VALUES('sky','13266861733');");
                  database.close();
                //�������ݿ⵽SD����
					ZaoUtils.copyFile(database.getPath(), fileName);	
				  Toast.makeText(DataToListActivity.this, SqliteUtils.update_version, Toast.LENGTH_LONG).show();
           }
           
           //�����ݿ��ѯ���ݣ���չʾ��ListView��
           public void query(View view){
        	   //��ռ�������
        	   lists.clear();
        	   SQLiteDatabase database = listViewOpenHelper.getReadableDatabase();
        	   Cursor cursor = database.rawQuery("SELECT * FROM listview", null);
        	   while(cursor.moveToNext()){
        		      Person  person = new Person();
        		      person.name = cursor.getString(1);
        		      person.phone = cursor.getString(2);
        		      lists.add(person);
        	   }
        	   cursor.close();
        	   database.close();
        	   //��������������Ҫ�ڵ���¼���ߣ���Ȼ���������á�
        	   lv_list.setAdapter(new MyAdapter());
        	  //�������ϣ��������Բ鿴ʹ�õ�
        	   for(Person person : lists){
        		   System.out.println(person);
        	   }
           }
           
           public class MyAdapter  extends BaseAdapter{
              //��ȡ��list���ϵĳ���
			@Override
			public int getCount() {
				return lists.size();
			}
            //��ȡ���ϵ�ÿһ����Ŀ
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return lists.get(position);
			}

			//����Position
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
            
			//���ò�չʾ����
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
                //����convertView 
				View view = null;
                 if(convertView == null){
                	 view  =  View.inflate(DataToListActivity.this, R.layout.item_listview_1121, null);
                 }  else  {
                	 view = convertView;
                 }
                 //�ҵ�Ҫ�޸ĵĿؼ�
                 TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
                 TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
                 //������ʾ������
                 tv_title.setText(lists.get(position).name);
                 tv_content.setText(lists.get(position).phone);
				 return view;
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

        			AdminUtils.AdminMenu(DataToListActivity.this, item);
        			return super.onOptionsItemSelected(item);
        	 }
}
