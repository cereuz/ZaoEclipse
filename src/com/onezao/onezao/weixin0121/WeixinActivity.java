package com.onezao.onezao.weixin0121;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class WeixinActivity extends Activity   implements  OnClickListener{
                   private ImageButton ib_index_0121;
				private ImageButton ib_contact_0121;
				private ImageButton ib_discover_0121;
				private ImageButton ib_me_0121;
				private IndexFragment  indexFragment;
				private ContactFragment contactFragment;
				private DiscoverFragment  discoverFragment;
				private MeFragment   meFragment;

				@Override
                protected void onCreate(Bundle savedInstanceState) {
                	super.onCreate(savedInstanceState);
                	setContentView(R.layout.activity_weixin_0121);
                	
                	ib_index_0121 = (ImageButton) findViewById(R.id.ib_index_0121);
                	ib_index_0121.setOnClickListener(this);
                	ib_contact_0121 = (ImageButton) findViewById(R.id.ib_contact_0121);
                	ib_contact_0121.setOnClickListener(this);
                	ib_discover_0121 = (ImageButton) findViewById(R.id.ib_discover_0121);
                	ib_discover_0121.setOnClickListener(this);
                	ib_me_0121 = (ImageButton) findViewById(R.id.ib_me_0121);
                	ib_me_0121.setOnClickListener(this);
                	ib_index_0121.setImageResource(R.drawable.indexon);
                }

				@Override
				public void onClick(View view) {
					//获取fragment  管理者
					FragmentManager manager = getFragmentManager();
					FragmentTransaction transaction = manager.beginTransaction();
                           int id = view.getId();		
                           switch (id) {
						case R.id.ib_index_0121:
							if(indexFragment  ==  null){
								indexFragment = new  IndexFragment();
							       }   
							transaction.replace(R.id.fragment_weixin_0121, indexFragment);
							//将四个的图片全部设置为未点击状态。
							clearIcon();
							ib_index_0121.setImageResource(R.drawable.indexon);
							break;
							
						case R.id.ib_contact_0121:
							if(contactFragment  ==  null){
					              contactFragment = new  ContactFragment();
					       }   
					        transaction.replace(R.id.fragment_weixin_0121, contactFragment);
							//将四个的图片全部设置为未点击状态。
							clearIcon();
							ib_contact_0121.setImageResource(R.drawable.contacton);
					        break;
							
						case R.id.ib_discover_0121:
							if(discoverFragment  ==  null){
								discoverFragment = new  DiscoverFragment();
					       }   
					        transaction.replace(R.id.fragment_weixin_0121, discoverFragment);
							//将四个的图片全部设置为未点击状态。
							clearIcon();
					        ib_discover_0121.setImageResource(R.drawable.discoveron);
							break;
							
						case R.id.ib_me_0121:
							if(meFragment  ==  null){
					              meFragment = new  MeFragment();
					       }   
							transaction.replace(R.id.fragment_weixin_0121, meFragment);
							//将四个的图片全部设置为未点击状态。
							clearIcon();
							ib_me_0121.setImageResource(R.drawable.meon);
							break;
						}
                           transaction.commit();
				}
				
				private  void  clearIcon(){
					ib_index_0121.setImageResource(R.drawable.indexoff);
					ib_contact_0121.setImageResource(R.drawable.contactoff);
					ib_discover_0121.setImageResource(R.drawable.discoveroff);
					ib_me_0121.setImageResource(R.drawable.meoff);
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

					AdminUtils.AdminMenu(WeixinActivity.this, item);
					return super.onOptionsItemSelected(item);
			 }
}
