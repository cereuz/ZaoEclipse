package com.onezao.onezao.fragmentdemo0116;

import com.onezao.onezao.zao.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class FirstFragment extends Fragment {
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
            		Bundle savedInstanceState) {
            	View view = inflater.inflate(R.layout.fragment_first_0116, null);
            	
            	Button btn_firstfragment_0116 = (Button) view.findViewById(R.id.btn_firstfragment_0116);
            	btn_firstfragment_0116.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Toast.makeText(getActivity(), "µÚÒ»¸ö Fragment", Toast.LENGTH_LONG).show();
					}
				});
            	return view;
            }
}
