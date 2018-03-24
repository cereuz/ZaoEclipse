package com.onezao.onezao.fragmentcomm0122;

import com.onezao.onezao.zao.R;
import com.onezao.onezao.zao.ZaoUtils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment {
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                		Bundle savedInstanceState) {
                	View view = inflater.inflate(R.layout.fragment_comm_first_0122, null);
                	Button btn_fragmentcomm_0122 = (Button) view.findViewById(R.id.btn_fragmentcomm_0122);
                	btn_fragmentcomm_0122.setOnClickListener(new  OnClickListener() {
						
						@Override
						public void onClick(View view) {
							String   text  =   ZaoUtils.getSystemTime();
							SecondFragment  secondFragment =(SecondFragment)getActivity().getFragmentManager().findFragmentByTag("fragmentcomm_right");
//							SecondFragment   secondFragment  =  new  SecondFragment();
							secondFragment.changeText(text);
						}
					});
                	return view;
                }
}
