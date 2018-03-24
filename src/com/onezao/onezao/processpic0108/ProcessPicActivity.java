package com.onezao.onezao.processpic0108;

import java.io.IOException;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProcessPicActivity extends Activity  implements android.view.View.OnClickListener{
            private EditText et_processpic_0108;
			private ImageView iv_image_0108;
			private ImageView iv_imagecopy_0108;
			private EditText et_matrixpic_0108;
			private String  MATRIX = "matrix";
			private String  ROTATE = "旋转";
			private String  SCALE = "缩放";
			private String  TRANSLATE = "平移";
			private TextView tv_processpic_0108;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_processpic_0108);
            	
            	tv_processpic_0108 = (TextView) findViewById(R.id.tv_processpic_0108);
            	
            	et_processpic_0108 = (EditText) findViewById(R.id.et_processpic_0108);
            	//给EditText设置与Button一样的属性
            	et_matrixpic_0108 = (EditText) findViewById(R.id.et_matrixpic_0108);
            	et_matrixpic_0108.setFocusable(false);
            	et_matrixpic_0108.setOnClickListener(this);
            	
            	iv_image_0108 = (ImageView) findViewById(R.id.iv_image_0108);
            	iv_imagecopy_0108 = (ImageView) findViewById(R.id.iv_imagecopy_0108);
            }
            
            public void  showPic(View view){
				String  et_processpicPath = et_processpic_0108.getText().toString().trim();
				String  et_matrixpic = et_matrixpic_0108.getText().toString().trim();
				String  picPath = ZaoUtils.pathSD+et_processpicPath;
				String exif = getExif(picPath);
				tv_processpic_0108.setText(exif);
            	//1.加载图片资源
            	Bitmap  bitmap = BitmapFactory.decodeFile(picPath);
            	//展示图片到控件上
            	iv_imagecopy_0108.setImageBitmap(bitmap);
            	//2.创建图片的副本
            	//2.1 根据原图的大小和配置信息，创建一个一样大小的空的图片
            	Bitmap bitmapCopy = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),bitmap.getConfig());
               //2.2  准备一个画布，把空的图片放到画布上
            	Canvas     canvas   =  new  Canvas(bitmapCopy);
            	//2.3  通过画布调用drawBitmap()方法把原图画到空的图片上
            	Matrix   matrix  =  new  Matrix();
            	if(et_matrixpic.equals(ROTATE)){
            		//2.3.1设置旋转
            	matrix.setRotate(180, bitmapCopy.getWidth()/2, bitmapCopy.getHeight()/2);
            	     }   else  if  (et_matrixpic.equals(TRANSLATE)){
			            	//2.3.2设置平移
			             	matrix.setTranslate(60, 0);
            	    }   else  if (et_matrixpic.equals(SCALE)){
            	//2.3.3设置缩放  ,  如果需要两次操作都生效，需要先set后post
            	matrix.setScale(1f, -1f);
            	matrix.postTranslate(0, bitmapCopy.getHeight());
            	    }
            	Paint  paint  =  new  Paint();
            	canvas.drawBitmap(bitmap, matrix, paint);
            	//2.4  将复制的文件展示到控件上。
            	iv_image_0108.setImageBitmap(bitmapCopy);
            }
            
            //点击EditText，弹出单选框，选择需要的文字
			@Override
			public void onClick(View v) {
                    int id = v.getId();
                    switch (id) {
					case R.id.et_matrixpic_0108:
				    	// 通过builder 构建器来构造
		        		AlertDialog.Builder   builder =  new  Builder(this);
		        		builder.setTitle("请选择你喜欢的操作条目");
		        		final String items[] = {MATRIX,ROTATE,SCALE,TRANSLATE};
		        		// 单选是设置单选按钮选项  .   -1代表没有条目被选中
		        		builder.setSingleChoiceItems(items, -1, new OnClickListener() {			
		        			@Override
		        			public void onClick(DialogInterface dialog, int which) {
		                           	//[1] 把选择的条目给取出来
		        				String item = items[which];
		        				Toast.makeText(ProcessPicActivity.this, item, Toast.LENGTH_SHORT).show();
		        				et_matrixpic_0108.setText(item);
		        				//[2] 把对话框关闭
		        				dialog.dismiss();
		        			}
		        		});		
		        		//最后一步，一定要记得和Toast一样，show出来。
		        		builder.show();
						break;
					}
			}
			
			//获取图片信息
			public String getExif(String path){
		            ExifInterface exifInterface = null;
					try {
						exifInterface = new ExifInterface(path);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            String time = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
		            String device = exifInterface.getAttribute(ExifInterface.TAG_MODEL);
		            String make = exifInterface.getAttribute(ExifInterface.TAG_MAKE);   
		            String text = "拍摄时间："+ time + "拍摄设备："+device + "拍摄制造商："+make;
		            Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();
		            return   text;
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

				AdminUtils.AdminMenu(ProcessPicActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
