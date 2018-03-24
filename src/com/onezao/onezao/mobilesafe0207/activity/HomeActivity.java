package com.onezao.onezao.mobilesafe0207.activity;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class HomeActivity extends Activity {
           @Override
        public void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	setContentView(R.layout.activity_home_0207);
        }
}
