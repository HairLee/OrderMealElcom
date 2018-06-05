package com.elcom.com.quickbloxex.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.elcom.com.quickbloxex.view.ui.fragment.NewsFragment;
import com.elcom.com.quickbloxex.view.ui.fragment.OrderMealScheduleFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Hailpt on 6/5/2018.
 */
public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    private List<Date> mDates;
    private int mCureentPos = 0;
    private int mPrevious= 0;
    public DemoCollectionPagerAdapter(FragmentManager fm,List<Date> pDates) {
        super(fm);
        mDates = pDates;
    }

    @Override
    public Fragment getItem(int position) {


        Calendar cal = Calendar.getInstance();
        cal.setTime(mDates.get(position));



        OrderMealScheduleFragment newsFragment = new OrderMealScheduleFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("Day",cal.getTime().toString());

        if(mCureentPos == 0) {
            args.putInt("from", 0);
            mCureentPos = mCureentPos + 4;
        } else {
            args.putInt("from", mCureentPos);
            if(mPrevious < position){
                mCureentPos = mCureentPos + 5;
            } else {
                mCureentPos = mCureentPos -5;
            }
        }
        Log.e("hailpt"," DemoCollectionPagerAdapter position = "+position + "mPrevious "+mPrevious);
        mPrevious = position;




        newsFragment.setData(mDates, mCureentPos);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public int getCount() {
        return mDates.size();
    }

    public void updateLayout(){

    }


}
