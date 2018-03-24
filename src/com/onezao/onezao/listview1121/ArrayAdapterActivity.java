package com.onezao.onezao.listview1121;

import com.onezao.onezao.login1114.LoginActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterActivity extends Activity {
          @Override
        protected void onCreate(Bundle savedInstanceState) {             
        	  super.onCreate(savedInstanceState);
        	  setContentView(R.layout.activity_listview_1121);
        	  
        	  ListView   lv_listview = (ListView) findViewById(R.id.lv_listview);
        	  String[]  obj  =  {"苹果","梨子","香蕉","菠萝","葡萄","提子","哈密瓜"};
        	  lv_listview.setAdapter(new ArrayAdapter<String>(this, R.layout.item_arrayadapter_1121, obj));
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

     			AdminUtils.AdminMenu(ArrayAdapterActivity.this, item);
     			return super.onOptionsItemSelected(item);
     	 }
}
