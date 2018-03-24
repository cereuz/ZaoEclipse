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
                    	//׼������
                    	Canvas   canvas  =  new  Canvas(bitmapCopy);
                    	//��ԭͼ��������֮��
                    	canvas.drawBitmap(bitmap, new Matrix(), new Paint());
                    	//�ѻ��õ�ͼƬ���õ��ؼ�֮��
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
							//�����ǲ����Ƴ��������򣬲�Ȼ�ᱨ������ҳ������
									try {
									//�����ʵ�ÿ�λ����������ô�һЩ
									for(int i = -6; i < 6;i++){
										for(int j = -6 ;j < 6 ; j++){
											if(Math.sqrt(i*i + j*j)<= 6 ){
											//�޸ģ�����ָ�ƶ���������ص�����Ϊ͸��.
												//�����޸ĵ���ԭͼ��x��y��Ӧ�����ص����ɫ�����ǻ�ȡ������Ļ������㡣
												//���ͼƬ����Ļ�ǵ�Ե����ʾ���Ͳ���������⡣�����Ļ�ֱ��ʺ�ͼƬ���ز��Ƚϴ��򻭱��ƶ���ʱ�����ƫ��
											bitmapCopy.setPixel((int)x + i, (int)y + j, Color.TRANSPARENT);
										   }
										}		
									  }  
									}  catch (Exception  e)  {
										
									}
									//չʾͼƬ
									iv_erasecloth_front.setImageBitmap(bitmapCopy);
									break;
								case MotionEvent.ACTION_UP:
									break;
								}
								return true;
							}
						});
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

						AdminUtils.AdminMenu(EraseClothActivity.this, item);
						return super.onOptionsItemSelected(item);
				 }
}
