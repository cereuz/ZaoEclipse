package com.onezao.onezao.notificationdemo0125;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NotificationDemoActivity extends Activity {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_notificationdemo_0125);
            }
            
            public void  sendNotification(View  view){
            	//ͨ��Notification.Builder  ����һ��notification
            Notification.Builder   builder = new  Builder(this);
            //֪ͨ��һ���յ���ʱ�򣬻���״̬������ʾ���֣�������־���ͨ��setTicker�����õ�
            builder.setTicker("�˻��춯֪ͨ�������ʻ�����ϴǮ�������Ѿ����췽���ᣬ�ⶳ���������ž�����ϵ���绰��13282380039");
/*            //���õ�ǰ��֪ͨ���û����֮��ͻ���ʧ��
            builder.setAutoCancel(true);*/
            //������Ч��
            long[] vib ={0,1000,2000,3000};
            builder.setVibrate(vib);
            //������ʾ�ĵƹ�
            builder.setLights(Color.BLUE, 1000, 1000);
            //����֪ͨ����
            Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.on); 
            builder.setSound(sound);
            //������֪ͨ������ʾ�Ĵ����
            builder.setContentTitle("�˻��춯֪ͨ��");
            //������֪ͨ������ʾ��С����������
            builder.setContentText("�����ʻ�����ϴǮ�������Ѿ����췽���ᣬ�ⶳ���������ž�����ϵ���绰��13282380039. �������齭·166���кͼ�԰0606����ϵ��");
            //������״̬����ʾ��Сͼ�꣬������������ã���Ȼϵͳ��������֪ͨ��Ϣ��
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setOngoing(true);
            //���֪֮ͨ����ת
            Intent  intent  =  new  Intent(this,NotificationDemoActivity.class);
            PendingIntent  intent2  =  PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(intent2);
            //����֪ͨ������֪ͨ
            Notification notification = builder.build();
            //��ȡNotificationManager
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //�������������֪ͨ�ᷢ�͵�֪ͨ����
            manager.notify(1, notification);
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
    			AdminUtils.AdminMenu(NotificationDemoActivity.this, item);
    			return super.onOptionsItemSelected(item);
    	 }
}
