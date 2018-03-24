package com.onezao.onezao.fragmentcomm0122;

import com.onezao.onezao.zao.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
            private TextView tv_fragmentcomm_second_0122;

			@Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
            		Bundle savedInstanceState) {
            	View view = inflater.inflate(R.layout.fragment_comm_second_0122, null);
            	tv_fragmentcomm_second_0122 = (TextView) view.findViewById(R.id.tv_fragmentcomm_second_0122);
            	return view;
            }
            
            //自己定义的方法
            public  void  changeText(String  text){
            	tv_fragmentcomm_second_0122.setText(text);
            }
}
