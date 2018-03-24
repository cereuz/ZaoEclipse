package com.onezao.onezao.contentprovider1224;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class ContentProviderActivity extends Activity {
            private SQLiteDatabase db;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_provider_1224);
            	MyOpenHelper   helper  =  new  MyOpenHelper(this);
            	db = helper.getReadableDatabase();
            }
            
            @Override
            protected void onDestroy() {
            	super.onDestroy();
            	db.close();
            }
}
