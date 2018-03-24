package com.onezao.onezao.alertdialog1213;

import com.onezao.onezao.listview1121.SimpleAdapterActivity;
import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AlertDialogActivity extends Activity {
	final String positive = "ȷ��" ;
    final String negative = "ȡ��";
	private TextView tv_mydialog_textview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert_dialog_1213);
		tv_mydialog_textview = (TextView) findViewById(R.id.tv_mydialog_textview);
	}

	//�����ť������һ����ͨ�Ի���
	public void clickpt(View  view){		
		// ͨ��builder ������������
		AlertDialog.Builder   builder =  new  Builder(this);
		builder.setTitle("����zz");
		builder.setMessage("��������ңԶ�ľ�����û�����磡");
		builder.setPositiveButton(positive, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.out.println("�����ȷ����ť");
				Toast.makeText(AlertDialogActivity.this, positive, Toast.LENGTH_SHORT).show();
				tv_mydialog_textview.setText(positive);
				Intent  intent = new Intent(AlertDialogActivity.this,SimpleAdapterActivity.class);
				startActivity(intent);
			}
		});
		builder.setNegativeButton(negative, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.out.println("�����ȡ����ť");
				Toast.makeText(AlertDialogActivity.this, negative, Toast.LENGTH_SHORT).show();
				tv_mydialog_textview.setText(negative);
			}
		});
		//���һ����һ��Ҫ�ǵú�Toastһ����show������
		builder.show();
	}
	
	//�����ť������һ����ѡ�Ի���
	public void clickdx(View  view){
		// ͨ��builder ������������
		AlertDialog.Builder   builder =  new  Builder(this);
		builder.setTitle("��ѡ����ϲ����ˮ��(��ѡ)��");
		final String items[] = {"ƻ��","�㽶","ѩ��","����","����","����","Android","ios","c","C++","html","c#","php"};
		// ��ѡ�����õ�ѡ��ťѡ��  .   -1����û����Ŀ��ѡ��
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
                   	//[1] ��ѡ�����Ŀ��ȡ����
				String item = items[which];
				Toast.makeText(AlertDialogActivity.this, item, Toast.LENGTH_SHORT).show();
				tv_mydialog_textview.setText(item);
				//[2] �ѶԻ���ر�
				dialog.dismiss();
			}
		});		
		//���һ����һ��Ҫ�ǵú�Toastһ����show������
		builder.show();
	}
	
	//�����ť������һ����ѡ�Ի���
		public void clickdxs(View  view){
			// ͨ��builder ������������
			AlertDialog.Builder   builder =  new  Builder(this);
			builder.setTitle("��ѡ����ϲ����ˮ��(��ѡ)��");
			final String items[] = {"ƻ��","�㽶","ѩ��","����","����","����","Android","ios","c","C++","html","c#","php"};
			//Ĭ��ѡ�е�ѡ��
			final boolean[ ]  checkedItems={false,true,false,false,true,true,false,false,false,true,false,false,false};
			//��ѡ��ť�ĵ���¼�
			builder.setMultiChoiceItems(items, checkedItems, new  OnMultiChoiceClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        					
				}
			});
			builder.setPositiveButton(positive, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
                     //��ѡ�е���Ŀ�����ݸ���ȡ����
					StringBuffer  sb = new StringBuffer();
					for (int i = 0; i < checkedItems.length; i++) {
						//�ж�һ��ѡ�е�
						if(checkedItems[i]){
							String  fruit = items[i];
							sb.append(fruit + "    ");
						}
					}
					Toast.makeText(AlertDialogActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
					tv_mydialog_textview.setText(sb.toString());
					//�رնԻ���
					dialog.dismiss();
				}
			});
			//���һ����һ��Ҫ�ǵú�Toastһ����show������
			builder.show();
		}
		
		
		//�������صĶ�����ֱ�������̸߳���UI
		//�����ť ����һ���������Ի���
		public void clickjd(View  view){
			//�������صĶ�����ֱ�������̸߳���UI
			//ʹ��ProgressDialog����
		 final ProgressDialog  dialog = new  ProgressDialog(this);
			dialog.setTitle("��������������");
			//��������һ�½���������ʽ
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			//���һ����һ��Ҫ�ǵú�Toastһ����show������
			dialog.show();
			//����һ�����߳�
			new Thread(){public void run(){
				//���ý��������ֵ
				dialog.setMax(100);
				//���õ�ǰ����
				for(int i = 0;i < 100 ;i++){
					dialog.setProgress(i);
					//˯��һ��
					SystemClock.sleep(100);
				}
				//�رնԻ���
				dialog.dismiss();
			};
		}.start();
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

			AdminUtils.AdminMenu(AlertDialogActivity.this, item);
			return super.onOptionsItemSelected(item);
	 }
}