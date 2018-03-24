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
            	//��ʱ��API��ȡ��Ļ��͸�
            	screenWidth = getWindowManager().getDefaultDisplay().getWidth();
            	screenHeight = getWindowManager().getDefaultDisplay().getHeight();
            	//�Ƽ���API��ȡ��Ļ��ߣ���Ļ�����Ϣ������point������
            	Point outSize = new Point();
            	getWindowManager().getDefaultDisplay().getSize(outSize);
            	screenText = "��Ļ�߶�old��"+screenHeight + "\n��Ļ���old��"+ screenWidth
            			   +  "\n��Ļ�߶�new��"+outSize.y + "\n��Ļ���new��"+ outSize.x;
            	tv_loadpic_0107.setText(screenText);
            	Toast.makeText(this, screenText, Toast.LENGTH_SHORT).show();
            }
			
			//ʵ�ֵ���¼�
			public void loadpic(View view){
				initClick();
//				loadpicDirect();
				loadpic1();
				
				//����ֱ�ӼӰ�ť�����õ���¼���Ҳ����ͨ���жϲ�ͬ����������ò�ͬ�ĵ��������
//				loadpic2();
//				loadpic3();
			}

			private void initClick() {
				et_loadpicPath = et_loadpic_0107.getText().toString().trim();
				et_picoptions = et_picoptions_0107.getText().toString().trim();
				picPath = ZaoUtils.pathSD+et_loadpicPath;
				Toast.makeText(getApplicationContext(), picPath, Toast.LENGTH_SHORT).show();
			}
             
			//�����κδ���ֱ�Ӽ�����ʾͼƬ
			private void loadpicDirect() {
				//����Bitmap����
				Bitmap bitmap = BitmapFactory.decodeFile(picPath);
				//չʾͼƬ��ImageView
				iv_pic_0107.setImageBitmap(bitmap);
			}
			
			//ʹ��Optionsѹ��ͼƬ
			public void loadpic1(){
			//Options  ѡ�����˼��ͨ������Options���󣬸���BitmapFactory�ڽ���ͼƬ��ʱ��
			//����һЩ���úõ�����ѡ�����ѹ��ͼƬ��ѹ�������������Ƿ��������ͼƬ	
			BitmapFactory.Options  options = new Options();
			//inSampleSize    sample ��������˼
			//inSampleSize  ͨ���������ֵ����ѹ��ͼƬ�����inSampleSize=2����ô���ص��ڴ��е�ͼƬ
			//�����ԭͼ��1/2  �߶�Ҳ��ԭͼ��1/2���ܵĴ�С��ԭͼ��1/4����Ҫע�⣬���inSampleSize<1,�ᰴ��1����
			//������Ĭ�ϻᰴ��2����ָ��������ͼƬ��ѹ�������Կ��Դ���2����ָ������ΪinSampleSize��ֵ
			options.inSampleSize = Integer.valueOf(et_picoptions) ;
			//ʹ�ð���options�����ķ���������ȡͼƬ
			Bitmap bitmap = BitmapFactory.decodeFile(picPath,options);
			//��ȡͼƬ���
			 int picHeight = options.outHeight;
			 int picWidth = options.outWidth;
			 String  picText2 =  "\nͼƬ�߶�1��"+picWidth + "\nͼƬ���1��"+picHeight;
			 tv_loadpic_0107.setText(screenText +picText2 +"\nͼƬ��ַ1��"+picPath + "\nѹ��������"+et_picoptions);
			iv_pic_0107.setImageBitmap(bitmap);
			}
			
			//������Ļ��С���Զ����������Ȼ�����ͼƬ
			public void loadpic2(View  view){
				//��ʼ��
				initClick();
				
				//�Ƚ�ͼƬ����Ļ�ķֱ��ʣ����ͼƬ�ֱ��ʱ���Ļ�ֱ��ʸ�
				//����ʹ��    ͼƬ���/��Ļ���
				//   ͼƬ�߶�/��Ļ�߶�
				//ͨ����������ֵ��ȷ��inSampleSize
				
				//1.��ȡ��Ļ�Ŀ��
				//2.��ȡͼƬ�Ŀ��
				BitmapFactory.Options  options = new Options();
				options.inJustDecodeBounds = true;
				Bitmap bitmap = BitmapFactory.decodeFile(picPath,options);
				int picHeight = options.outHeight;
				int picWidth = options.outWidth;
				String  picText =  "\nͼƬ�߶�2��"+picWidth + "\nͼƬ���2��"+picHeight;
				if(bitmap == null){
					tv_loadpic_0107.setText(screenText + picText  +"\nͼƬ��ַ2��"+picPath);
				}
				//3.����inSampleSize
	           //ֻ�е�ͼƬ�Ŀ�Ȼ��߸߶ȴ�����Ļ�Ŀ�Ⱥ͸߶ȵ�ʱ���ѹ��ͼƬ				
				if(picWidth > screenWidth || picHeight > screenHeight){
					//��ȡͼƬ   �� ��Ļ  ��Ӧ�Ŀ�߱������������롣
					int widthRound = Math.round((float)picWidth/(float)screenWidth);
					int heightRound = Math.round((float)picHeight/(float)screenHeight);
					//����ѹ��������ȡ��߱��������ֵ
					options.inSampleSize = Math.max(widthRound, heightRound);
				}
				//�ü���õ�inSampleSize����������ͼƬ
				options.inJustDecodeBounds = false;  
				bitmap = BitmapFactory.decodeFile(picPath,options);
				 picHeight = options.outHeight;
				 picWidth = options.outWidth;
				 String  picText2 =  "\nͼƬ�߶�22��"+picWidth + "\nͼƬ���22��"+picHeight;
				 tv_loadpic_0107.setText(screenText + picText+picText2  +"\nͼƬ��ַ22��"+picPath +"\nѹ��������"+options.inSampleSize );
				 //��ImageView��ʾͼƬ
				iv_pic_0107.setImageBitmap(bitmap);
			}
			
			//�����Դ�ķ�ʽ������inSampleSize
			public void loadpic3(View  view){
				//��ʼ��
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

				AdminUtils.AdminMenu(LoadPicActivity.this, item);
				return super.onOptionsItemSelected(item);
		 }
			
			
}
