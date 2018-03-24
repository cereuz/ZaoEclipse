package com.onezao.onezao.listview1121;

import com.onezao.onezao.login1114.LoginActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_listview_1121);
        	
        	//找到关心的控件
        	ListView lv_listview = (ListView) findViewById(R.id.lv_listview);
        	lv_listview.setAdapter(new MyAdapter());
        }
           
           public class  MyAdapter   extends  BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 16061606;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView  tv_text  = null;
				if(convertView == null){
					   //说明没有可以重用的view对象，需要创建新的View对象
				     tv_text  =  new TextView(ListViewActivity.this);
				     System.out.println("创建新的View对象");
				}   else  {
					//说明有可以重用的view对象，直接复用就可以。
					tv_text  = (TextView) convertView;
					System.out.println("复用原来的View对象");
				}
				//给TextView设置数据。
                 tv_text.setText("我是第" + position+"个条目。");
				return tv_text;
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

      			AdminUtils.AdminMenu(ListViewActivity.this, item);
      			return super.onOptionsItemSelected(item);
      	 }
}
