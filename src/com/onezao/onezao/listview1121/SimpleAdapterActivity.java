package com.onezao.onezao.listview1121;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onezao.onezao.login1114.LoginActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleAdapterActivity extends Activity {
	   public String title  = "今天，我写了一篇日记。。。";
	   public String content = "你的内容为空，请写下你的日记。\n Email : sunedo@qq.com \n主页 ：www.onezao.com\n淘宝 ：http://fone.taobao.com \n豆瓣 ：https://site.douban.com/273850/";
          @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_listview_1121);
        	
            ListView   lv_listview = (ListView) findViewById(R.id.lv_listview);
            List<Map<String,String>>   data  =   new ArrayList<Map<String, String>>();
            String[]   from ={"title","content"};
            for (int i = 0; i <  160; i++) {
            	Map<String,String>  item = new HashMap<String, String>();
				item.put("title", i+"、"+title);
				item.put("content", i+"、"+content);
				data.add(item);				
			}
            int[]  to =new int[]{R.id.tv_title,R.id.tv_content};
            /*
             * 第一个参数：上下文
             * 第二个参数：要显示的数据，每一个条目的数据放到一个Map中，再把Map都放到集合中
             * 第三个参数：要加载条目的布局文件的资源ID
             * 第四个参数：String数组，每一个元素，都是保存要展示数据的map中的key，要展示的数据是key对应的value值
             * 第五个参数：int 数组，存放的是要展示数据的控件ID，每一个元素跟String数组的元素的要key对应起来，然后系统匹配value值
             * 展示数据是从String数组的元素作为key到map中取值，取出的值展示到int数组对应ID 元素的控件上
             */
            lv_listview.setAdapter(new SimpleAdapter(this, data, R.layout.item_simpleadapter_1121, from, to));
            //设置条目的点击事件
            lv_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//利用参数view是被点击的那个item来获取需要的数据
					 TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
					 String text = tv_title.getText().toString();
                     Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();   					
				}
			});
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

     			AdminUtils.AdminMenu(SimpleAdapterActivity.this, item);
     			return super.onOptionsItemSelected(item);
     	 }
}
