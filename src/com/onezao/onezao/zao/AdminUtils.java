package com.onezao.onezao.zao;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

public class AdminUtils {
     public static boolean AdminMenu(Context  context ,MenuItem item) {
 		int id = item.getItemId();
 		if (id == R.id.action_diary) {
 			Toast.makeText(context, "�ռǡ�����", Toast.LENGTH_SHORT).show();
 /*			Intent  intent  =  new  Intent(this,WritediaryActivity.class);
 			startActivity(intent);*/
 			return true;
 		}  else  if (id == R.id.action_query) {
 			Toast.makeText(context, "��ѯ������", Toast.LENGTH_SHORT).show();
 /*			Intent  intent  =  new  Intent(this,CheckActivity.class);
 			startActivity(intent);*/
 			return true;
 		}  else  if (id == R.id.action_settings) {
 			Toast.makeText(context, "���á�����", Toast.LENGTH_SHORT).show();
 /*			Intent  intent  =  new  Intent(this,SettingActivity.class);
 			startActivity(intent);*/
 			return true;
 		}  else  if (id == R.id.action_about) {
 			Toast.makeText(context, "���ڡ�����", Toast.LENGTH_SHORT).show();
 /*	    Intent  intent  =  new  Intent(this,AboutActivity.class);
 			startActivity(intent);*/
 			return true;
 		}  else  if (id == android.R.id.home) {
 			Toast.makeText(context, "��ҳ,��ʾȫ��", Toast.LENGTH_SHORT).show();
 			/*Intent  intent  =  new  Intent(this,AboutActivity.class);
 			startActivity(intent);*/
 			//finish()����Ŀǰ����Activity���еķ�������Ҫʹ��Actitvityǿתһ�¡�
 			((Activity)context).finish();
 			return true;
 		}  else  if (id ==  R.id.action_search) {
 			Toast.makeText(context, "����һ�£�onezao��ȥ���ظ�", Toast.LENGTH_SHORT).show();
 			return true;
 	}
 		return false;
 	}
}
