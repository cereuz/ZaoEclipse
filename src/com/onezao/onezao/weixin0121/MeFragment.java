package com.onezao.onezao.weixin0121;

import com.onezao.onezao.zao.AdminActivity;
import com.onezao.onezao.zao.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MeFragment extends Fragment {
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                		Bundle savedInstanceState) {
                	View view = inflater.inflate(R.layout.fragment_weixin_me0121, null);
                	Button btn_weixin_me_0121 = (Button) view.findViewById(R.id.btn_weixin_me_0121);
                	btn_weixin_me_0121.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							Toast.makeText(getActivity(), "跳转到启动页面AdminActivity！", Toast.LENGTH_SHORT).show();
							Intent  intent  =  new  Intent(getActivity(),AdminActivity.class);
							startActivity(intent);
						}
					});
                	return view;
                }
}
