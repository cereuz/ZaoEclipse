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
            	//通过Notification.Builder  创建一个notification
            Notification.Builder   builder = new  Builder(this);
            //通知第一次收到的时候，会在状态栏中显示文字，这个文字就是通过setTicker来设置的
            builder.setTicker("账户异动通知：您的帐户涉嫌洗钱操作，已经被检方冻结，解冻事宜请与张警官联系。电话：13282380039");
/*            //设置当前的通知，用户点击之后就会消失掉
            builder.setAutoCancel(true);*/
            //设置震动效果
            long[] vib ={0,1000,2000,3000};
            builder.setVibrate(vib);
            //设置显示的灯光
            builder.setLights(Color.BLUE, 1000, 1000);
            //设置通知声音
            Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.on); 
            builder.setSound(sound);
            //设置在通知栏中显示的大标题
            builder.setContentTitle("账户异动通知！");
            //设置在通知栏中显示的小的文字内容
            builder.setContentText("您的帐户涉嫌洗钱操作，已经被检方冻结，解冻事宜请与张警官联系。电话：13282380039. 请速与衢江路166号中和家园0606号联系！");
            //设置在状态栏显示的小图标，【这个必须设置，不然系统会忽略这个通知消息】
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setOngoing(true);
            //点击通知之后跳转
            Intent  intent  =  new  Intent(this,NotificationDemoActivity.class);
            PendingIntent  intent2  =  PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(intent2);
            //创建通知，发送通知
            Notification notification = builder.build();
            //获取NotificationManager
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //调用这个方法，通知会发送到通知栏中
            manager.notify(1, notification);
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
    			AdminUtils.AdminMenu(NotificationDemoActivity.this, item);
    			return super.onOptionsItemSelected(item);
    	 }
}
