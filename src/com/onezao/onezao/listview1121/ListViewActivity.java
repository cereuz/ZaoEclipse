package com.onezao.onezao.listview1121;

import com.onezao.onezao.login1114.LoginActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends Activity {
           @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_listview_1121);
        	
        	//�ҵ����ĵĿؼ�
        	ListView lv_listview = (ListView) findViewById(R.id.lv_listview);
        	lv_listview.setAdapter(new MyAdapter());
        }
           
           public class  MyAdapter   extends  BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 16061606;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView  tv_text  = null;
				if(convertView == null){
					   //˵��û�п������õ�view������Ҫ�����µ�View����
				     tv_text  =  new TextView(ListViewActivity.this);
				     System.out.println("�����µ�View����");
				}   else  {
					//˵���п������õ�view����ֱ�Ӹ��þͿ��ԡ�
					tv_text  = (TextView) convertView;
					System.out.println("����ԭ����View����");
				}
				//��TextView�������ݡ�
                 tv_text.setText("���ǵ�" + position+"����Ŀ��");
				return tv_text;
			}
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

      			AdminUtils.AdminMenu(ListViewActivity.this, item);
      			return super.onOptionsItemSelected(item);
      	 }
}
