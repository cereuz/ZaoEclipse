package com.onezao.onezao.viewanim0124;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.R.anim;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewAnimationActivity extends Activity {
              private ImageView iv_viewanim_0124;

			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_viewanim_0124);
            	iv_viewanim_0124 = (ImageView) findViewById(R.id.iv_viewanim_0124);
            }
              
              //͸��
              public  void  alpha2(View  view){
            	  Toast.makeText(getApplicationContext(), "alpha", Toast.LENGTH_SHORT).show();
            	  //AlphaAnimation͸���ȶ�����
            	  //������������һ��������������ʼ��ʱ���͸���ȣ��ڶ�������������������ʱ���͸����
            	  AlphaAnimation    alphaAnim   =  new  AlphaAnimation(1.0f, 0.0f);
            	  //���ö���ִ�е�ʱ�䣬��λ�Ǻ���ֵ
            	  alphaAnim.setDuration(2000);
            	  //���ö����ظ���ģʽ,Animation.REVERSE����ִ�У�Animation.RESTART�ظ���һ��
            	  alphaAnim.setRepeatMode(Animation.REVERSE);
            	  //���ö����ظ����������Ϊ0����ִֻ��һ�ζ������������0������òŻ���Ч
            	  alphaAnim.setRepeatCount(6);
            	  //���ö���ִ��֮��ִ�ж����Ŀؼ�ͣ���ڽ�����״̬��Ĭ����false����ִ�����˻ָ�Ϊ��ʼ״̬��
            	  alphaAnim.setFillAfter(true);
            	  //�ѵ�ǰ�������õ�ImageView��
            	  iv_viewanim_0124.setAnimation(alphaAnim);
            	  //��������
            	  alphaAnim.start();
              }
              
              //��ת
              public  void  rotate2(View  view){
            	  Toast.makeText(getApplicationContext(), "rotate", Toast.LENGTH_SHORT).show();
            	  //��һ��������������ʼ��ʱ���ڵĽǶ�
            	  //�ڶ�������������������ʱ��ĽǶ�
            	  RotateAnimation   rotateAnim = new  RotateAnimation(0, 540);
            	  //����ִ�е�ʱ��
            	  rotateAnim.setDuration(6000);
            	  //�����ظ���ģʽ
            	  rotateAnim.setRepeatMode(Animation.REVERSE);
            	  //�����ظ��Ĵ���
            	  rotateAnim.setRepeatCount(3);
            	  //����ͣ����������״̬
            	  rotateAnim.setFillAfter(true);
            	  //�ѵ�ǰ�������õ�ImageView��
            	  iv_viewanim_0124.setAnimation(rotateAnim);
            	  //��������
            	  rotateAnim.start();
              }
              
              //����
              public  void  scale2(View  view){
            	  Toast.makeText(getApplicationContext(), "scale", Toast.LENGTH_SHORT).show();
            	  ScaleAnimation  scaleAnim = new  ScaleAnimation(1, 2,1,2);
            	  scaleAnim.setDuration(3000);
            	  scaleAnim.setRepeatCount(4);
            	  scaleAnim.setRepeatMode(Animation.REVERSE);
            	  scaleAnim.setFillAfter(true);
            	  iv_viewanim_0124.setAnimation(scaleAnim);
            	  scaleAnim.start();
              }
              
              //ƽ��
              public  void  translate2(View  view){
            	  Toast.makeText(getApplicationContext(), "translate", Toast.LENGTH_SHORT).show();
            	  TranslateAnimation   translateAnim  =  new TranslateAnimation(10, 1000, 0, 600);
            	  translateAnim.setDuration(2000);
            	  translateAnim.setRepeatMode(Animation.REVERSE);
            	  translateAnim.setRepeatCount(4);
            	  translateAnim.setFillAfter(true);
            	  iv_viewanim_0124.setAnimation(translateAnim);
            	  translateAnim.start();
              }
              
              //���
              public  void  set(View  view){
            	  Toast.makeText(getApplicationContext(), "set", Toast.LENGTH_SHORT).show();
            	  AnimationSet   animSet   =  new  AnimationSet(false);
            	  
            	  //AlphaAnimation͸���ȶ�����
            	  //������������һ��������������ʼ��ʱ���͸���ȣ��ڶ�������������������ʱ���͸����
            	  AlphaAnimation    alphaAnim   =  new  AlphaAnimation(1.0f, 0.0f);
            	  //���ö���ִ�е�ʱ�䣬��λ�Ǻ���ֵ
            	  alphaAnim.setDuration(2000);
            	  //���ö����ظ���ģʽ,Animation.REVERSE����ִ�У�Animation.RESTART�ظ���һ��
            	  alphaAnim.setRepeatMode(Animation.REVERSE);
            	  //���ö����ظ����������Ϊ0����ִֻ��һ�ζ������������0������òŻ���Ч
            	  alphaAnim.setRepeatCount(6);
            	  //���ö���ִ��֮��ִ�ж����Ŀؼ�ͣ���ڽ�����״̬��Ĭ����false����ִ�����˻ָ�Ϊ��ʼ״̬��
            	  alphaAnim.setFillAfter(true);
            	  animSet.addAnimation(alphaAnim);
            	  
               	  //��һ��������������ʼ��ʱ���ڵĽǶ�
            	  //�ڶ�������������������ʱ��ĽǶ�
            	  RotateAnimation   rotateAnim = new  RotateAnimation(0, 540);
            	  //����ִ�е�ʱ��
            	  rotateAnim.setDuration(6000);
            	  //�����ظ���ģʽ
            	  rotateAnim.setRepeatMode(Animation.REVERSE);
            	  //�����ظ��Ĵ���
            	  rotateAnim.setRepeatCount(3);
            	  //����ͣ����������״̬
            	  rotateAnim.setFillAfter(true);
            	  animSet.addAnimation(rotateAnim);
            	  
             	  ScaleAnimation  scaleAnim = new  ScaleAnimation(1, 2,1,2);
            	  scaleAnim.setDuration(3000);
            	  scaleAnim.setRepeatCount(4);
            	  scaleAnim.setRepeatMode(Animation.REVERSE);
            	  scaleAnim.setFillAfter(true);
            	  animSet.addAnimation(scaleAnim);
            	  
            	  TranslateAnimation   translateAnim  =  new TranslateAnimation(10, 1000, 0, 600);
            	  translateAnim.setDuration(2000);
            	  translateAnim.setRepeatMode(Animation.REVERSE);
            	  translateAnim.setRepeatCount(4);
            	  translateAnim.setFillAfter(true);
            	  animSet.addAnimation(translateAnim);
            	  
            	  iv_viewanim_0124.startAnimation(animSet);
              }
              
              
              
              
              //͸��2
              public  void  alpha(View  view){
            	  Toast.makeText(getApplicationContext(), "alpha2", Toast.LENGTH_SHORT).show();
            	  //AlphaAnimation͸���ȶ�����
            	  //������������һ��������������ʼ��ʱ���͸���ȣ��ڶ�������������������ʱ���͸����
            	  AlphaAnimation    alphaAnim   =  new  AlphaAnimation(1.0f, 0.1f);
            	  //���ö���ִ�е�ʱ�䣬��λ�Ǻ���ֵ
            	  alphaAnim.setDuration(2000);
            	  //���ö����ظ���ģʽ,Animation.REVERSE����ִ�У�Animation.RESTART�ظ���һ��
            	  alphaAnim.setRepeatMode(Animation.RESTART);
            	  //���ö����ظ����������Ϊ0����ִֻ��һ�ζ������������0������òŻ���Ч
            	  alphaAnim.setRepeatCount(6);
            	  //���ö���ִ��֮��ִ�ж����Ŀؼ�ͣ���ڽ�����״̬��Ĭ����false����ִ�����˻ָ�Ϊ��ʼ״̬��
            	  alphaAnim.setFillAfter(true);
            	  //�ѵ�ǰ�������õ�ImageView��
            	  iv_viewanim_0124.startAnimation(alphaAnim);
              }
              
              //��ת2
              public  void  rotate(View  view){
            	  Toast.makeText(getApplicationContext(), "rotate2", Toast.LENGTH_SHORT).show();
            	  //��һ��������������ʼ��ʱ���ڵĽǶ�
            	  //�ڶ�������������������ʱ��ĽǶ�
            	  float  fromDegrees = 0;
            	  float  toDegrees =  360;
            	  //��ת���ĵ�X���������
            	  int pivoteXType  =  Animation.RELATIVE_TO_SELF;
            	  //��ת���ĵ�X�����ֵ
            	  float  pivotXValue  =  0.5f;
            	  //��ת���ĵ�Y���������
            	  int pivoteYValue = Animation.RELATIVE_TO_SELF;
            	  //��ת���ĵ�Y�����ֵ
            	  float  pivotYValue  =  0.5f;
            	  RotateAnimation   rotateAnim = new  RotateAnimation(fromDegrees, toDegrees, pivoteXType, pivotXValue, pivoteYValue, pivotYValue);
            	  
            	  //����ִ�е�ʱ��
            	  rotateAnim.setDuration(6000);
            	  //�����ظ���ģʽ
            	  rotateAnim.setRepeatMode(Animation.REVERSE);
            	  //�����ظ��Ĵ���
            	  rotateAnim.setRepeatCount(3);
            	  //����ͣ����������״̬
            	  rotateAnim.setFillAfter(true);
            	  //�ѵ�ǰ�������õ�ImageView��,��������
            	  iv_viewanim_0124.startAnimation(rotateAnim);
              }
              
              //����2
              public  void  scale(View  view){
            	  Toast.makeText(getApplicationContext(), "scale", Toast.LENGTH_SHORT).show();
            	  int  pivotXType  =  Animation.RELATIVE_TO_SELF;
            	  float  pivotXValue  = 0.5f;
            	  int  pivotYType  =  Animation.RELATIVE_TO_SELF;
            	  float  pivotYValue  = 0.5f;
            	  ScaleAnimation  scaleAnim = new  ScaleAnimation(1, 2,1,1,pivotXType,pivotXValue,pivotYType,pivotYValue);
            	  scaleAnim.setDuration(3000);
            	  scaleAnim.setRepeatCount(4);
            	  scaleAnim.setRepeatMode(Animation.REVERSE);
            	  scaleAnim.setFillAfter(false);
            	  iv_viewanim_0124.startAnimation(scaleAnim);
              }
              
              //ƽ��2
              public  void  translate(View  view){
            	  Toast.makeText(getApplicationContext(), "translate", Toast.LENGTH_SHORT).show();
            	  int  fromXType  =  Animation.RELATIVE_TO_PARENT;
            	  float  fromXValue = -0.5f;
            	  int  toXType  = Animation.RELATIVE_TO_PARENT;
            	  float  toXValue = 0.5f;
            	  int  fromYType  = Animation.RELATIVE_TO_PARENT;
            	  float fromYValue  =  - 0.5f;
            	  int  toYType  = Animation.RELATIVE_TO_PARENT;
            	  float  toYValue = 0.5f;
            	  TranslateAnimation   translateAnim  =  new TranslateAnimation(fromXType, fromXValue, toXType, toXValue,fromYType,fromYValue,toYType,toYValue);
            	  translateAnim.setDuration(2000);
            	  translateAnim.setRepeatMode(Animation.REVERSE);
            	  translateAnim.setRepeatCount(4);
            	  translateAnim.setFillAfter(false);
            	  //����һ��������������������ִ�еĹ����м�����٣����٣��ظ���Ч��
            	  //���ղ���Interpolator����������������ȥ��ϵͳ�Ѿ�ʵ�ֺõĶ���
            	  translateAnim.setInterpolator(new  BounceInterpolator());
            	  iv_viewanim_0124.startAnimation(translateAnim);
              }
              
              //���2
              public  void  set2(View  view){
            	  
              }
              
              
              
              
              
              
              
              
              
          	//���ض����˵�����Ӳ˵��ĵ���¼���
      		@Override
      		public boolean onCreateOptionsMenu(Menu menu) {
      			  //�������Ͻǵ�ͼ��ĵ���¼�  ActionBar
      		      ActionBar actionBar = this.getActionBar();
      		     actionBar.setHomeButtonEnabled(true);
      			// Inflate the menu; this adds items to the action bar if it is present.
      			getMenuInflater().inflate(R.menu.menu_anim_0124, menu);
      			return true;
      		}
      		
      		
      		/*
      		 * ʹ��XML�ļ������嶯��     ͨ��AnimationUtils��loadAnimation()����
      		 */
      		//͸��
      		public  void  alpha3(){
      			Animation loadAlphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha0124);
      			iv_viewanim_0124.startAnimation(loadAlphaAnimation);
      		}
      		//��ת
      		public  void  rotate3(){
      			Animation loadRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate0124);
      			iv_viewanim_0124.startAnimation(loadRotateAnimation);
      		}
      		//����
      		public  void  scale3(){
      			Animation loadScaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale0124);
      			iv_viewanim_0124.startAnimation(loadScaleAnimation);
      		}
      		//ƽ��
      		public  void  translate3(){
      			Animation loadtranslateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate0124);
      			iv_viewanim_0124.startAnimation(loadtranslateAnimation);
      		}
      		//��� 
      		public  void  animset3(){
      			AnimationSet loadSetAnimation = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animset0124);
      			iv_viewanim_0124.startAnimation(loadSetAnimation);
      		}
      		
      		
      		@Override
      		public boolean onOptionsItemSelected(MenuItem item) {

      			int id = item.getItemId();
      	 		if (id == R.id.alpha) {
      	 			Toast.makeText(this, "͸��������", Toast.LENGTH_SHORT).show();
      	 			alpha3();
      	 /*		Intent  intent  =  new  Intent(this,WritediaryActivity.class);
      	 			startActivity(intent);*/
      	 			return true;
      	 		}  else  if (id == R.id.rotate) {
      	 			Toast.makeText(this, "��ת������", Toast.LENGTH_SHORT).show();
      	 			rotate3();
      	 			/*			Intent  intent  =  new  Intent(this,CheckActivity.class);
      	 			startActivity(intent);*/
      	 			return true;
      	 		}  else  if (id == R.id.scale) {
      	 			Toast.makeText(this, "���š�����", Toast.LENGTH_SHORT).show();
      	 		 	scale3();
      	 /*			Intent  intent  =  new  Intent(this,SettingActivity.class);
      	 			startActivity(intent);*/
      	 			return true;
      	 		}  else  if (id == R.id.translate) {
      	 			Toast.makeText(this, "ƽ�ơ�����", Toast.LENGTH_SHORT).show();
      	 		     translate3();
      	 /*	    Intent  intent  =  new  Intent(this,AboutActivity.class);
      	 			startActivity(intent);*/
      	 			return true;
      	 		}  else  if (id == R.id.animset) {
      	 			Toast.makeText(this, "���", Toast.LENGTH_SHORT).show();
      	 			animset3();
      	 			/*Intent  intent  =  new  Intent(this,AboutActivity.class);
      	 			startActivity(intent);*/
      	 			return true;
      	 		}   else  if (id == android.R.id.home) {
      	 			Toast.makeText(this, "���", Toast.LENGTH_SHORT).show();
      	 			animset3();
      	 			/*Intent  intent  =  new  Intent(this,AboutActivity.class);
      	 			startActivity(intent);*/
      	 			return true;
      	 		}   else  if (id ==  R.id.action_search) {
      	 			Toast.makeText(this, "����һ�£�onezao��ȥ���ظ�", Toast.LENGTH_SHORT).show();
      	 			return true;
      	 	}
      			return super.onOptionsItemSelected(item);
      	 }
}
