package com.onezao.onezao.fragmentcomm0122;

import com.onezao.onezao.erasecloth0111.EraseClothActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentCommActivity extends Activity {
               @Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_fragmentcomm_0122);
            	
            	FragmentManager manager = getFragmentManager();
            	FragmentTransaction transaction = manager.beginTransaction();
            	//使用三个参数的方法
            	transaction.replace(R.id.ll_fragmentcomm0122_left, new  FirstFragment(),"fragmentcomm_left");
            	transaction.replace(R.id.ll_fragmentcomm0122_right, new  SecondFragment(),"fragmentcomm_right");
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

					AdminUtils.AdminMenu(FragmentCommActivity.this, item);
					return super.onOptionsItemSelected(item);
			 }
}
