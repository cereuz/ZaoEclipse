package com.onezao.onezao.intentactivity1205;

import com.onezao.onezao.zao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IntentActivity  extends Activity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.activity_intent_1205);
        	
        	EditText et_intent_title = (EditText) findViewById(R.id.et_intent_title);
        	EditText et_intent_content = (EditText) findViewById(R.id.et_intent_content);

        	Intent intent = getIntent();
        	String content = intent.getStringExtra("sms_body");
        	String title = content.substring(6, 9);
        	
        	et_intent_title.setText(title);
        	et_intent_content.setText(content);
        	
        }
}
