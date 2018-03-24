package com.onezao.onezao.fragmentdynamic0118;

import com.onezao.onezao.zao.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
            		Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_first_0116, null);
            	return view;
            }
}
