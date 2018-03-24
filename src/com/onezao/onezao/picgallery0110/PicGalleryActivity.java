package com.onezao.onezao.picgallery0110;

import java.io.IOException;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PicGalleryActivity extends Activity {
		private ImageView iv_picgallery_0110;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_picgallery_0110);
        	iv_picgallery_0110 = (ImageView) findViewById(R.id.iv_picgallery_0110);
        }
		
		public   void  gallery(View  view){
			   Intent intent = new Intent();
		        intent.setAction("android.intent.action.PICK");
		        intent.setType("image/*");
		        this.startActivityForResult(intent, 0);
		}
		
		   // �õ�����ֵ����ѡ�е�ͼƬ
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if (data != null) {
	            Uri uri = data.getData();
	            iv_picgallery_0110.setImageURI(uri);
	        }
	    }
	    
	    
	  //���ض����˵�����Ӳ˵��ĵ���¼���
	  		@Override
	  		public boolean onCreateOptionsMenu(Menu menu) {
	  			  //�������Ͻǵ�ͼ��ĵ���¼�  ActionBar
	  		      ActionBar actionBar = this.getActionBar();
	  		     actionBar.setHomeButtonEnabled(true);
	  			// Inflate the menu; this adds items to the action bar if it is present.
	  			getMenuInflater().inflate(R.menu.admin, menu);
	  			return true;
	  		}
	  		
	  		@Override
	  		public boolean onOptionsItemSelected(MenuItem item) {

	  			AdminUtils.AdminMenu(PicGalleryActivity.this, item);
	  			return super.onOptionsItemSelected(item);
	  	 }
}
