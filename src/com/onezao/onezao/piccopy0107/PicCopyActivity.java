package com.onezao.onezao.piccopy0107;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class PicCopyActivity extends Activity {
           private ImageView iv_image_0107;
           private ImageView iv_imagecopy_0107;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_piccopy_0107);
        	iv_image_0107 = (ImageView) findViewById(R.id.iv_image_0107);
        	iv_imagecopy_0107 = (ImageView) findViewById(R.id.iv_imagecopy_0107);
        }
		
		public void showPic(View view){
			//getResources()    上下文的方法，返回应用中的res目录对象
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ctgu2012);
			iv_imagecopy_0107.setImageBitmap(bitmap);  
			//使用原图  创建一个可以修改的bitmap对象，第一个参数是图片的宽度，第二个参数是图片的高度
			//第三个参数是图片的配置信息，都从原图获取
			Bitmap bmCopy = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
			//创建一个Canvas（帆布，画布），通过这个画布向复制的bitmap中画内容
			//需要注意：这个bitmap对象必须是mutable（可以修改的），所以传入刚创建的空白图片
			Canvas  canvas = new Canvas(bmCopy);
			//通过Matrix（矩阵）可以对图片进行处理
			Matrix  matrix = new Matrix();
			//得到   画笔    paint
			Paint  paint = new Paint();
			//执行完这个方法，原图的内容就会画到创建的空白图片上
			canvas.drawBitmap(bitmap, matrix, paint);
			for(int i=0;i<bitmap.getWidth()/2;i++){
			bmCopy.setPixel(30+i,30+i,Color.BLUE);
		     	}
			iv_image_0107.setImageBitmap(bmCopy);
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

			AdminUtils.AdminMenu(PicCopyActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
