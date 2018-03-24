package com.onezao.onezao.pressurediagram0130;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyPressureView extends View {
	private int pressure = 300;

	public MyPressureView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		//����һ��defStyleAttr  ��xml�ļ�������һ����ʽ��ʱ�򣬽���xml֮�󣬾ͻ�ת�������defStyleAttr
	}

	public MyPressureView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//AttributeSet  ���Լ��ϣ�����xml�ļ�ʱ�����Զ����װ�����AttributeSet��
	}

	public MyPressureView(Context context) {
		super(context);
		//����Ա�ڴ����ж�̬�����ؼ���ʱ��ʹ��
	}
        
	//����ѹ����С
	public void setPressure(int pressure) {
		this.pressure = pressure;
		//��pressure�����仯��ʱ����Ҫ����onDraw�������»�һ�¿ؼ�Viewͼ��
		//ʹ��ǰ��View��Ч�����View�ǿɼ��ģ���δ����ĳһʱ�̣����Զ�����onDraw���»��ƽ���
      //		invalidate();����������߳�ʹ�õģ����̲߳����޸�UI����������̣߳���Ҫ����postInvalidate();
		postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new  Paint();
		if(pressure > 599){
			paint.setColor(Color.RED);
		}  else if (pressure > 499){
			paint.setColor(Color.YELLOW);
		}  else  {
			paint.setColor(Color.GREEN);
		}
		paint.setTextSize(40);
		canvas.drawText("��ǰѹ��ֵ��"+ pressure, 60, 960, paint);
		
//		canvas.drawRect(left, top, right, bottom, paint);
		canvas.drawRect(100, 800-pressure , 300, 900, paint);
	}
}
