package com.onezao.onezao.fragmentdynamic0118;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentDynamicActivity extends Activity {
            @SuppressWarnings("deprecation")
			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_fragmentdynamic_0118);
            	//获取屏幕的宽高
            	int width = getWindowManager().getDefaultDisplay().getWidth();
            	int height = getWindowManager().getDefaultDisplay().getHeight();
            	//一，获取FragmentManager
            	FragmentManager manager = getFragmentManager();
            	//二，开启fragment事务
            	FragmentTransaction transaction = manager.beginTransaction();
            	if(width  >  height ){
            		//横屏      把fragment对象替换到viewgroup节点下
            		//第一个参数，用来放置fragment的viewgroup的ID
            		//第二个参数，要显示的fragment对象
            		transaction.replace(android.R.id.content, new SecondFragment());
            	}  else  {
            		//竖屏
            		transaction.replace(android.R.id.content, new  FirstFragment());
            	}
            	//设置完对应的fragment一定要调用commit提交事务
            	transaction.commit();
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

    			AdminUtils.AdminMenu(FragmentDynamicActivity.this, item);
    			return super.onOptionsItemSelected(item);
    	 }
}
