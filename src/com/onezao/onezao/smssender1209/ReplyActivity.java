package com.onezao.onezao.smssender1209;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by zneo on 2017/12/9.
 */

public class ReplyActivity extends Activity{
    public int resultCode = 1;
    private  String[]   smss = {"我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗",
            "我是葡萄","我是香蕉","我是苹果","我是荔枝","我是哈密瓜","我是西瓜","我是甘蔗"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_contact_1209);
       ListView lv_smssender = (ListView)findViewById(R.id.lv_smssender);
       lv_smssender.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.item_sms_reply_1209,smss));
        lv_smssender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //得到条目的数据
                String item = ((TextView)view).getText().toString();
                //保存数据
                Intent intent =  new Intent();
                intent.putExtra("item",item);
                setResult(resultCode,intent);
                //关闭当前页面
                finish();
            }
        });
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

		AdminUtils.AdminMenu(ReplyActivity.this, item);
		return super.onOptionsItemSelected(item);
 }
}
