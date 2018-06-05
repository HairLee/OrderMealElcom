package com.elcom.com.quickbloxex.view.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elcom.com.quickbloxex.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderMealScheduleFragment extends Fragment {



    private int mValue = 0;
    private String mDay = "";
    private List<Date> mDates = new ArrayList<>();
    private int mCureentPos = 0;
    private int mFrom = 0;
    private int mTo = 0;
    TextView tv2,tv3,tv4,tv5,tv6;
    public OrderMealScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        assert getArguments() != null;
        mFrom = getArguments().getInt("from", 0);
        mTo = getArguments().getInt("position", 0);

        Log.e("hailpt"," OrderMealScheduleFragment onCreateView mFrom === "+mFrom);

        mDay = getArguments().getString("Day", "Test");
        return inflater.inflate(R.layout.fragment_order_meal_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvNumber = (TextView)view.findViewById(R.id.tvNumber);
         tv2 = (TextView)view.findViewById(R.id.tv2);
         tv3 = (TextView)view.findViewById(R.id.tv3);
         tv4 = (TextView)view.findViewById(R.id.tv4);
         tv5 = (TextView)view.findViewById(R.id.tv5);
         tv6 = (TextView)view.findViewById(R.id.tv6);
        Log.e("hailpt"," OrderMealScheduleFragment onViewCreated");



        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
        tv2.setText(dateFormat.format(mDates.get(mFrom)));
        tv3.setText(dateFormat.format(mDates.get(mFrom+1)));
        tv4.setText(dateFormat.format(mDates.get(mFrom + 2)));
        tv5.setText(dateFormat.format(mDates.get(mFrom + 3)));
        tv6.setText(dateFormat.format(mDates.get(mFrom + 4)));


        tvNumber.setText(mDay+ " Pos = "+mTo);
    }

    public void updateLayout(){

    }


    public void setData(List<Date> pDates, int from){
        mDates = pDates;
        Log.e("hailpt"," OrderMealScheduleFragment setData");
//        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
//        tv2.setText(dateFormat.format(mDates.get(from+1)));
//        tv3.setText(dateFormat.format(mDates.get(from+2)));
//        tv4.setText(dateFormat.format(mDates.get(from + 3)));
//        tv5.setText(dateFormat.format(mDates.get(from + 4)));
//        tv6.setText(dateFormat.format(mDates.get(from + 5)));
    }
}
