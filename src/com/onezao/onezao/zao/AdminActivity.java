package com.onezao.onezao.zao;

import com.onezao.onezao.alertdialog1213.AlertDialogActivity;
import com.onezao.onezao.autocomplete0123.AutoCompleteActivity;
import com.onezao.onezao.autosmslistener1212.AutoSmsReceiverActivity;
import com.onezao.onezao.bindservice1214.BindServiceActivity;
import com.onezao.onezao.contentprovider1224.ContentProviderActivity;
import com.onezao.onezao.database2listview.DataToListActivity;
import com.onezao.onezao.dialer1113.DialerActivity0001;
import com.onezao.onezao.dialer1113.DialerActivity0002;
import com.onezao.onezao.dialer1113.DialerActivity0003;
import com.onezao.onezao.dialer1113.DialerActivity0004;
import com.onezao.onezao.drawableanim0123.DrawableAnimationActivity;
import com.onezao.onezao.erasecloth0111.EraseClothActivity;
import com.onezao.onezao.fragmentcomm0122.FragmentCommActivity;
import com.onezao.onezao.fragmentdemo0116.FragmentDemoActivity;
import com.onezao.onezao.fragmentdynamic0118.FragmentDynamicActivity;
import com.onezao.onezao.handlerdelay1127.DelayHandlerActivity;
import com.onezao.onezao.htmlcodeviewer1124.HtmlCodeActivity;
import com.onezao.onezao.htmlcodeviewer1124.HtmlCodeHandlerActivity;
import com.onezao.onezao.htmlcodeviewer1124.ShowImageActivity;
import com.onezao.onezao.insertcontact0101.InsertContactActivity;
import com.onezao.onezao.ipdialer1210.IPDialerActivity;
import com.onezao.onezao.listview1121.ArrayAdapterActivity;
import com.onezao.onezao.listview1121.ListViewActivity;
import com.onezao.onezao.listview1121.MoreListViewActivity;
import com.onezao.onezao.listview1121.SimpleAdapterActivity;
import com.onezao.onezao.loadpic0107.LoadPicActivity;
import com.onezao.onezao.login1114.LoginActivity;
import com.onezao.onezao.login1114.LoginSpActivity;
import com.onezao.onezao.mixstartservice1217.MixStartActivity;
import com.onezao.onezao.mobilesafe0207.activity.SplashActivity;
import com.onezao.onezao.musicframework1216.MusicPlayerFrameActivity;
import com.onezao.onezao.musicplayer0111.MusicPlayerActivity;
import com.onezao.onezao.notificationdemo0125.NotificationDemoActivity;
import com.onezao.onezao.paintboard0108.PaintBoardActivity;
import com.onezao.onezao.phonerecorder1214.RecordServiceActivity;
import com.onezao.onezao.piccopy0107.PicCopyActivity;
import com.onezao.onezao.picgallery0110.PicGalleryActivity;
import com.onezao.onezao.playnetmusic0113.PlayNetMusicActivity;
import com.onezao.onezao.playnetvideo0113.PlayNetVideoActivity;
import com.onezao.onezao.pressurediagram0130.PressureDiagramActivity;
import com.onezao.onezao.processpic0108.ProcessPicActivity;
import com.onezao.onezao.querycontact1229.QueryContactActivity;
import com.onezao.onezao.rigisterreceiver1212.RigisterReceiverActivity;
import com.onezao.onezao.rpcalc1207.RpCalcActivity;
import com.onezao.onezao.sdcardspace1115.SpaceActivity;
import com.onezao.onezao.sendbroadcast1211.SendBroadcastActivity;
import com.onezao.onezao.sendrice1211.SendOrderBroadcastActivity;
import com.onezao.onezao.sendsms1208.SmsActivity;
import com.onezao.onezao.smsbackup1225.SmsBackupActivity;
import com.onezao.onezao.smslistener1210.SmsReceiverActivity;
import com.onezao.onezao.smssender1209.SmsSenderActivity;
import com.onezao.onezao.sqlite1118.SqliteActivity;
import com.onezao.onezao.sqlite1118.TransActivity;
import com.onezao.onezao.startservice1214.ServiceActivity;
import com.onezao.onezao.videoview0114.VideoViewActivity;
import com.onezao.onezao.viewanim0124.ViewAnimationActivity;
import com.onezao.onezao.weixin0121.WeixinActivity;
import com.onezao.onezao.xmlseralizer1116.XmlSeralizerActivity;
import com.onezao.onezao.zhihurss1129.ZhihuRssActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminActivity extends Activity  implements  OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_activity_admin);
		
		Button button_toDial_1113 = (Button) findViewById(R.id.button_toDial_1113);
		button_toDial_1113.setOnClickListener(this);		
		Button  button_toDial_1113_02 = (Button) findViewById(R.id.button_toDial_1113_02);
		button_toDial_1113_02.setOnClickListener(this);		
		Button button_toDial_1113_03 = (Button)findViewById(R.id.button_toDial_1113_03);
		button_toDial_1113_03.setOnClickListener(this);
		Button button_toDial_1113_04 = (Button)findViewById(R.id.button_toDial_1113_04);
		button_toDial_1113_04.setOnClickListener(this);
		Button button_toLoginActivity_1114 = (Button)findViewById(R.id.button_toLoginActivity_1114);
		button_toLoginActivity_1114.setOnClickListener(this);
		Button button_toSpaceActivity_1115 = (Button)findViewById(R.id.button_toSpaceActivity_1115);
		button_toSpaceActivity_1115.setOnClickListener(this);
		Button button_toLoginSpActivity_1115 = (Button)findViewById(R.id.button_toLoginSpActivity_1115);
		button_toLoginSpActivity_1115.setOnClickListener(this);
		Button button_toXmlSeralizerActivity_1115 = (Button)findViewById(R.id.button_toXmlSeralizerActivity_1115);
		button_toXmlSeralizerActivity_1115.setOnClickListener(this);
		Button button_toSqliteActivity_1118 = (Button)findViewById(R.id.button_toSqliteActivity_1118);
		button_toSqliteActivity_1118.setOnClickListener(this);
		Button button_toTransActivity_1118 = (Button)findViewById(R.id.button_toTransActivity_1118);
		button_toTransActivity_1118.setOnClickListener(this);
		Button button_toListViewActivity_1121 = (Button)findViewById(R.id.button_toListViewActivity_1121);
		button_toListViewActivity_1121.setOnClickListener(this);
		Button button_toMoreListViewActivity_1121 = (Button)findViewById(R.id.button_toMoreListViewActivity_1121);
		button_toMoreListViewActivity_1121.setOnClickListener(this);
		Button button_toArrayAdapterActivity_1121 = (Button)findViewById(R.id.button_toArrayAdapterActivity_1121);
		button_toArrayAdapterActivity_1121.setOnClickListener(this);
		Button button_toSimpleAdapterActivity_1121 = (Button)findViewById(R.id.button_toSimpleAdapterActivity_1121);
		button_toSimpleAdapterActivity_1121.setOnClickListener(this);
		Button button_toDatabaseToListActivity_1121 = (Button)findViewById(R.id.button_toDatabaseToListActivity_1121);
		button_toDatabaseToListActivity_1121.setOnClickListener(this);
		Button button_toHtmlCodeActivity_1124 = (Button)findViewById(R.id.button_toHtmlCodeActivity_1124);
		button_toHtmlCodeActivity_1124.setOnClickListener(this);
		Button button_toHtmlCodeHandlerActivity_1124 = (Button)findViewById(R.id.button_toHtmlCodeHandlerActivity_1124);
		button_toHtmlCodeHandlerActivity_1124.setOnClickListener(this);
		Button button_toShowImageActivity_1124 = (Button)findViewById(R.id.button_toShowImageActivity_1124);
		button_toShowImageActivity_1124.setOnClickListener(this);
		Button button_toDelayHandlerActivity_1127 = (Button)findViewById(R.id.button_toDelayHandlerActivity_1127);
		button_toDelayHandlerActivity_1127.setOnClickListener(this);
		Button button_toZhihuRssActivity_1129 = (Button)findViewById(R.id.button_toZhihuRssActivity_1129);
		button_toZhihuRssActivity_1129.setOnClickListener(this);
		Button button_toZaoNoteActivity_1205 = (Button)findViewById(R.id.button_toZaoNoteActivity_1205);
		button_toZaoNoteActivity_1205.setOnClickListener(this);
		Button button_toRpCalcActivity_1207 = (Button)findViewById(R.id.button_toRpCalcActivity_1207);
		button_toRpCalcActivity_1207.setOnClickListener(this);
		Button button_toSmsActivity_1208 = (Button)findViewById(R.id.button_toSmsActivity_1208);
		button_toSmsActivity_1208.setOnClickListener(this);
		Button button_toSmsSenderActivity_1209 = (Button)findViewById(R.id.button_toSmsSenderActivity_1209);
		button_toSmsSenderActivity_1209.setOnClickListener(this);
		Button button_toIPDialerActivity_1210 = (Button)findViewById(R.id.button_toIPDialerActivity_1210);
		button_toIPDialerActivity_1210.setOnClickListener(this);
		Button button_toSmsReveicerActivity_1210 = (Button)findViewById(R.id.button_toSmsReveicerActivity_1210);
		button_toSmsReveicerActivity_1210.setOnClickListener(this);
		Button button_toSendBroadcastActivity_1211 = (Button)findViewById(R.id.button_toSendBroadcastActivity_1211);
		button_toSendBroadcastActivity_1211.setOnClickListener(this);
		Button button_toSendOrderBroadcastActivity_1211 = (Button)findViewById(R.id.button_toSendOrderBroadcastActivity_1211);
		button_toSendOrderBroadcastActivity_1211.setOnClickListener(this);
		Button button_toRigisterReceiverActivity_1212 = (Button)findViewById(R.id.button_toRigisterReceiverActivity_1212);
		button_toRigisterReceiverActivity_1212.setOnClickListener(this);
		Button button_toAutoSmsReceiverActivity_1212 = (Button)findViewById(R.id.button_toAutoSmsReceiverActivity_1212);
		button_toAutoSmsReceiverActivity_1212.setOnClickListener(this);
		Button button_toAlertDialogActivity_1213 = (Button)findViewById(R.id.button_toAlertDialogActivity_1213);
		button_toAlertDialogActivity_1213.setOnClickListener(this);
		Button button_toServiceActivity_1214 = (Button)findViewById(R.id.button_toServiceActivity_1214);
		button_toServiceActivity_1214.setOnClickListener(this);
		Button button_toRecordServiceActivity_1214 = (Button)findViewById(R.id.button_toRecordServiceActivity_1214);
		button_toRecordServiceActivity_1214.setOnClickListener(this);
		Button button_toBindServiceActivity_1214 = (Button)findViewById(R.id.button_toBindServiceActivity_1214);
		button_toBindServiceActivity_1214.setOnClickListener(this);
		Button button_toMusicPlayerFrameActivity_1216 = (Button)findViewById(R.id.button_toMusicPlayerFrameActivity_1216);
		button_toMusicPlayerFrameActivity_1216.setOnClickListener(this);
		Button button_toMixStartActivity_1217 = (Button)findViewById(R.id.button_toMixStartActivity_1217);
		button_toMixStartActivity_1217.setOnClickListener(this);
		Button button_toContentProviderActivity_1224 = (Button)findViewById(R.id.button_toContentProviderActivity_1224);
		button_toContentProviderActivity_1224.setOnClickListener(this);
		Button button_toSmsBackupActivity_1225 = (Button)findViewById(R.id.button_toSmsBackupActivity_1225);
		button_toSmsBackupActivity_1225.setOnClickListener(this);
		Button button_toQueryContactActivity_1229 = (Button)findViewById(R.id.button_toQueryContactActivity_1229);
		button_toQueryContactActivity_1229.setOnClickListener(this);
		Button button_toInsertContactActivity_0101 = (Button)findViewById(R.id.button_toInsertContactActivity_0101);
		button_toInsertContactActivity_0101.setOnClickListener(this);
		Button button_toLoadPicActivity_0107 = (Button)findViewById(R.id.button_toLoadPicActivity_0107);
		button_toLoadPicActivity_0107.setOnClickListener(this);
		Button button_toPicCopyActivity_0107 = (Button)findViewById(R.id.button_toPicCopyActivity_0107);
		button_toPicCopyActivity_0107.setOnClickListener(this);
		Button button_toProcessPicActivity_0108 = (Button)findViewById(R.id.button_toProcessPicActivity_0108);
		button_toProcessPicActivity_0108.setOnClickListener(this);
		Button button_toPaintBoardActivity_0108 = (Button)findViewById(R.id.button_toPaintBoardActivity_0108);
		button_toPaintBoardActivity_0108.setOnClickListener(this);
		Button button_toPicGalleryActivity_0110 = (Button)findViewById(R.id.button_toPicGalleryActivity_0110);
		button_toPicGalleryActivity_0110.setOnClickListener(this);
		Button button_toEraseClothActivity_0111 = (Button)findViewById(R.id.button_toEraseClothActivity_0111);
		button_toEraseClothActivity_0111.setOnClickListener(this);
		Button button_toMusicPlayerActivity_0111 = (Button)findViewById(R.id.button_toMusicPlayerActivity_0111);
		button_toMusicPlayerActivity_0111.setOnClickListener(this);
		Button button_toPlayNetMusicActivity_0113 = (Button)findViewById(R.id.button_toPlayNetMusicActivity_0113);
		button_toPlayNetMusicActivity_0113.setOnClickListener(this);
		Button button_toPlayNetVideoActivity_0113 = (Button)findViewById(R.id.button_toPlayNetVideoActivity_0113);
		button_toPlayNetVideoActivity_0113.setOnClickListener(this);
		Button button_toVideoViewActivity_0114 = (Button)findViewById(R.id.button_toVideoViewActivity_0114);
		button_toVideoViewActivity_0114.setOnClickListener(this);
		Button button_toFragmentDemoActivity_0116 = (Button)findViewById(R.id.button_toFragmentDemoActivity_0116);
		button_toFragmentDemoActivity_0116.setOnClickListener(this);
		Button button_toFragmentDynamicActivity_0118 = (Button)findViewById(R.id.button_toFragmentDynamicActivity_0118);
		button_toFragmentDynamicActivity_0118.setOnClickListener(this);
		Button button_toWeixinActivity_0121 = (Button)findViewById(R.id.button_toWeixinActivity_0121);
		button_toWeixinActivity_0121.setOnClickListener(this);
		Button button_toFragmentCommActivity_0122 = (Button)findViewById(R.id.button_toFragmentCommActivity_0122);
		button_toFragmentCommActivity_0122.setOnClickListener(this);
		Button button_toAutoCompleteActivity_0123 = (Button)findViewById(R.id.button_toAutoCompleteActivity_0123);
		button_toAutoCompleteActivity_0123.setOnClickListener(this);
		Button button_toDrawableanim_0123 = (Button)findViewById(R.id.button_toDrawableanim_0123);
		button_toDrawableanim_0123.setOnClickListener(this);
		Button button_toViewAnimActivity_0124 = (Button)findViewById(R.id.button_toViewAnimActivity_0124);
		button_toViewAnimActivity_0124.setOnClickListener(this);
		Button button_toNotificationDemoActivity_0125 = (Button)findViewById(R.id.button_toNotificationDemoActivity_0125);
		button_toNotificationDemoActivity_0125.setOnClickListener(this);
		Button button_toPressureDiagramActivity_0130 = (Button)findViewById(R.id.button_toPressureDiagramActivity_0130);
		button_toPressureDiagramActivity_0130.setOnClickListener(this);
		Button button_toSplashActivity_0207 = (Button)findViewById(R.id.button_toSplashActivity_0207);
		button_toSplashActivity_0207.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
           	switch (v.getId()) {
			case R.id.button_toDial_1113:
				Intent  intent = new Intent(this,DialerActivity0001.class);
				startActivity(intent);
				break;
			case R.id.button_toDial_1113_02:
				Intent intent2 = new Intent(this,DialerActivity0002.class);
				startActivity(intent2);				
				break;
			case R.id.button_toDial_1113_03:
				Intent intent3 = new Intent(this,DialerActivity0003.class);
				startActivity(intent3);
				break;
			case R.id.button_toDial_1113_04:
				Intent intent4 = new Intent(this,DialerActivity0004.class);
				startActivity(intent4);
				break;
			case R.id.button_toLoginActivity_1114:
				Intent intent5 = new Intent(this,LoginActivity.class);
				startActivity(intent5);
				break;
			case R.id.button_toSpaceActivity_1115:
				Intent intent6 = new Intent(this,SpaceActivity.class);
				startActivity(intent6);
				break;
			case R.id.button_toLoginSpActivity_1115:
				Intent intent7 = new Intent(this,LoginSpActivity.class);
				startActivity(intent7);
				break;
			case R.id.button_toXmlSeralizerActivity_1115:
				Intent intent8 = new Intent(this,XmlSeralizerActivity.class);
				startActivity(intent8);
				break;
			case R.id.button_toSqliteActivity_1118:
				Intent intent9 = new Intent(this,SqliteActivity.class);
				startActivity(intent9);
				break;
			case R.id.button_toTransActivity_1118:
				Intent intent10 = new Intent(this,TransActivity.class);
				startActivity(intent10);
				break;
			case R.id.button_toListViewActivity_1121:
				Intent intent11 = new Intent(this,ListViewActivity.class);
				startActivity(intent11);
				break;
			case R.id.button_toMoreListViewActivity_1121:
				Intent intent12 = new Intent(this,MoreListViewActivity.class);
				startActivity(intent12);
				break;
			case R.id.button_toArrayAdapterActivity_1121:
				Intent intent13 = new Intent(this,ArrayAdapterActivity.class);
				startActivity(intent13);
				break;
			case R.id.button_toSimpleAdapterActivity_1121:
				Intent intent14 = new Intent(this,SimpleAdapterActivity.class);
				startActivity(intent14);
				break;
			case R.id.button_toDatabaseToListActivity_1121:
				Intent intent15 = new Intent(this,DataToListActivity.class);
				startActivity(intent15);
				break;
			case R.id.button_toHtmlCodeActivity_1124:
				Intent intent16 = new Intent(this,HtmlCodeActivity.class);
				startActivity(intent16);
				break;
			case R.id.button_toHtmlCodeHandlerActivity_1124:
				Intent intent17 = new Intent(this,HtmlCodeHandlerActivity.class);
				startActivity(intent17);
				break;
			case R.id.button_toShowImageActivity_1124:
				Intent intent18 = new Intent(this,ShowImageActivity.class);
				startActivity(intent18);
				break;
			case R.id.button_toDelayHandlerActivity_1127:
				Intent intent19 = new Intent(this,DelayHandlerActivity.class);
				startActivity(intent19);
				break;
			case R.id.button_toZhihuRssActivity_1129:
				Intent intent20 = new Intent(this,ZhihuRssActivity.class);
				startActivity(intent20);
				break;
			case R.id.button_toZaoNoteActivity_1205:
				Intent intent21 = new Intent();
				intent21.setClassName("com.onezao.onezao.zaonotes", "com.onezao.onezao.zaonotes.ZaonotesActivity");
				startActivity(intent21);
				break;
			case R.id.button_toRpCalcActivity_1207:
				Intent intent22 = new Intent(this,RpCalcActivity.class);
				startActivity(intent22);
				break;			
			case R.id.button_toSmsActivity_1208:
				Intent intent23 = new Intent(this,SmsActivity.class);
				startActivity(intent23);
				break;			
			case R.id.button_toSmsSenderActivity_1209:
				Intent intent24 = new Intent(this,SmsSenderActivity.class);
				startActivity(intent24);
				break;			
			case R.id.button_toIPDialerActivity_1210:
				Intent intent25 = new Intent(this,IPDialerActivity.class);
				startActivity(intent25);
				break;			
			case R.id.button_toSmsReveicerActivity_1210:
				Intent intent26 = new Intent(this,SmsReceiverActivity.class);
				startActivity(intent26);
				break;			
			case R.id.button_toSendBroadcastActivity_1211:
				Intent intent27 = new Intent(this,SendBroadcastActivity.class);
				startActivity(intent27);
				break;			
			case R.id.button_toSendOrderBroadcastActivity_1211:
				Intent intent28 = new Intent(this,SendOrderBroadcastActivity.class);
				startActivity(intent28);
				break;			
			case R.id.button_toRigisterReceiverActivity_1212:
				Intent intent29 = new Intent(this,RigisterReceiverActivity.class);
				startActivity(intent29);
				break;			
			case R.id.button_toAutoSmsReceiverActivity_1212:
				Intent intent30 = new Intent(this,AutoSmsReceiverActivity.class);
				startActivity(intent30);
				break;			
			case R.id.button_toAlertDialogActivity_1213:
				Intent intent31 = new Intent(this,AlertDialogActivity.class);
				startActivity(intent31);
				break;			
			case R.id.button_toServiceActivity_1214:
				Intent intent32 = new Intent(this,ServiceActivity.class);
				startActivity(intent32);
				break;			
			case R.id.button_toRecordServiceActivity_1214:
				Intent intent33 = new Intent(this,RecordServiceActivity.class);
				startActivity(intent33);
				break;			
			case R.id.button_toBindServiceActivity_1214:
				Intent intent34 = new Intent(this,BindServiceActivity.class);
				startActivity(intent34);
				break;			
			case R.id.button_toMusicPlayerFrameActivity_1216:
				Intent intent35 = new Intent(this,MusicPlayerFrameActivity.class);
				startActivity(intent35);
				break;			
			case R.id.button_toMixStartActivity_1217:
				Intent intent36 = new Intent(this,MixStartActivity.class);
				startActivity(intent36);
				break;			
			case R.id.button_toContentProviderActivity_1224:
				Intent intent37 = new Intent(this,ContentProviderActivity.class);
				startActivity(intent37);
				break;			
			case R.id.button_toSmsBackupActivity_1225:
				Intent intent38 = new Intent(this,SmsBackupActivity.class);
				startActivity(intent38);
				break;			
			case R.id.button_toQueryContactActivity_1229:
				Intent intent39 = new Intent(this,QueryContactActivity.class);
				startActivity(intent39);
				break;			
			case R.id.button_toInsertContactActivity_0101:
				Intent intent40 = new Intent(this,InsertContactActivity.class);
				startActivity(intent40);
				break;			
			case R.id.button_toLoadPicActivity_0107:
				Intent intent41 = new Intent(this,LoadPicActivity.class);
				startActivity(intent41);
				break;			
			case R.id.button_toPicCopyActivity_0107:
				Intent intent42 = new Intent(this,PicCopyActivity.class);
				startActivity(intent42);
				break;			
			case R.id.button_toProcessPicActivity_0108:
				Intent intent43 = new Intent(this,ProcessPicActivity.class);
				startActivity(intent43);
				break;			
			case R.id.button_toPaintBoardActivity_0108:
				Intent intent44 = new Intent(this,PaintBoardActivity.class);
				startActivity(intent44);
				break;			
			case R.id.button_toPicGalleryActivity_0110:
				Intent intent45 = new Intent(this,PicGalleryActivity.class);
				startActivity(intent45);
				break;			
			case R.id.button_toEraseClothActivity_0111:
				Intent intent46 = new Intent(this,EraseClothActivity.class);
				startActivity(intent46);
				break;			
			case R.id.button_toMusicPlayerActivity_0111:
				Intent intent47 = new Intent(this,MusicPlayerActivity.class);
				startActivity(intent47);
				break;			
			case R.id.button_toPlayNetMusicActivity_0113:
				Intent intent48 = new Intent(this,PlayNetMusicActivity.class);
				startActivity(intent48);
				break;			
			case R.id.button_toPlayNetVideoActivity_0113:
				Intent intent49 = new Intent(this,PlayNetVideoActivity.class);
				startActivity(intent49);
				break;			
			case R.id.button_toVideoViewActivity_0114:
				Intent intent50 = new Intent(this,VideoViewActivity.class);
				startActivity(intent50);
				break;			
			case R.id.button_toFragmentDemoActivity_0116:
				Intent intent51 = new Intent(this,FragmentDemoActivity.class);
				startActivity(intent51);
				break;			
			case R.id.button_toFragmentDynamicActivity_0118:
				Intent intent52 = new Intent(this,FragmentDynamicActivity.class);
				startActivity(intent52);
				break;			
			case R.id.button_toWeixinActivity_0121:
				Intent intent53 = new Intent(this,WeixinActivity.class);
				startActivity(intent53);
				break;			
			case R.id.button_toFragmentCommActivity_0122:
				Intent intent54 = new Intent(this,FragmentCommActivity.class);
				startActivity(intent54);
				break;			
			case R.id.button_toAutoCompleteActivity_0123:
				Intent intent55 = new Intent(this,AutoCompleteActivity.class);
				startActivity(intent55);
				break;			
			case R.id.button_toDrawableanim_0123:
				Intent intent56 = new Intent(this,DrawableAnimationActivity.class);
				startActivity(intent56);
				break;			
			case R.id.button_toViewAnimActivity_0124:
				Intent intent57 = new Intent(this,ViewAnimationActivity.class);
				startActivity(intent57);
				break;			
			case R.id.button_toNotificationDemoActivity_0125:
				Intent intent58 = new Intent(this,NotificationDemoActivity.class);
				startActivity(intent58);
				break;			
			case R.id.button_toPressureDiagramActivity_0130:
				Intent intent59 = new Intent(this,PressureDiagramActivity.class);
				startActivity(intent59);
				break;			
			case R.id.button_toSplashActivity_0207:
				Intent intent60 = new Intent(this,SplashActivity.class);
				startActivity(intent60);
				break;			
			}	
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

			AdminUtils.AdminMenu(AdminActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}
