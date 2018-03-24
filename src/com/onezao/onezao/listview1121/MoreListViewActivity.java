package com.onezao.onezao.listview1121;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MoreListViewActivity extends Activity {
         @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_listview_1121);
        	
        	ListView lv_listview = (ListView) findViewById(R.id.lv_listview);
        	lv_listview.setAdapter(new MyAdapter());
        }
         
         public class MyAdapter  extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 1606;
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
				//第一个参数：上下文，第二个参数：条目布局的资源id，第三个参数：ViewGroup（比如LinearLayout  RelativeLayout）
				//如果ViewGroup传入一个具体的对象，那么这个方法创建出来的View对象就会作为这个ViewGroup的一个子childView，不需要放入就可以直接传入null
/*     一、		
 * 		LayoutInflater  inflater = LayoutInflater.from(getApplicationContext());
				inflater.inflate(R.layout.item_listview_1121, null);
		二、		
				LayoutInflater   inflater2 = getLayoutInflater();
				//谷歌源代码使用这个方式
		三、
				LayoutInflater   inflater3  =(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);*/
				
				View  view = null;
				if(convertView == null){
					       view = View.inflate(MoreListViewActivity.this, R.layout.item_listview_1121, null);
						} else {
							view = convertView;
						}
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

     			AdminUtils.AdminMenu(MoreListViewActivity.this, item);
     			return super.onOptionsItemSelected(item);
     	 }
}
