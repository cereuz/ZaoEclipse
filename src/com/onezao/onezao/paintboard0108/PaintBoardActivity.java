package com.onezao.onezao.paintboard0108;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.onezao.onezao.zao.AdminUtils;
import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PaintBoardActivity extends Activity implements 	android.view.View.OnClickListener {
	private ImageView iv_paintboard_0108;
	private EditText et_paintboard_0108;
	private Paint paint;
	private Canvas canvas;
	private Bitmap bitmap2;
	private EditText et_paintsize_0108;
	private EditText et_paintcolor_0108;
	private String picPath;
	private Button btn_paintboard_0108;
	private Button btn_paintsave_0108;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paintboard_0108);
		iv_paintboard_0108 = (ImageView) findViewById(R.id.iv_paintboard_0108);
		et_paintboard_0108 = (EditText) findViewById(R.id.et_paintboard_0108);

		et_paintsize_0108 = (EditText) findViewById(R.id.et_paintsize_0108);
		et_paintsize_0108.setFocusable(false);
		et_paintsize_0108.setOnClickListener(this);

		et_paintcolor_0108 = (EditText) findViewById(R.id.et_paintcolor_0108);
		et_paintcolor_0108.setFocusable(false);
		et_paintcolor_0108.setOnClickListener(this);
		
		btn_paintboard_0108 = (Button) findViewById(R.id.btn_paintboard_0108);
		btn_paintboard_0108.setOnClickListener(this);
		
		btn_paintsave_0108 = (Button) findViewById(R.id.btn_paintsave_0108);
		btn_paintsave_0108.setOnClickListener(this);
	}

	//�жϲ��������¼�
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ����ͼƬ������ͼƬ���ʵ���¼���
		case R.id.btn_paintboard_0108:
			loadpic();
		    break;
		    //���û��ʳߴ�
		case R.id.et_paintsize_0108:
			chooseSize();
			break;
			//���û�����ɫ
		case R.id.et_paintcolor_0108:
			chooseColor();
			break;
			//���滭��༭���ͼƬ
		case R.id.btn_paintsave_0108:
//			File file = new File(Environment.getExternalStorageDirectory().getPath(),"/ame/"+SystemClock.uptimeMillis()+".png");
			File file = new File(Environment.getExternalStorageDirectory().getPath(),"/ame/"+ZaoUtils.getSystemTime()+".png");
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				// ͨ������������ļ����浽SD����
				// 1.����ĸ�ʽ 2.�����������0�������������С��100��������������� 3.�������ȷ�ϱ����λ��
				bitmap2.compress(CompressFormat.PNG, 100, fos);
				//����SD�����صĹ㲥��ϵͳ�յ��㲥֮�󣬻�ɨ��SD�������±�����ļ�de·����ӵ����ݿ���
/*				Intent  intent = new  Intent();
				intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
				intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
				sendBroadcast(intent);*/
				//��׿4.0֮�󣬱���ͨ�������ķ�ʽ�����С���Ϊϵͳ������Ȩ�ޣ����ܷ��ϱߵĹ㲥��
				this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + Environment.getExternalStorageDirectory())));  
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toast.makeText(getApplicationContext(), "����ɹ���" , Toast.LENGTH_SHORT).show();
			break;
		}
	}

           //����ͼƬ�ķ���
	private void loadpic() {
		String et_processpicPath = et_paintboard_0108.getText().toString().trim();
		picPath = ZaoUtils.pathSD + et_processpicPath;
		// 0.����ͼƬ��Դ���ѱ���ͼ���ؽ���
		Bitmap bitmap = BitmapFactory.decodeFile(picPath);
		// 1. �����յ�ͼƬ�������Ҫ����ͼƬ����Ҫmutable
		bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
				bitmap.getConfig());
		// 2.ʹ�ÿյ�ͼƬ����һ��canvas
		canvas = new Canvas(bitmap2);
		// 3. ��ͼƬ , ��ͼƬ��Դ���������ϡ�
		paint = new Paint();
		Matrix matrix = new Matrix();
		canvas.drawBitmap(bitmap, matrix, paint);
		// 4. �����õ�ͼƬ���õ��ؼ���
		iv_paintboard_0108.setImageBitmap(bitmap2);

		iv_paintboard_0108.setOnTouchListener(new OnTouchListener() {
			private float startx;
			private float starty;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					System.out.println("����View");
					Toast.makeText(PaintBoardActivity.this, "����View",
							Toast.LENGTH_SHORT).show();
					// ��¼����λ��
					startx = event.getRawX();
					starty = event.getRawY();
					break;

				case MotionEvent.ACTION_MOVE:
					System.out.println("�ƶ�");
					Toast.makeText(PaintBoardActivity.this, "�ƶ�",
							Toast.LENGTH_SHORT).show();

					float x = event.getRawX();
					float y = event.getRawY();
					// ���ߣ�������ƶ���·��������
					canvas.drawLine(startx, starty, x, y, paint);
					// ��ͼƬ��ʾ���ؼ���
					iv_paintboard_0108.setImageBitmap(bitmap2);
					// ���߽���֮�󣬰ѿ�ʼ�����޸�Ϊ���µ�λ�á�
					startx = x;
					starty = y;
					break;

				case MotionEvent.ACTION_UP:
					System.out.println("̧��");
					Toast.makeText(PaintBoardActivity.this, "̧��",
							Toast.LENGTH_SHORT).show();
					break;
				}
				// ����true˵����ǰ�ؼ���������һЩ���touch�¼�
				// touch�¼���һ��ACTION_DOWN ��ACTION_MOVE��0����������һ��ACTION_UP���
				// ���ACTION_DOWN��ʱ��û�з���true����ACTION_MOVE��ACTION_UP�ͽ������ؼ�������
				return true; // ���뷵��true������ʵ�ֵ�����ʹ��ܡ�
			}
		});
	}

	// ѡ�����û�����ɫ
	private void chooseColor() {
		// ͨ��builder ������������
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��ѡ������Ҫ����ɫ��");
		final String items[] = { "Color.BLACK", "Color.BLUE", "Color.CYAN",
				"Color.DKGRAY", "Color.GRAY", "Color.GREEN", "Color.LTGRAY",
				"Color.MAGENTA", "Color.RED", "Color.TRANSPARENT",
				"Color.WHITE", "Color.YELLOW" };
		// ��ѡ�����õ�ѡ��ťѡ�� . -1����û����Ŀ��ѡ��
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// [1] ��ѡ�����Ŀ��ȡ����
				final String item = items[which];
				Toast.makeText(PaintBoardActivity.this, item,
						Toast.LENGTH_SHORT).show();
				runOnUiThread(new Runnable() {
					public void run() {
						et_paintcolor_0108.setText(item);
					}
				});
				switch (which) {
				case 0:
					paint.setColor(Color.BLACK);
					break;
				case 1:
					paint.setColor(Color.BLUE);
					break;
				case 2:
					paint.setColor(Color.CYAN);
					break;
				case 3:
					paint.setColor(Color.DKGRAY);
					break;
				case 4:
					paint.setColor(Color.GRAY);
					break;
				case 5:
					paint.setColor(Color.GREEN);
					break;
				case 6:
					paint.setColor(Color.LTGRAY);
					break;
				case 7:
					paint.setColor(Color.MAGENTA);
					break;
				case 8:
					paint.setColor(Color.RED);
					break;
				case 9:
					paint.setColor(Color.TRANSPARENT);
					break;
				case 10:
					paint.setColor(Color.WHITE);
					break;
				case 11:
					paint.setColor(Color.YELLOW);
					break;
				}
				// [2] �ѶԻ���ر�
				dialog.dismiss();
			}
		});
		// ���һ����һ��Ҫ�ǵú�Toastһ����show������
		builder.show();
	}

	// ѡ�����û��ʴ�С
	private void chooseSize() {
		// ͨ��builder ������������
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("��ѡ������Ҫ�Ļ��ʴ�С��");
		final String items[] = { "6", "9", "12", "16", "20", "24", "30", "36",
				"50", "60", "80", "100", "200", "500", "1000", "2000" };
		// ��ѡ�����õ�ѡ��ťѡ�� . -1����û����Ŀ��ѡ��
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// [1] ��ѡ�����Ŀ��ȡ����
				final String item = items[which];
				Toast.makeText(PaintBoardActivity.this, item,
						Toast.LENGTH_SHORT).show();
				runOnUiThread(new Runnable() {
					public void run() {
						et_paintsize_0108.setText(item);
						paint.setStrokeWidth(Integer.valueOf(item));
					}
				});
				// [2] �ѶԻ���ر�
				dialog.dismiss();
			}
		});
		// ���һ����һ��Ҫ�ǵú�Toastһ����show������
		builder.show();
	}

	// ���ض����˵�����Ӳ˵��ĵ���¼���
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// �������Ͻǵ�ͼ��ĵ���¼� ActionBar
		ActionBar actionBar = this.getActionBar();
		actionBar.setHomeButtonEnabled(true);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		AdminUtils.AdminMenu(PaintBoardActivity.this, item);
		return super.onOptionsItemSelected(item);
	}
}
