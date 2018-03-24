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
			//复制之后设置的数据库名字，文件名称前边需要加上  /
			public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/zao20171121.db";
		    //创建的数据库名字,
		  	public  String name =  "zao20171120.db";
		  	//版本编号
//		  	private EditText et_sqlite_version;
		    //CursorFactory   游标工厂
		    public   CursorFactory  factory  = null;
			private int spVersion;
			//使用SP存储数据库的版本数
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
		
           //往数据库插入数据
           public void insert(View view){
                  SQLiteDatabase database = listViewOpenHelper.getReadableDatabase();
                  database.execSQL("INSERT into listview  (name,phone) VALUES('neo','17010076022');");
                  database.execSQL("INSERT into listview  (name,phone) VALUES('蜗牛','13636127440');");
                  database.execSQL("INSERT into listview  (name,phone) VALUES('小英','17301216959');");
                  database.execSQL("INSERT into listview  (name,phone) VALUES('sky','13266861733');");
                  database.close();
                //复制数据库到SD卡中
					ZaoUtils.copyFile(database.getPath(), fileName);	
				  Toast.makeText(DataToListActivity.this, SqliteUtils.update_version, Toast.LENGTH_LONG).show();
           }
           
           //从数据库查询数据，并展示到ListView上
           public void query(View view){
        	   //清空集合数据
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
        	   //设置适配器，需要在点击事件里边，不然不能起作用。
        	   lv_list.setAdapter(new MyAdapter());
        	  //遍历集合，仅供调试查看使用的
        	   for(Person person : lists){
        		   System.out.println(person);
        	   }
           }
           
           public class MyAdapter  extends BaseAdapter{
              //获取到list集合的长度
			@Override
			public int getCount() {
				return lists.size();
			}
            //获取集合的每一个条目
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return lists.get(position);
			}

			//返回Position
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
            
			//设置并展示内容
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
                //复用convertView 
				View view = null;
                 if(convertView == null){
                	 view  =  View.inflate(DataToListActivity.this, R.layout.item_listview_1121, null);
                 }  else  {
                	 view = convertView;
                 }
                 //找到要修改的控件
                 TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
                 TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
                 //设置显示的内容
                 tv_title.setText(lists.get(position).name);
                 tv_content.setText(lists.get(position).phone);
				 return view;
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

        			AdminUtils.AdminMenu(DataToListActivity.this, item);
        			return super.onOptionsItemSelected(item);
        	 }
}
