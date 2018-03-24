package com.onezao.onezao.fragmentdynamic0118;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentDynamicActivity extends Activity {
            @SuppressWarnings("deprecation")
			@Override
            protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_fragmentdynamic_0118);
            	//��ȡ��Ļ�Ŀ��
            	int width = getWindowManager().getDefaultDisplay().getWidth();
            	int height = getWindowManager().getDefaultDisplay().getHeight();
            	//һ����ȡFragmentManager
            	FragmentManager manager = getFragmentManager();
            	//��������fragment����
            	FragmentTransaction transaction = manager.beginTransaction();
            	if(width  >  height ){
            		//����      ��fragment�����滻��viewgroup�ڵ���
            		//��һ����������������fragment��viewgroup��ID
            		//�ڶ���������Ҫ��ʾ��fragment����
            		transaction.replace(android.R.id.content, new SecondFragment());
            	}  else  {
            		//����
            		transaction.replace(android.R.id.content, new  FirstFragment());
            	}
            	//�������Ӧ��fragmentһ��Ҫ����commit�ύ����
            	transaction.commit();
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

    			AdminUtils.AdminMenu(FragmentDynamicActivity.this, item);
    			return super.onOptionsItemSelected(item);
    	 }
}
