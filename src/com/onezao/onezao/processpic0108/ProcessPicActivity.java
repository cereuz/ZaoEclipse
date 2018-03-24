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
			private String  ROTATE = "��ת";
			private String  SCALE = "����";
			private String  TRANSLATE = "ƽ��";
			private TextView tv_processpic_0108;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_processpic_0108);
            	
            	tv_processpic_0108 = (TextView) findViewById(R.id.tv_processpic_0108);
            	
            	et_processpic_0108 = (EditText) findViewById(R.id.et_processpic_0108);
            	//��EditText������Buttonһ��������
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
            	//1.����ͼƬ��Դ
            	Bitmap  bitmap = BitmapFactory.decodeFile(picPath);
            	//չʾͼƬ���ؼ���
            	iv_imagecopy_0108.setImageBitmap(bitmap);
            	//2.����ͼƬ�ĸ���
            	//2.1 ����ԭͼ�Ĵ�С��������Ϣ������һ��һ����С�Ŀյ�ͼƬ
            	Bitmap bitmapCopy = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),bitmap.getConfig());
               //2.2  ׼��һ���������ѿյ�ͼƬ�ŵ�������
            	Canvas     canvas   =  new  Canvas(bitmapCopy);
            	//2.3  ͨ����������drawBitmap()������ԭͼ�����յ�ͼƬ��
            	Matrix   matrix  =  new  Matrix();
            	if(et_matrixpic.equals(ROTATE)){
            		//2.3.1������ת
            	matrix.setRotate(180, bitmapCopy.getWidth()/2, bitmapCopy.getHeight()/2);
            	     }   else  if  (et_matrixpic.equals(TRANSLATE)){
			            	//2.3.2����ƽ��
			             	matrix.setTranslate(60, 0);
            	    }   else  if (et_matrixpic.equals(SCALE)){
            	//2.3.3��������  ,  �����Ҫ���β�������Ч����Ҫ��set��post
            	matrix.setScale(1f, -1f);
            	matrix.postTranslate(0, bitmapCopy.getHeight());
            	    }
            	Paint  paint  =  new  Paint();
            	canvas.drawBitmap(bitmap, matrix, paint);
            	//2.4  �����Ƶ��ļ�չʾ���ؼ��ϡ�
            	iv_image_0108.setImageBitmap(bitmapCopy);
            }
            
            //���EditText��������ѡ��ѡ����Ҫ������
			@Override
			public void onClick(View v) {
                    int id = v.getId();
                    switch (id) {
					case R.id.et_matrixpic_0108:
				    	// ͨ��builder ������������
		        		AlertDialog.Builder   builder =  new  Builder(this);
		        		builder.setTitle("��ѡ����ϲ���Ĳ�����Ŀ");
		        		final String items[] = {MATRIX,ROTATE,SCALE,TRANSLATE};
		        		// ��ѡ�����õ�ѡ��ťѡ��  .   -1����û����Ŀ��ѡ��
		        		builder.setSingleChoiceItems(items, -1, new OnClickListener() {			
		        			@Override
		        			public void onClick(DialogInterface dialog, int which) {
		                           	//[1] ��ѡ�����Ŀ��ȡ����
		        				String item = items[which];
		        				Toast.makeText(ProcessPicActivity.this, item, Toast.LENGTH_SHORT).show();
		        				et_matrixpic_0108.setText(item);
		        				//[2] �ѶԻ���ر�
		        				dialog.dismiss();
		        			}
		        		});		
		        		//���һ����һ��Ҫ�ǵú�Toastһ����show������
		        		builder.show();
						break;
					}
			}
			
			//��ȡͼƬ��Ϣ
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
		            String text = "����ʱ�䣺"+ time + "�����豸��"+device + "���������̣�"+make;
		            Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();
		            return   text;
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

				AdminUtils.AdminMenu(ProcessPicActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
}
