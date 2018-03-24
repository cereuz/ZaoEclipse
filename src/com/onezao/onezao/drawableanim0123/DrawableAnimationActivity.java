package com.onezao.onezao.drawableanim0123;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class DrawableAnimationActivity extends Activity {
                @Override
                protected void onCreate(Bundle savedInstanceState) {
                	super.onCreate(savedInstanceState);
                	setContentView(R.layout.activity_drawableanim_0123);
                	
                	ImageView iv_drawableanim_0123 = (ImageView) findViewById(R.id.iv_drawableanim_0123);
                	AnimationDrawable  animation = (AnimationDrawable) iv_drawableanim_0123.getBackground();
                	animation.start();
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

        			AdminUtils.AdminMenu(DrawableAnimationActivity.this, item);
        			return super.onOptionsItemSelected(item);
        	 }
}
