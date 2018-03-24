package com.onezao.onezao.listview1121;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onezao.onezao.login1114.LoginActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleAdapterActivity extends Activity {
	   public String title  = "���죬��д��һƪ�ռǡ�����";
	   public String content = "�������Ϊ�գ���д������ռǡ�\n Email : sunedo@qq.com \n��ҳ ��www.onezao.com\n�Ա� ��http://fone.taobao.com \n���� ��https://site.douban.com/273850/";
          @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_listview_1121);
        	
            ListView   lv_listview = (ListView) findViewById(R.id.lv_listview);
            List<Map<String,String>>   data  =   new ArrayList<Map<String, String>>();
            String[]   from ={"title","content"};
            for (int i = 0; i <  160; i++) {
            	Map<String,String>  item = new HashMap<String, String>();
				item.put("title", i+"��"+title);
				item.put("content", i+"��"+content);
				data.add(item);				
			}
            int[]  to =new int[]{R.id.tv_title,R.id.tv_content};
            /*
             * ��һ��������������
             * �ڶ���������Ҫ��ʾ�����ݣ�ÿһ����Ŀ�����ݷŵ�һ��Map�У��ٰ�Map���ŵ�������
             * ������������Ҫ������Ŀ�Ĳ����ļ�����ԴID
             * ���ĸ�������String���飬ÿһ��Ԫ�أ����Ǳ���Ҫչʾ���ݵ�map�е�key��Ҫչʾ��������key��Ӧ��valueֵ
             * �����������int ���飬��ŵ���Ҫչʾ���ݵĿؼ�ID��ÿһ��Ԫ�ظ�String�����Ԫ�ص�Ҫkey��Ӧ������Ȼ��ϵͳƥ��valueֵ
             * չʾ�����Ǵ�String�����Ԫ����Ϊkey��map��ȡֵ��ȡ����ֵչʾ��int�����ӦID Ԫ�صĿؼ���
             */
            lv_listview.setAdapter(new SimpleAdapter(this, data, R.layout.item_simpleadapter_1121, from, to));
            //������Ŀ�ĵ���¼�
            lv_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//���ò���view�Ǳ�������Ǹ�item����ȡ��Ҫ������
					 TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
					 String text = tv_title.getText().toString();
                     Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();   					
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

     			AdminUtils.AdminMenu(SimpleAdapterActivity.this, item);
     			return super.onOptionsItemSelected(item);
     	 }
}
