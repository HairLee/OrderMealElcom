package com.elcom.com.quickbloxex.view.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elcom.com.quickbloxex.R;
import com.elcom.com.quickbloxex.view.ui.activity.OrderMealScheduleActivity;

public class NewsFragment extends Fragment {

    private View view;
    private Button btnShow;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(view == null){
            view = inflater.inflate(R.layout.fragment_news,null);


            btnShow = (Button)view.findViewById(R.id.btnShow);
            btnShow.setOnClickListener(v -> OrderMealScheduleActivity.start(getContext(),1));
        }

        return view;
    }


    public void setupView(){

    }

}
