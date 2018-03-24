 package com.onezao.onezao.erasecloth0111;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class EraseClothActivity extends Activity {
                     private ImageView iv_erasecloth_front;
					private Bitmap bitmapCopy;

					@Override
                    protected void onCreate(Bundle savedInstanceState) {
                    	super.onCreate(savedInstanceState);
                    	setContentView(R.layout.activity_erasecloth_0111);
                    	iv_erasecloth_front = (ImageView) findViewById(R.id.iv_erasecloth_front);
                    	
                    	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.front);
                    	bitmapCopy = Bitmap.createBitmap(bitmap.getWidth() , bitmap.getHeight() , bitmap.getConfig());
                    	//准备画布
                    	Canvas   canvas  =  new  Canvas(bitmapCopy);
                    	//把原图画到画布之上
                    	canvas.drawBitmap(bitmap, new Matrix(), new Paint());
                    	//把画好的图片设置到控件之上
                    	iv_erasecloth_front.setImageBitmap(bitmapCopy); 
                    	
                    	iv_erasecloth_front.setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View v, MotionEvent event) {
								int action = event.getAction();
								switch (action) {
								case MotionEvent.ACTION_DOWN:
									break;
								case MotionEvent.ACTION_MOVE:
									float x = event.getX();
									float y = event.getY();
							//画笔是不能移出画布区域，不然会报错，程序页面闪退
									try {
									//将画笔的每次画的区域设置大一些
									for(int i = -6; i < 6;i++){
										for(int j = -6 ;j < 6 ; j++){
											if(Math.sqrt(i*i + j*j)<= 6 ){
											//修改，将手指移动结果的像素点设置为透明.
												//这里修改的是原图的x，y对应的像素点的颜色，但是获取的是屏幕的坐标点。
												//如果图片和屏幕是点对点的显示，就不会出现问题。如果屏幕分辨率和图片像素差别比较大，则画笔移动的时候会有偏差
											bitmapCopy.setPixel((int)x + i, (int)y + j, Color.TRANSPARENT);
										   }
										}		
									  }  
									}  catch (Exception  e)  {
										
									}
									//展示图片
									iv_erasecloth_front.setImageBitmap(bitmapCopy);
									break;
								case MotionEvent.ACTION_UP:
									break;
								}
								return true;
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

						AdminUtils.AdminMenu(EraseClothActivity.this, item);
						return super.onOptionsItemSelected(item);
				 }
}
