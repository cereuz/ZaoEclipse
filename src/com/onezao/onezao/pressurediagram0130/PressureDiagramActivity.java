package com.onezao.onezao.pressurediagram0130;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PressureDiagramActivity extends Activity {
            static {
            	System.loadLibrary("pressure");
            }
	        private ProgressBar pb_pressure_0130;
			private int pb_max_0130 = 800;
			private EditText et_pressure_0130;
			private MyPressureView pb_mypressure_0130;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_pressurediagram_0130);
            	et_pressure_0130 = (EditText) findViewById(R.id.et_pressure_0130);
            	pb_pressure_0130 = (ProgressBar) findViewById(R.id.pb_pressure_0130);
            	pb_mypressure_0130 = (MyPressureView) findViewById(R.id.pb_mypressure_0130);
            	pb_pressure_0130.setMax(pb_max_0130);
            }
			
			public  void  start(View  view){
				Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
                //C方法运行在主线程，主线程不能做耗时操作。不然会堵塞
				new  Thread(){
					public void run(){
						startMonitor();
					}
				}.start();
			}
			
			public  void  stop(View  view){
				Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
				stopMonitor();
			}
			
			public void setPressure(final int pressure){
				pb_pressure_0130.setProgress(pressure);
				pb_mypressure_0130.setPressure(pressure);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						et_pressure_0130.setText(String.valueOf(pressure)+"|"+ZaoUtils.getSystemTime());						
					}
				});
			}
			
			//模拟开启，模拟停止
			public  native void  startMonitor();
			public  native void  stopMonitor();
			
			
			
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

				AdminUtils.AdminMenu(PressureDiagramActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
