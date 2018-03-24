package com.onezao.onezao.loadpic0107;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoadPicActivity extends Activity {
            private ImageView iv_pic_0107;
			private EditText et_loadpic_0107;
			private EditText et_picoptions_0107;
			private String et_loadpicPath;
			private String et_picoptions;
			private String picPath;
			private TextView tv_loadpic_0107;
			private String screenText;
			private int screenWidth;
			private int screenHeight;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_loadpic_0107);
            	iv_pic_0107 = (ImageView) findViewById(R.id.iv_pic_0107);
            	et_loadpic_0107 = (EditText) findViewById(R.id.et_loadpic_0107);
            	et_picoptions_0107 = (EditText) findViewById(R.id.et_picoptions_0107);
            	tv_loadpic_0107 = (TextView) findViewById(R.id.tv_loadpic_0107);
            	//过时的API获取屏幕宽和高
            	screenWidth = getWindowManager().getDefaultDisplay().getWidth();
            	screenHeight = getWindowManager().getDefaultDisplay().getHeight();
            	//推荐的API获取屏幕宽高，屏幕宽高信息保存在point对象中
            	Point outSize = new Point();
            	getWindowManager().getDefaultDisplay().getSize(outSize);
            	screenText = "屏幕高度old："+screenHeight + "\n屏幕宽度old："+ screenWidth
            			   +  "\n屏幕高度new："+outSize.y + "\n屏幕宽度new："+ outSize.x;
            	tv_loadpic_0107.setText(screenText);
            	Toast.makeText(this, screenText, Toast.LENGTH_SHORT).show();
            }
			
			//实现点击事件
			public void loadpic(View view){
				initClick();
//				loadpicDirect();
				loadpic1();
				
				//可以直接加按钮，设置点击事件，也可以通过判断不同情况，来调用不同的点击方法。
//				loadpic2();
//				loadpic3();
			}

			private void initClick() {
				et_loadpicPath = et_loadpic_0107.getText().toString().trim();
				et_picoptions = et_picoptions_0107.getText().toString().trim();
				picPath = ZaoUtils.pathSD+et_loadpicPath;
				Toast.makeText(getApplicationContext(), picPath, Toast.LENGTH_SHORT).show();
			}
             
			//不做任何处理，直接加载显示图片
			private void loadpicDirect() {
				//创建Bitmap对象
				Bitmap bitmap = BitmapFactory.decodeFile(picPath);
				//展示图片到ImageView
				iv_pic_0107.setImageBitmap(bitmap);
			}
			
			//使用Options压缩图片
			public void loadpic1(){
			//Options  选项的意思，通过创建Options对象，告诉BitmapFactory在解码图片的时候
			//采用一些配置好的设置选项，比如压缩图片的压缩比例，或者是否加载完整图片	
			BitmapFactory.Options  options = new Options();
			//inSampleSize    sample 采样的意思
			//inSampleSize  通过配置这个值可以压缩图片，如果inSampleSize=2，那么加载到内存中的图片
			//宽度是原图的1/2  高度也是原图的1/2，总的大小是原图的1/4；需要注意，如果inSampleSize<1,会按照1处理
			//解码器默认会按照2的幂指数来处理图片的压缩，所以可以传入2的幂指数来作为inSampleSize的值
			options.inSampleSize = Integer.valueOf(et_picoptions) ;
			//使用包含options参数的方法，来获取图片
			Bitmap bitmap = BitmapFactory.decodeFile(picPath,options);
			//获取图片宽高
			 int picHeight = options.outHeight;
			 int picWidth = options.outWidth;
			 String  picText2 =  "\n图片高度1："+picWidth + "\n图片宽度1："+picHeight;
			 tv_loadpic_0107.setText(screenText +picText2 +"\n图片地址1："+picPath + "\n压缩比例："+et_picoptions);
			iv_pic_0107.setImageBitmap(bitmap);
			}
			
			//根据屏幕大小，自动计算比例，然后加载图片
			public void loadpic2(View  view){
				//初始化
				initClick();
				
				//比较图片和屏幕的分辨率，如果图片分辨率比屏幕分辨率高
				//可以使用    图片宽度/屏幕宽度
				//   图片高度/屏幕高度
				//通过这两个比值来确定inSampleSize
				
				//1.获取屏幕的宽高
				//2.获取图片的宽高
				BitmapFactory.Options  options = new Options();
				options.inJustDecodeBounds = true;
				Bitmap bitmap = BitmapFactory.decodeFile(picPath,options);
				int picHeight = options.outHeight;
				int picWidth = options.outWidth;
				String  picText =  "\n图片高度2："+picWidth + "\n图片宽度2："+picHeight;
				if(bitmap == null){
					tv_loadpic_0107.setText(screenText + picText  +"\n图片地址2："+picPath);
				}
				//3.计算inSampleSize
	           //只有当图片的宽度或者高度大于屏幕的宽度和高度的时候才压缩图片				
				if(picWidth > screenWidth || picHeight > screenHeight){
					//获取图片   和 屏幕  对应的宽高比例，四舍五入。
					int widthRound = Math.round((float)picWidth/(float)screenWidth);
					int heightRound = Math.round((float)picHeight/(float)screenHeight);
					//计算压缩比例，取宽高比例的最大值
					options.inSampleSize = Math.max(widthRound, heightRound);
				}
				//用计算好的inSampleSize比例来加载图片
				options.inJustDecodeBounds = false;  
				bitmap = BitmapFactory.decodeFile(picPath,options);
				 picHeight = options.outHeight;
				 picWidth = options.outWidth;
				 String  picText2 =  "\n图片高度22："+picWidth + "\n图片宽度22："+picHeight;
				 tv_loadpic_0107.setText(screenText + picText+picText2  +"\n图片地址22："+picPath +"\n压缩比例："+options.inSampleSize );
				 //用ImageView显示图片
				iv_pic_0107.setImageBitmap(bitmap);
			}
			
			//不断试错的方式来计算inSampleSize
			public void loadpic3(View  view){
				//初始化
				initClick();
				
				BitmapFactory.Options  options =  new Options();
				options.inSampleSize = 1;
				Bitmap bitmap = null;
				int i =1;
				for(;;){
					try{
						options.inSampleSize = i;
						bitmap = BitmapFactory.decodeFile(picPath,options);
						break;
					}  catch (Error   e){
						i  *= 2;
					}
				}
//				Toast.makeText(getApplicationContext(), i, Toast.LENGTH_SHORT).show(); 
				iv_pic_0107.setImageBitmap(bitmap);
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

				AdminUtils.AdminMenu(LoadPicActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
			
}
