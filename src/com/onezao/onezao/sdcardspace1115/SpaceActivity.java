package com.onezao.onezao.sdcardspace1115;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SpaceActivity extends Activity {
           private EditText et_space_total;
		private EditText et_space_free;
		private EditText et_space_other;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_sdspace_1115);
        	
        	et_space_total = (EditText) findViewById(R.id.et_space_total);
        	et_space_free = (EditText) findViewById(R.id.et_space_free);
        	et_space_other = (EditText) findViewById(R.id.et_space_other);
        	   
        }
		
		public void space(View view){
			
			//获取外部存储空间			
			String pathOut = Environment.getExternalStorageDirectory().getPath();
			String absolutePathOut = Environment.getExternalStorageDirectory().getAbsolutePath();
			long totalSpaceOut = Environment.getExternalStorageDirectory().getTotalSpace();
			long freeSpaceOut  = Environment.getExternalStorageDirectory().getFreeSpace();		
			
			//获取内部存储空间
			String pathIn = Environment.getRootDirectory().getPath();
			String absolutePathIn = Environment.getRootDirectory().getAbsolutePath();
			long totalSpaceIn = Environment.getRootDirectory().getTotalSpace();
			long freeSpaceIn = Environment.getRootDirectory().getFreeSpace();
			//格式化空间大小
			String totalFileSizeOut = Formatter.formatFileSize(SpaceActivity.this, totalSpaceOut);
			String freeFileSizeOut = Formatter.formatFileSize(SpaceActivity.this, freeSpaceOut);
			String totalFileSizeIn = Formatter.formatFileSize(SpaceActivity.this, totalSpaceIn);
			String freeFileSizeIn = Formatter.formatFileSize(SpaceActivity.this, freeSpaceIn);
			
			//获取DataDirectory
			long totalDataDirectory = Environment.getDataDirectory().getTotalSpace();
			String totalSizeDataDirectory = Formatter.formatFileSize(SpaceActivity.this, totalDataDirectory);

			long freeDataDirectory = Environment.getDataDirectory().getFreeSpace();
			String freeSizeDataDirectory = Formatter.formatFileSize(SpaceActivity.this, freeDataDirectory);

			long totalDownloadCacheDirectory = Environment.getDownloadCacheDirectory().getTotalSpace();
			String totalFileDownloadCacheDirectory = Formatter.formatFileSize(SpaceActivity.this, totalDownloadCacheDirectory);
			long freeDownloadCacheDirectory = Environment.getDownloadCacheDirectory().getFreeSpace();
			String freeFileDownloadCacheDirectory = Formatter.formatFileSize(SpaceActivity.this, freeDownloadCacheDirectory);
			long totalDIRECTORYMUSIC = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getTotalSpace();
			String totalFileDIRECTORYMUSIC = Formatter.formatFileSize(SpaceActivity.this, totalDIRECTORYMUSIC);
			long freeDIRECTORY_MUSIC = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getFreeSpace();
			String freeFileDIRECTORY_MUSIC = Formatter.formatFileSize(SpaceActivity.this, freeDIRECTORY_MUSIC);
			
			String textOut = "pathOut："+pathOut + "\n"+"absolutePathOut："+absolutePathOut +"\n"+"totalFileSizeOut："+totalFileSizeOut+"\n"+"freeFileSizeOut："+freeFileSizeOut;
			String textIn  ="pathIn："+ pathIn + "\n"+ "absolutePathIn："+absolutePathIn+ "\n"+"totalFileSizeIn："+totalFileSizeIn+ "\n"+"freeFileSizeIn："+freeFileSizeIn;
			String textOther = "totalSizeDataDirectory："+totalSizeDataDirectory+"\n"+"freeSizeDataDirectory："+freeSizeDataDirectory+"\n"+"totalFileDownloadCacheDirectory："+totalFileDownloadCacheDirectory+"\n"+"freeFileDownloadCacheDirectory："+freeFileDownloadCacheDirectory+"\n"+"totalFileDIRECTORYMUSIC："+totalFileDIRECTORYMUSIC+"\n"+"freeFileDIRECTORY_MUSIC："+freeFileDIRECTORY_MUSIC;
			et_space_total.setText(textOut);
			et_space_free.setText(textIn);
			et_space_other.setText(textOther);
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

			AdminUtils.AdminMenu(SpaceActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
		
}
