package com.onezao.onezao.fragmentdemo0116;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FragmentDemoActivity extends Activity {
         private Button btn_fragment_0116;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_fragment_0116);
        	btn_fragment_0116 = (Button) findViewById(R.id.btn_fragment_0116);
        	btn_fragment_0116.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
                         Toast.makeText(getApplicationContext(), "我是FragmentDemoActivity！！", Toast.LENGTH_SHORT).show();					
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

 			AdminUtils.AdminMenu(FragmentDemoActivity.this, item);
 			return super.onOptionsItemSelected(item);
 	 }
}
