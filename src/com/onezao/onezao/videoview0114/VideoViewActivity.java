package com.onezao.onezao.videoview0114;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {
           private VideoView vv_videoview_0114;
		private EditText et_videoview_0114;
		private LayoutParams layoutParams2;

		@Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
			  //���ر�����
			getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        	setContentView(R.layout.activity_videoview_0114);
        	et_videoview_0114 = (EditText) findViewById(R.id.et_videoview_0114);
        	vv_videoview_0114 = (VideoView) findViewById(R.id.vv_videoview_0114);
        }
		
		//׼�������ŵİ�ť
		public  void  prepare(View  view){
			String  path  =  et_videoview_0114.getText().toString().trim();
			//������Ƶ��·����setVideoPath()�����У�������һ��MediaPlayer���󣬵�����setDataSource()����
			//Ҳ�������첽׼���ķ���
			vv_videoview_0114.setVideoPath(path);
            //���ü���
			vv_videoview_0114.setOnPreparedListener(new OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mp) {
					   vv_videoview_0114.start();
				}
			});
			//���ý��������� 
			vv_videoview_0114.setMediaController(new MediaController(this));
			
		}
		
		//������ͣ�Ŀ��ư�ť
		public  void  start(View  view){
			
		}
		
		//������Ļ�л�
		  @Override
		    public void onConfigurationChanged(Configuration newConfig) {
		        super.onConfigurationChanged(newConfig);
		        int rot = getWindowManager().getDefaultDisplay().getRotation();
		        if(rot == Surface.ROTATION_90 || rot == Surface.ROTATION_270){
		        layoutParams2 = vv_videoview_0114.getLayoutParams();
		            RelativeLayout.LayoutParams layoutParams =
		                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		            vv_videoview_0114.setLayoutParams(layoutParams);
		            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		        }else if(rot == Surface.ROTATION_0){
//		            RelativeLayout.LayoutParams lp = new  RelativeLayout.LayoutParams(320,240);
//		            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
		        	vv_videoview_0114.setLayoutParams(layoutParams2);
		        }
	            //������ɵ�ʱ��ļ���
	            vv_videoview_0114.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer arg0) {
					   	Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT).show();
					}
				});
		    }
		  
}
