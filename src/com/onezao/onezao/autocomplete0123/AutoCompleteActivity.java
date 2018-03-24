package com.onezao.onezao.autocomplete0123;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteActivity extends Activity {
	             private  String[]  names  =  {"zneo","zero","zcool","zen","stun","stuck","sleep","book","bitch","bingo","bin"};
                       @Override
                    protected void onCreate(Bundle savedInstanceState) {
                    	super.onCreate(savedInstanceState);
                      	setContentView(R.layout.activity_autocomplete_0123);
                      	AutoCompleteTextView actv_0123 = (AutoCompleteTextView) findViewById(R.id.actv_0123);
                      	ArrayAdapter<String>   adapter  =  new  ArrayAdapter<String>(getApplicationContext(), R.layout.item_actv_0123,names);
                      	actv_0123.setAdapter(adapter);
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

               			AdminUtils.AdminMenu(AutoCompleteActivity.this, item);
               			return super.onOptionsItemSelected(item);
               	 }            
}
