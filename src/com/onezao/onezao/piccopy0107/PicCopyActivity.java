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
			//getResources()    �����ĵķ���������Ӧ���е�resĿ¼����
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ctgu2012);
			iv_imagecopy_0107.setImageBitmap(bitmap);  
			//ʹ��ԭͼ  ����һ�������޸ĵ�bitmap���󣬵�һ��������ͼƬ�Ŀ�ȣ��ڶ���������ͼƬ�ĸ߶�
			//������������ͼƬ��������Ϣ������ԭͼ��ȡ
			Bitmap bmCopy = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
			//����һ��Canvas����������������ͨ������������Ƶ�bitmap�л�����
			//��Ҫע�⣺���bitmap���������mutable�������޸ĵģ������Դ���մ����Ŀհ�ͼƬ
			Canvas  canvas = new Canvas(bmCopy);
			//ͨ��Matrix�����󣩿��Զ�ͼƬ���д���
			Matrix  matrix = new Matrix();
			//�õ�   ����    paint
			Paint  paint = new Paint();
			//ִ�������������ԭͼ�����ݾͻử�������Ŀհ�ͼƬ��
			canvas.drawBitmap(bitmap, matrix, paint);
			for(int i=0;i<bitmap.getWidth()/2;i++){
			bmCopy.setPixel(30+i,30+i,Color.BLUE);
		     	}
			iv_image_0107.setImageBitmap(bmCopy);
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

			AdminUtils.AdminMenu(PicCopyActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
