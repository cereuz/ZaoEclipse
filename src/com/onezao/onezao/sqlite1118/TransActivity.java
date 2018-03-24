package com.onezao.onezao.sqlite1118;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TransActivity extends Activity {
				//����֮�����õ����ݿ����֣��ļ�����ǰ����Ҫ����  /
				public  String  fileName = Environment.getExternalStorageDirectory().getPath()+"/zao20171121.db";
			    //���������ݿ�����,
			  	public  String name =  "zao20171120.db";
			  	//�汾���
			  	public   String   spFileKey  = "spVersion" ;
			    //CursorFactory   �α깤��
			    public   CursorFactory  factory  = null;
				private MySqliteOpenHelper openHelper;
	              
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	    super.onCreate(savedInstanceState);
        	    setContentView(R.layout.activity_sqlite_1118);
        	    
        	    SharedPreferences sp = getSharedPreferences("zao1120.xml", MODE_PRIVATE);
            	int spVersion = sp.getInt(spFileKey, 1);
        	    openHelper = new MySqliteOpenHelper(this, name, factory, spVersion);
        }
           
           public void trans(View view){
        	   SQLiteDatabase database = openHelper.getReadableDatabase();
        	   database.beginTransaction();
        	   try {
          	       database.execSQL("UPDATE trans set money=money-200 where name=?",new String[]{"neo"});
//          	       int i = 110/0;
            	   database.execSQL("UPDATE trans set money=money+200 where name=?",new String[]{"sky"});
            	   database.setTransactionSuccessful();
        	   }
            	   finally  {
                database.endTransaction();
           	    database.close();
           	   //�������ݿ⵽SD����
   				ZaoUtils.copyFile(database.getPath(), fileName);	
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
				AdminUtils.AdminMenu(TransActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
