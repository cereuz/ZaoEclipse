package com.onezao.onezao.smssender1209;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

/**
 * Created by zneo on 2017/12/9.
 */

public class ContactActivity extends Activity{
    private List<Contact>  contactList = new ArrayList<Contact>();
    private int resultCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_contact_1209);

       ListView lv_smssender =(ListView)findViewById(R.id.lv_smssender);
        //创建联系人数据
        for (int i = 0 ;i < 23 ;i++){
            Contact contact =  new Contact();
            contact.name = "neo" + i;
            contact.phone = "1366666666"+i;
            contactList.add(contact);
        }
        lv_smssender.setAdapter(new MyAdapter());
        lv_smssender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                  //获取电话
                String  name = contactList.get(position).name;
                String  phone = contactList.get(position).phone;
                //保存数据
                Intent intent =  new Intent();
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                setResult(resultCode,intent);
                //关闭页面
                finish();
                //传回数据
            }
        });
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contactList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View convertView, ViewGroup viewGroup) {
            View  view = null;
            if(convertView == null){
                view = View.inflate(getApplicationContext(),R.layout.item_contact_1209,null);
            } else {
                view = convertView;
            }
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        EditText et_phone = (EditText) view.findViewById(R.id.et_phone);
       final String name =  contactList.get(i).name;
       final String phone =  contactList.get(i).phone;
            tv_name.setText(name);
            et_phone.setText(phone);

          //当ListView的item中有EditText的时候的处理
            et_phone.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ((ViewGroup) v.getParent())
                            .setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
                    return false;
                }
            });
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ((ViewGroup) v)
                            .setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                    Toast.makeText(getApplicationContext(), "点击了" + name +"："+ phone, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            return view;
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

		AdminUtils.AdminMenu(ContactActivity.this, item);
		return super.onOptionsItemSelected(item);
 }
}
