package com.elcom.com.quickbloxex.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.elcom.com.quickbloxex.R;

/**
 * Created by Hailpt on 6/4/2018.
 */
public class TestPagerAdapter extends PagerAdapter {

    public Object instantiateItem(ViewGroup collection, int position) {


        return collection.findViewById(R.id.page_one);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        // No super
    }
}
